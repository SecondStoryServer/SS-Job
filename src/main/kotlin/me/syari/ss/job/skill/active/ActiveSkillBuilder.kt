package me.syari.ss.job.skill.active

class ActiveSkillBuilder {
    private val skills = mutableMapOf<ActiveSkill.Type, ActiveSkill>()

    fun set(type: ActiveSkill.Type, activeSkill: ActiveSkill) {
        skills[type] = activeSkill
    }

    fun build(): Map<ActiveSkill.Type, ActiveSkill> {
        return skills
    }

    companion object {
        fun buildActiveSkill(run: ActiveSkillBuilder.() -> Unit): Map<ActiveSkill.Type, ActiveSkill> {
            return ActiveSkillBuilder().apply(run).build()
        }
    }
}