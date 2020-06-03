package me.syari.ss.job.grade.grade2

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.grade.grade1.Mage
import me.syari.ss.job.grade.grade1.Priest
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Bishop: JobData {
    override val id = "bishop"
    override val display = "ビショップ"
    override val icon = Material.WOODEN_HOE
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Mace
    )
    override val requirements = setOf(
        Requirement.Job(Priest), Requirement.Job(Mage)
    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {

    }
}