package me.syari.ss.job.skill

import org.bukkit.Material
import org.bukkit.entity.Player

interface ActiveSkill {
    val icon: Material
    val display: String
    val description: String
    val coolTime: Int
    fun use(player: Player)

    enum class Type(val toString: String) {
        RightMouseButton("RMB"),
        KeyQ("Q"),
        KeyF("F");

        override fun toString(): String {
            return toString
        }
    }
}