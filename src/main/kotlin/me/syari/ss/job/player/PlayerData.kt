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
                getJob(it)
            }
        }
        set(value) {
            DatabaseConnector.ActiveJob.set(
                uuidPlayer, value?.data
            )
        }

    private val allJob by lazy {
        DatabaseConnector.JobExp.getAll(uuidPlayer).keys.map {
            it to PlayerJobData(this, it)
        }.toMap().toMutableMap()
    }

    fun getJob(data: JobData): PlayerJobData {
        return allJob.getOrPut(data) { PlayerJobData(this, data) }
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

    fun updateMainPassiveSkill() {
        uuidPlayer.player?.status?.let { playerStatus ->
            playerStatus.clear(Cause.PassiveSkillMain)
            activeJob?.let { mainJob ->
                val level = mainJob.level
                if (mainJob.isAvailable) {
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
            allJob.values.forEach { playerJobData ->
                if (playerJobData.isAvailable) {
                    val level = playerJobData.level
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