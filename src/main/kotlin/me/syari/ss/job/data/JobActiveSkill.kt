package me.syari.ss.job.data

import org.bukkit.Material
import org.bukkit.entity.Player

interface JobActiveSkill {
    val icon: Material
    val display: String
    val description: String
    val coolTime: Int
    val needLevel: Int
    val extraSkill: Boolean
    fun use(player: Player)
}