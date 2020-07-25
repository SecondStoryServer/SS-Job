package me.syari.ss.job

import me.syari.ss.core.auto.OnEnable
import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.core.sql.ConnectState
import me.syari.ss.core.sql.ConnectState.Companion.checkConnect
import me.syari.ss.core.sql.MySQL
import me.syari.ss.job.data.JobData
import me.syari.ss.job.player.PlayerJob
import org.bukkit.OfflinePlayer

object DatabaseConnector: OnEnable {
    override fun onEnable() {
        createTable()
    }

    var sql: MySQL? = null

    fun checkConnect(): ConnectState {
        return sql.checkConnect()
    }

    fun createTable(): ConnectState {
        return sql?.run {
            use {
                executeUpdate("CREATE TABLE IF NOT EXISTS JobLevel(UUID VARCHAR(36), JobId VARCHAR(255), Level INT, PRIMARY KEY(UUID, JobId));")
                executeUpdate("CREATE TABLE IF NOT EXISTS JobData(UUID VARCHAR(36) PRIMARY KEY, ActiveJob VARCHAR(255), PlayerLevel INT, StatusPoint INT);")
            } ?: return ConnectState.CatchException
            ConnectState.Success
        } ?: ConnectState.NullError
    }

    fun deleteCache(player: OfflinePlayer) {
        deleteCache(UUIDPlayer(player))
    }

    fun deleteCache(uuidPlayer: UUIDPlayer) {
        JobLevel.deleteCache(uuidPlayer)
        ActiveJob.deleteCache(uuidPlayer)
        StatusPoint.deleteCache(uuidPlayer)
        PlayerJob.deleteCache(uuidPlayer)
        PlayerLevel.deleteCache(uuidPlayer)
    }

    fun clearCache() {
        JobLevel.clearCache()
        ActiveJob.clearCache()
        StatusPoint.clearCache()
        PlayerJob.clearCache()
        PlayerLevel.clearCache()
    }

    object JobLevel {
        private val jobLevelCache = mutableMapOf<Pair<UUIDPlayer, JobData>, Int?>()

        fun get(
            uuidPlayer: UUIDPlayer,
            jobData: JobData
        ): Int? {
            return jobLevelCache[uuidPlayer to jobData]
        }

        private val defaultAvailableJobList = JobData.jobList.filter { it.defaultAvailable }.map { it to 0 }.toMap()

        fun getJobList(
            uuidPlayer: UUIDPlayer
        ): Map<JobData, Int> {
            return defaultAvailableJobList.toMutableMap().apply {
                sql?.use {
                    val result = executeQuery(
                        "SELECT JobId, Level FROM JobLevel WHERE UUID = '$uuidPlayer';"
                    )
                    while (result.next()) {
                        val jobId = result.getString(1)
                        JobData.getById(jobId)?.let { jobData ->
                            val level = result.getInt(2)
                            jobLevelCache[uuidPlayer to jobData] = level
                            this@apply[jobData] = level
                        }
                    }
                }
            }
        }

        fun set(
            uuidPlayer: UUIDPlayer,
            jobData: JobData,
            level: Int
        ) {
            sql?.use {
                executeQuery(
                    "INSERT INTO JobLevel VALUE ('$uuidPlayer', '${jobData.id}', $level) ON DUPLICATE KEY UPDATE Level = '$level';"
                )
            }
            jobLevelCache[uuidPlayer to jobData] = level
        }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            jobLevelCache.keys.removeIf { it.first == uuidPlayer }
        }

        fun clearCache() {
            jobLevelCache.clear()
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
                val result = executeQuery("SELECT ActiveJob FROM JobData WHERE UUID = '$uuidPlayer' LIMIT 1;")
                if (result.next()) {
                    id = result.getString(1)
                }
            }
            return id?.let { JobData.getById(it) }
        }

        fun set(
            uuidPlayer: UUIDPlayer,
            jobData: JobData?
        ) {
            val id = jobData?.id
            sql?.use {
                executeQuery(
                    "INSERT INTO JobData VALUE ('$uuidPlayer', '$id', 0, 0) ON DUPLICATE KEY UPDATE ActiveJob = '$id';"
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

    object StatusPoint {
        private val statusPointCache = mutableMapOf<UUIDPlayer, Int>()

        fun get(uuidPlayer: UUIDPlayer): Int {
            return statusPointCache.getOrPut(uuidPlayer) { getFromSQL(uuidPlayer) }
        }

        private fun getFromSQL(uuidPlayer: UUIDPlayer): Int {
            var point = 0
            sql?.use {
                val result = executeQuery("SELECT StatusPoint FROM JobData WHERE UUID = '$uuidPlayer' LIMIT 1;")
                if (result.next()) {
                    point = result.getInt(1)
                }
            }
            return point
        }

        fun set(
            uuidPlayer: UUIDPlayer,
            point: Int
        ) {
            sql?.use {
                executeQuery(
                    "INSERT INTO JobData VALUE ('$uuidPlayer', null, 0, $point) ON DUPLICATE KEY UPDATE StatusPoint = $point;"
                )
            }
            statusPointCache[uuidPlayer] = point
        }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            statusPointCache.remove(uuidPlayer)
        }

        fun clearCache() {
            statusPointCache.clear()
        }
    }

    object PlayerLevel {
        private val playerLevelCache = mutableMapOf<UUIDPlayer, Int>()

        fun get(uuidPlayer: UUIDPlayer): Int {
            return playerLevelCache.getOrPut(uuidPlayer) { getFromSQL(uuidPlayer) }
        }

        private fun getFromSQL(uuidPlayer: UUIDPlayer): Int {
            var point = 0
            sql?.use {
                val result = executeQuery("SELECT PlayerLevel FROM JobData WHERE UUID = '$uuidPlayer' LIMIT 1;")
                if (result.next()) {
                    point = result.getInt(1)
                }
            }
            return point
        }

        fun set(
            uuidPlayer: UUIDPlayer,
            level: Int
        ) {
            sql?.use {
                executeQuery(
                    "INSERT INTO JobData VALUE ('$uuidPlayer', null, $level, 0) ON DUPLICATE KEY UPDATE PlayerLevel = $level;"
                )
            }
            playerLevelCache[uuidPlayer] = level
        }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            playerLevelCache.remove(uuidPlayer)
        }

        fun clearCache() {
            playerLevelCache.clear()
        }
    }
}