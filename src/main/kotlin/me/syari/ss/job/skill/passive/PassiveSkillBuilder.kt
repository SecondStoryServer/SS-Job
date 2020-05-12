package me.syari.ss.job.skill.passive

import me.syari.ss.battle.status.player.StatusChange
import me.syari.ss.battle.status.player.StatusType

class PassiveSkillBuilder {
    private val list = mutableListOf<PassiveSkill>()

    fun add(
        needLevel: Int, statusType: StatusType, value: Float, changeType: StatusChange.Type, extraSkill: Boolean = false
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