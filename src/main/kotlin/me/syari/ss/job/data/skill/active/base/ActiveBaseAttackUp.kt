package me.syari.ss.job.data.skill.active.base

import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusType
import org.bukkit.Material

class ActiveBaseAttackUp(
    rank: Rank
): ActiveStatusUp(
    StatusType.BaseAttack,
    Material.BLAZE_POWDER,
    rank.percent,
    StatusChange.Type.Multi,
    1,
    true,
    rank.effectTime,
    rank.coolTime,
    rank.display,
    "+${rank.percent * 100}%"
) {
    enum class Rank(
        val display: String, val percent: Float, val effectTime: Int, val coolTime: Int
    ) {
        Small(
            "小", 1.5F, 30 * 20, 2 * 60 * 20
        )
    }
}