package me.syari.ss.job.skill.list

import me.syari.ss.battle.status.StatusType
import me.syari.ss.battle.status.player.PlayerStatus.Companion.status
import me.syari.ss.battle.status.player.StatusChange.Cause
import me.syari.ss.battle.status.player.StatusChange.Type
import me.syari.ss.job.skill.ActiveSkill
import org.bukkit.Material
import org.bukkit.entity.Player

object ActiveBerserk: ActiveSkill {
    override val icon = Material.IRON_AXE
    override val display = "バーサーク"
    override val description = "動きが遅くなる代わりに攻撃力が +300% 上昇させる"
    override val coolTime = 3 * 60 * 20

    override fun use(player: Player) {
        val playerStatus = player.status
        playerStatus.add(
            Cause.ActiveSkill, StatusType.allAttack, 3.0F, Type.Multi, 20 * 20
        )
        playerStatus.add(
            Cause.ActiveSkill, StatusType.MoveSpeed, -0.08F, Type.Add, 20 * 20
        )
    }
}