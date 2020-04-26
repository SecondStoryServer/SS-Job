package me.syari.ss.job.data.skill.base

import me.syari.ss.battle.status.PlayerStatus.Companion.status
import me.syari.ss.battle.status.StatusChange
import me.syari.ss.battle.status.StatusChangeCause
import me.syari.ss.battle.status.StatusChangeType
import me.syari.ss.battle.status.StatusType
import me.syari.ss.job.data.JobActiveSkill
import org.bukkit.Material
import org.bukkit.entity.Player

class DamageUp(private val rank: Rank) : JobActiveSkill {
    override val icon = Material.BLAZE_POWDER
    override val display = "&c攻撃上昇&6〈${rank.display}〉"
    override val description = "自身の攻撃力を${String.format("%.1f", rank.damageUp)}倍に上昇させる"
    override val coolTime = rank.coolTime

    override fun use(player: Player) {
        val playerStatus = player.status
        playerStatus.add(
                StatusChangeCause.Skill,
                StatusChange(
                        StatusType.BaseAttack,
                        rank.damageUp,
                        StatusChangeType.Multi
                ),
                rank.effectTime
        )
    }

    enum class Rank(val display: String, val damageUp: Float, val effectTime: Int, val coolTime: Int) {
        Small("小", 1.5F, 40, 3 * 60 * 20),
        Medium("中", 2.0F, 40, 3 * 60 * 20),
        Large("大", 3.0F, 40, 3 * 60 * 20)
    }
}