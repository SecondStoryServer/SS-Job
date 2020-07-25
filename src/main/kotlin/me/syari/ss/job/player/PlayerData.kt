package me.syari.ss.job.player

import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.job.DatabaseConnector
import me.syari.ss.job.grade.JobData
import org.bukkit.OfflinePlayer

data class PlayerData(val uuidPlayer: UUIDPlayer) {
    private val allJob by lazy {
        DatabaseConnector.JobExp.getJobList(uuidPlayer).map {
            it to PlayerJobData(this, it)
        }.toMap().toMutableMap()
    }

    fun getJob(data: JobData): PlayerJobData {
        return allJob.getOrPut(data) { PlayerJobData(this, data) }
    }

    var activeJob: PlayerJobData?
        get() {
            return DatabaseConnector.ActiveJob.get(uuidPlayer)?.let {
                getJob(it)
            }
        }
        set(value) {
            DatabaseConnector.ActiveJob.set(
                uuidPlayer, value?.data
            )
        }

    var jobPoint: Int
        set(value) {
            DatabaseConnector.JobPoint.set(
                uuidPlayer, value
            )
        }
        get() {
            return DatabaseConnector.JobPoint.get(uuidPlayer)
        }

    fun hasJobPoint(point: Int): Boolean {
        return point <= jobPoint
    }

    fun updateExpBar() {
        uuidPlayer.player?.let { player ->
            activeJob?.let { activeJob ->
                player.sendExperienceChange(
                    activeJob.levelProgress, activeJob.level
                )
            }
        }
    }

    companion object {
        private val jobDataCache = mutableMapOf<UUIDPlayer, PlayerData>()

        val OfflinePlayer.jobData
            get() = UUIDPlayer(this).jobData

        private val UUIDPlayer.jobData
            get() = jobDataCache.getOrPut(this) { PlayerData(this) }

        fun deleteCache(uuidPlayer: UUIDPlayer) {
            jobDataCache.remove(uuidPlayer)
        }

        fun clearCache() {
            jobDataCache.clear()
        }
    }
}