package me.syari.ss.job.grade.grade2

import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.grade.grade1.Bard
import me.syari.ss.job.grade.grade1.Ranger
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Thief: JobData {
    override val id = "thief"
    override val display = "シーフ"
    override val icon = Material.LEATHER_BOOTS
    override val description = ""
    override val availableWeaponType = setOf<WeaponType>(

    )
    override val requirements = setOf(
        Requirement.Job(Ranger), Requirement.Job(Bard)
    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {

    }
}