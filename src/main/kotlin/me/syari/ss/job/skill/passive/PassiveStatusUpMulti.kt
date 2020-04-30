package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusType
import org.bukkit.Material

open class PassiveStatusUpMulti(
    statusType: StatusType,
    override val icon: Material,
    percent: Float,
    override val needLevel: Int,
    override val extraSkill: Boolean
): PassiveStatusUp(
    statusType, icon, percent, StatusChange.Type.Multi, needLevel, extraSkill, "+${percent * 100}%"
)