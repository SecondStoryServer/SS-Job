package me.syari.ss.job.skill.active.base

import me.syari.ss.battle.status.player.PlayerStatus.Companion.status
import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusType
import me.syari.ss.job.skill.active.ActiveSkill
import org.bukkit.Material
import org.bukkit.entity.Player

open class ActiveStatusUp(
    private val statusType: StatusType,
    override val icon: Material,
    private val value: Float,
    private val changeType: StatusChange.Type,
    override val needLevel: Int,
    override val extraSkill: Boolean,
    private val effectTime: Int,
    override val coolTime: Int,
    amountText: String,
    upText: String
): ActiveSkill {
    override val display = "${statusType.display} 上昇 〈${amountText}〉"
    override val description = "自身の ${statusType.display} を $upText 上昇させる"

    override fun use(player: Player) {
        player.status.add(
            StatusChange.Cause.ActiveSkill, statusType, value, changeType, effectTime
        )
    }
}