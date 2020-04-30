package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusType
import org.bukkit.Material

open class PassiveStatusUpAdd(
    statusType: StatusType,
    override val icon: Material,
    add: Float,
    override val needLevel: Int,
    override val extraSkill: Boolean = false
): PassiveStatusUp(
    statusType, icon, add, StatusChange.Type.Add, needLevel, extraSkill, "+$add"
)