package me.syari.ss.job.skill.active

import org.bukkit.Material
import org.bukkit.entity.Player

interface ActiveSkill {
    val icon: Material
    val display: String
    val description: String
    val coolTime: Int
    val needLevel: Int
    val extraSkill: Boolean
    fun use(player: Player)

    data class Type(val first: ClickType, val second: ClickType, val third: ClickType) {
        override fun toString(): String {
            return "$first $second $third"
        }

        companion object {
            val RightRightRight = Type(ClickType.Right, ClickType.Right, ClickType.Right)
            val RightLeftRight = Type(ClickType.Left, ClickType.Left, ClickType.Right)
            val RightLeftLeft = Type(ClickType.Right, ClickType.Left, ClickType.Left)
        }
    }

    enum class ClickType(val toString: String) {
        Right("R"),
        Left("L");

        override fun toString(): String {
            return toString
        }
    }
}