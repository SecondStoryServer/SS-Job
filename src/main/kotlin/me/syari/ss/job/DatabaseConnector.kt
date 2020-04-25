package me.syari.ss.job

import me.syari.ss.core.auto.OnEnable
import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.core.sql.ConnectState
import me.syari.ss.core.sql.MySQL

object DatabaseConnector : OnEnable {
    override fun onEnable() {
        createTable()
    }

    var sql: MySQL? = null

    fun checkConnect(): ConnectState {
        return ConnectState.get(sql?.canConnect())
    }

    fun createTable(): ConnectState {
        return ConnectState.get(sql?.use {
            executeUpdate("CREATE TABLE IF NOT EXISTS JobExp(UUID VARCHAR(36), JobId VARCHAR(255), Exp INT, PRIMARY KEY(UUID, JobId));")
            executeUpdate("CREATE TABLE IF NOT EXISTS JobData(UUID VARCHAR(36) PRIMARY KEY, JobId VARCHAR(255), JobPoint INT);")
        })
    }

    object JobExp {
        private val jobExpCache = mutableMapOf<Pair<UUIDPlayer, String>, Int?>()

        fun get(uuidPlayer: UUIDPlayer, jobId: String): Int? {
            return jobExpCache.getOrPut(uuidPlayer to jobId) { getFromSQL(uuidPlayer, jobId) }
        }

        private fun getFromSQL(uuidPlayer: UUIDPlayer, jobId: String): Int? {
            var exp: Int? = null
            sql?.use {
                val result =
                    executeQuery("SELECT Exp FROM JobExp WHERE UUID = '$uuidPlayer' AND JobId = '$jobId' LIMIT 1;")
                if (result.next()) {
                    exp = result.getInt(1)
                }
            }
            return exp
        }

        fun set(uuidPlayer: UUIDPlayer, jobId: String, exp: Int?) {
            sql?.use {
                if (exp != null) {
                    executeQuery("INSERT INTO JobExp VALUE ('$uuidPlayer', '$jobId', $exp) ON DUPLICATE KEY UPDATE Exp = '$exp';")
                } else {
                    executeQuery("DELETE FROM JobExp WHERE UUID = '$uuidPlayer' LIMIT 1;")
                }
            }
            jobExpCache[uuidPlayer to jobId] = exp
        }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            jobExpCache.keys.removeIf { it.first == uuidPlayer }
        }

        fun clearCache() {
            jobExpCache.clear()
        }
    }

    object ActiveJob {
        private val activeJobCache = mutableMapOf<UUIDPlayer, String?>()

        fun get(uuidPlayer: UUIDPlayer): String? {
            return activeJobCache.getOrPut(uuidPlayer) { getFromSQL(uuidPlayer) }
        }

        private fun getFromSQL(uuidPlayer: UUIDPlayer): String? {
            var id: String? = null
            sql?.use {
                val result = executeQuery("SELECT JobId FROM JobData WHERE UUID = '$uuidPlayer' LIMIT 1;")
                if (result.next()) {
                    id = result.getString(1)
                }
            }
            return id
        }

        fun set(uuidPlayer: UUIDPlayer, id: String?) {
            sql?.use {
                executeQuery("INSERT INTO JobData VALUE ('$uuidPlayer', '$id', 0) ON DUPLICATE KEY UPDATE JobId = '$id';")
            }
            activeJobCache[uuidPlayer] = id
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

        fun set(uuidPlayer: UUIDPlayer, point: Int) {
            sql?.use {
                executeQuery("INSERT INTO JobData VALUE ('$uuidPlayer', null, $point) ON DUPLICATE KEY UPDATE JobPoint = $point;")
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