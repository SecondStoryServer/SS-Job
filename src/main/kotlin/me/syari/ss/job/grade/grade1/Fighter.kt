package me.syari.ss.job.grade.grade1

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Fighter: JobData {
    override val id = "fighter"
    override val display = "ファイター"
    override val icon = Material.BLACK_DYE
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Knuckle
    )
    override val requirements = setOf<Requirement>(

    )
    override val activeSkill = buildActiveSkill {

    }
}