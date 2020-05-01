package me.syari.ss.job.player

import me.syari.ss.battle.status.player.PlayerStatus.Companion.status
import me.syari.ss.battle.status.player.StatusChange.Cause
import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.job.DatabaseConnector
import me.syari.ss.job.grade.JobData
import org.bukkit.OfflinePlayer

data class PlayerData(val uuidPlayer: UUIDPlayer) {
    var activeJob: PlayerJobData?
        get() {
            return DatabaseConnector.ActiveJob.get(uuidPlayer)?.let {
                PlayerJobData(
                    this, it
                )
            }
        }
        set(value) {
            DatabaseConnector.ActiveJob.set(
                uuidPlayer, value?.data
            )
        }

    val allJob: List<PlayerJobData>
        get() {
            return DatabaseConnector.JobExp.getAll(uuidPlayer).keys.map { it.get(this) }
        }

    fun getJob(data: JobData): PlayerJobData {
        return data.get(this)
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

    fun updateMainPassiveSkill() {
        uuidPlayer.player?.status?.let { playerStatus ->
            playerStatus.clear(Cause.PassiveSkillMain)
            activeJob?.let { mainJob ->
                val level = mainJob.level
                if (level != null) {
                    mainJob.data.passiveSkill.forEach { passiveSkill ->
                        passiveSkill.apply(level, true, playerStatus)
                    }
                }
            }
        }
    }

    fun updateAllPassiveSkill() {
        uuidPlayer.player?.let { player ->
            val playerStatus = player.status
            playerStatus.clear(Cause.PassiveSkillMain)
            playerStatus.clear(Cause.PassiveSkillExtra)
            allJob.forEach { playerJobData ->
                val level = playerJobData.level
                if (level != null) {
                    val isActive = playerJobData.isActive
                    playerJobData.data.passiveSkill.forEach { passiveSkill ->
                        passiveSkill.apply(level, isActive, playerStatus)
                    }
                }
            }
        }
    }

    fun reloadExp() {
        DatabaseConnector.JobExp.reloadAll(uuidPlayer)
    }

    companion object {
        val OfflinePlayer.jobData
            get() = UUIDPlayer(this).jobData

        val UUIDPlayer.jobData
            get() = PlayerData(this)
    }
}