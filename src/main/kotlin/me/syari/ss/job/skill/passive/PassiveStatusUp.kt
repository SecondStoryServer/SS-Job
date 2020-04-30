package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.player.PlayerStatus
import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusType
import org.bukkit.Material

open class PassiveStatusUp(
    private val statusType: StatusType,
    override val icon: Material,
    private val value: Float,
    private val changeType: StatusChange.Type,
    override val needLevel: Int,
    override val extraSkill: Boolean,
    upText: String
): PassiveSkill {
    override val display = "${statusType.display} 上昇 $upText"

    override fun apply(playerStatus: PlayerStatus) {
        playerStatus.add(
            StatusChange.Cause.PassiveSkill, statusType, value, changeType
        )
    }
}