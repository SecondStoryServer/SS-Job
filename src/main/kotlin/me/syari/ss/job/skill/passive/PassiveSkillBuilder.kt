package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.StatusType
import me.syari.ss.battle.status.player.StatusChange

class PassiveSkillBuilder {
    private val list = mutableListOf<PassiveSkill>()

    fun add(
        needLevel: Int,
        statusType: StatusType,
        value: Float,
        changeType: StatusChange.Type,
        extraSkill: Boolean = false
    ) {
        add(needLevel, listOf(statusType), value, changeType, extraSkill)
    }

    fun add(
        needLevel: Int,
        statusType: List<StatusType>,
        value: Float,
        changeType: StatusChange.Type,
        extraSkill: Boolean = false
    ) {
        list.add(PassiveSkill(needLevel, statusType, value, changeType, extraSkill))
    }

    fun build(): List<PassiveSkill> {
        return list
    }

    companion object {
        fun buildPassiveSkill(run: PassiveSkillBuilder.() -> Unit): List<PassiveSkill> {
            return PassiveSkillBuilder().apply(run).build()
        }
    }
}