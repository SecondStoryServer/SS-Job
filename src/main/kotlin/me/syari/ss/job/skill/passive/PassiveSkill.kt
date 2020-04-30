package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.player.PlayerStatus
import org.bukkit.Material

interface PassiveSkill {
    val icon: Material
    val display: String
    val needLevel: Int
    val extraSkill: Boolean
    fun apply(playerStatus: PlayerStatus)
}