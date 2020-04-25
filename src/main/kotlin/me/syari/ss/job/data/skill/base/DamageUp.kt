package me.syari.ss.job.data.skill.base

import me.syari.ss.job.data.JobActiveSkill
import org.bukkit.Material
import org.bukkit.entity.Player

class DamageUp(private val rank: Rank): JobActiveSkill {
    override val icon = Material.BLAZE_POWDER
    override val display = "&c攻撃上昇&6〈${rank.display}〉"
    override val description = "自身の攻撃力を上昇させる"
    override val coolTime = rank.coolTime

    override fun use(player: Player){
        TODO()
    }

    enum class Rank(val display: String, val damageUp: Float, val coolTime: Int) {
        Small("小", 1.5F, 50),
        Medium("中", 2.0F, 50),
        Large("大", 3.0F, 50)
    }
}