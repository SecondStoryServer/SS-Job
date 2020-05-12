package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.player.PlayerStatus
import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusChange.Cause
import me.syari.ss.battle.status.player.StatusType

data class PassiveSkill(
    val needLevel: Int,
    val statusType: StatusType,
    val value: Float,
    val changeType: StatusChange.Type,
    val extraSkill: Boolean
) {
    fun apply(level: Int, isActive: Boolean, playerStatus: PlayerStatus) {
        if (isAvailable(level)) {
            val cause = when {
                isActive -> Cause.PassiveSkillMain
                extraSkill -> Cause.PassiveSkillExtra
                else -> return
            }
            playerStatus.add(
                cause, statusType, value, changeType
            )
        }
    }

    fun isAvailable(level: Int): Boolean {
        return needLevel <= level
    }
}