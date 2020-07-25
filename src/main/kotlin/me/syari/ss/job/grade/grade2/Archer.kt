package me.syari.ss.job.grade.grade2

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.grade.grade1.Mage
import me.syari.ss.job.grade.grade1.Ranger
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Archer: JobData {
    override val id = "archer"
    override val display = "アーチャー"
    override val icon = Material.BOW
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Bow
    )
    override val requirements = setOf(
        Requirement.Job(Ranger), Requirement.Job(Mage)
    )
    override val activeSkill = buildActiveSkill {

    }
}