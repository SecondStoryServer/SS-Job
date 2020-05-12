package me.syari.ss.job.grade.grade2

import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.grade.grade1.Knight
import me.syari.ss.job.grade.grade1.Priest
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Paladin: JobData {
    override val id = "paladin"
    override val display = "パラディン"
    override val icon = Material.GOLDEN_CHESTPLATE
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Sword
    )
    override val requirements = setOf(
        Requirement.Point(5), Requirement.Job(Knight), Requirement.Job(Priest)
    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {

    }
}