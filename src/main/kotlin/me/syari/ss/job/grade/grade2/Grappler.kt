package me.syari.ss.job.grade.grade2

import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.grade.grade1.Fighter
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Grappler: JobData {
    override val id = "grappler"
    override val display = "グラップラー"
    override val icon = Material.BLACK_DYE
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Knuckle
    )
    override val requirements = setOf(
        Requirement.Job(Fighter)
    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {

    }
}