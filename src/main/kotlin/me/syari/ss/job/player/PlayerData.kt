package me.syari.ss.job.player

import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.job.DatabaseConnector
import me.syari.ss.job.data.JobData
import org.bukkit.OfflinePlayer

data class PlayerData(val uuidPlayer: UUIDPlayer) {
    var activeJob: PlayerJob?
        get() {
            return DatabaseConnector.ActiveJob.get(uuidPlayer)?.let { id ->
                JobData.getById(id)?.let {
                    PlayerJob(
                        this, it
                    )
                }
            }
        }
        set(value) {
            DatabaseConnector.ActiveJob.set(
                uuidPlayer, value?.data?.id
            )
        }

    fun getJob(data: JobData): PlayerJob {
        return PlayerJob(
            this, data
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

    fun isAvailableJob(jobData: JobData): Boolean {
        return getJob(jobData).isAvailable
    }

    fun updateExpBar() {
        uuidPlayer.player?.let { player ->
            activeJob?.let { activeJob ->
                player.sendExperienceChange(
                    activeJob.levelProgress, activeJob.level ?: 0
                )
            }
        }
    }

    companion object {
        val OfflinePlayer.jobData
            get() = UUIDPlayer(this).jobData

        val UUIDPlayer.jobData
            get() = PlayerData(this)
    }
}