package me.syari.ss.job.player

import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.job.DatabaseConnector
import me.syari.ss.job.data.JobData
import org.bukkit.OfflinePlayer

data class PlayerJob(val uuidPlayer: UUIDPlayer) {
    private val allJob by lazy {
        DatabaseConnector.JobLevel.getJobList(uuidPlayer).toMutableMap()
    }

    fun isActiveJob(data: JobData) = activeJob == data

    fun getJobLevel(data: JobData): Int? {
        return allJob[data]
    }

    var activeJob: JobData?
        get() {
            return DatabaseConnector.ActiveJob.get(uuidPlayer)
        }
        set(value) {
            DatabaseConnector.ActiveJob.set(
                uuidPlayer, value
            )
        }

    var jobPoint: Int
        set(value) {
            DatabaseConnector.StatusPoint.set(
                uuidPlayer, value
            )
        }
        get() {
            return DatabaseConnector.StatusPoint.get(uuidPlayer)
        }

    fun hasJobPoint(point: Int): Boolean {
        return point <= jobPoint
    }

    companion object {
        private val jobDataCache = mutableMapOf<UUIDPlayer, PlayerJob>()

        val OfflinePlayer.jobData
            get() = UUIDPlayer(this).jobData

        private val UUIDPlayer.jobData
            get() = jobDataCache.getOrPut(this) { PlayerJob(this) }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            jobDataCache.remove(uuidPlayer)
        }

        fun clearCache() {
            jobDataCache.clear()
        }
    }
}