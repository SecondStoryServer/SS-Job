package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.player.PlayerStatus
import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusType

data class PassiveSkill(
    val needLevel: Int,
    val statusType: StatusType,
    val value: Float,
    val changeType: StatusChange.Type,
    val extraSkill: Boolean = false
) {
    fun apply(level: Int, isActive: Boolean, playerStatus: PlayerStatus) {
        if ((isActive || extraSkill) && needLevel <= level) {
            playerStatus.add(
                StatusChange.Cause.PassiveSkill, statusType, value, changeType
            )
        }
    }
}