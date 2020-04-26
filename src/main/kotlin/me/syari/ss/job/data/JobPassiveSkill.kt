package me.syari.ss.job.data

import me.syari.ss.battle.status.player.PlayerStatus
import org.bukkit.Material

interface JobPassiveSkill {
    val icon: Material
    val display: String
    val description: String
    val needLevel: Int
    val extraSkill: Boolean
    fun apply(playerStatus: PlayerStatus)
}