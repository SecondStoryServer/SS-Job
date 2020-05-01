package me.syari.ss.job

import me.syari.ss.core.auto.OnEnable
import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.core.sql.ConnectState
import me.syari.ss.core.sql.MySQL
import me.syari.ss.job.grade.JobData

object DatabaseConnector: OnEnable {
    override fun onEnable() {
        createTable()
    }

    var sql: MySQL? = null

    fun checkConnect(): ConnectState {
        return ConnectState.get(sql?.canConnect())
    }

    fun createTable(): ConnectState {
        return ConnectState.get(sql?.use {
            executeUpdate(
                "CREATE TABLE IF NOT EXISTS JobExp(UUID VARCHAR(36), JobId VARCHAR(255), Exp INT, PRIMARY KEY(UUID, JobId));"
            )
            executeUpdate(
                "CREATE TABLE IF NOT EXISTS JobData(UUID VARCHAR(36) PRIMARY KEY, JobId VARCHAR(255), JobPoint INT);"
            )
        })
    }

    object JobExp {
        private val jobExpCache = mutableMapOf<Pair<UUIDPlayer, JobData>, Int?>()

        fun get(
            uuidPlayer: UUIDPlayer, jobData: JobData
        ): Int? {
            return jobExpCache[uuidPlayer to jobData]
        }

        fun getAll(
            uuidPlayer: UUIDPlayer
        ): Map<JobData, Int?> {
            return jobExpCache.filterKeys {
                it.first == uuidPlayer
            }.map {
                it.key.second to it.value
            }.toMap()
        }

        fun reloadAll(
            uuidPlayer: UUIDPlayer
        ) {
            sql?.use {
                val result = executeQuery(
                    "SELECT JobId, Exp FROM JobExp WHERE UUID = '$uuidPlayer';"
                )
                while (result.next()) {
                    val jobId = result.getString(1)
                    JobData.getById(jobId)?.let { jobData ->
                        val exp = result.getInt(2)
                        jobExpCache[uuidPlayer to jobData] = exp
                    }
                }
            }
        }

        fun set(
            uuidPlayer: UUIDPlayer, jobData: JobData, exp: Int
        ) {
            sql?.use {
                executeQuery(
                    "INSERT INTO JobExp VALUE ('$uuidPlayer', '${jobData.id}', $exp) ON DUPLICATE KEY UPDATE Exp = '$exp';"
                )
            }
            jobExpCache[uuidPlayer to jobData] = exp
        }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            jobExpCache.keys.removeIf { it.first == uuidPlayer }
        }

        fun clearCache() {
            jobExpCache.clear()
        }
    }

    object ActiveJob {
        private val activeJobCache = mutableMapOf<UUIDPlayer, JobData?>()

        fun get(uuidPlayer: UUIDPlayer): JobData? {
            return activeJobCache.getOrPut(uuidPlayer) { getFromSQL(uuidPlayer) }
        }

        private fun getFromSQL(uuidPlayer: UUIDPlayer): JobData? {
            var id: String? = null
            sql?.use {
                val result = executeQuery("SELECT JobId FROM JobData WHERE UUID = '$uuidPlayer' LIMIT 1;")
                if (result.next()) {
                    id = result.getString(1)
                }
            }
            return id?.let { JobData.getById(it) }
        }

        fun set(
            uuidPlayer: UUIDPlayer, jobData: JobData?
        ) {
            val id = jobData?.id
            sql?.use {
                executeQuery(
                    "INSERT INTO JobData VALUE ('$uuidPlayer', '$id', 0) ON DUPLICATE KEY UPDATE JobId = '$id';"
                )
            }
            activeJobCache[uuidPlayer] = jobData
        }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            activeJobCache.remove(uuidPlayer)
        }

        fun clearCache() {
            activeJobCache.clear()
        }
    }

    object JobPoint {
        private val jobPointCache = mutableMapOf<UUIDPlayer, Int>()

        fun get(uuidPlayer: UUIDPlayer): Int {
            return jobPointCache.getOrPut(uuidPlayer) { getFromSQL(uuidPlayer) }
        }

        private fun getFromSQL(uuidPlayer: UUIDPlayer): Int {
            var point = 0
            sql?.use {
                val result = executeQuery("SELECT JobPoint FROM JobData WHERE UUID = '$uuidPlayer' LIMIT 1;")
                if (result.next()) {
                    point = result.getInt(1)
                }
            }
            return point
        }

        fun set(
            uuidPlayer: UUIDPlayer, point: Int
        ) {
            sql?.use {
                executeQuery(
                    "INSERT INTO JobData VALUE ('$uuidPlayer', null, $point) ON DUPLICATE KEY UPDATE JobPoint = $point;"
                )
            }
            jobPointCache[uuidPlayer] = point
        }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            jobPointCache.remove(uuidPlayer)
        }

        fun clearCache() {
            jobPointCache.clear()
        }
    }
}