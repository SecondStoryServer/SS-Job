package me.syari.ss.job.skill.active

class ActiveSkillBuilder {
    private val list = mutableListOf<ActiveSkill>()

    fun add(activeSkill: ActiveSkill) {
        list.add(activeSkill)
    }

    fun build(): List<ActiveSkill> {
        return list
    }

    companion object {
        fun buildActiveSkill(run: ActiveSkillBuilder.() -> Unit): List<ActiveSkill> {
            return ActiveSkillBuilder().apply(run).build()
        }
    }
}