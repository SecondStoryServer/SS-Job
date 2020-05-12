package me.syari.ss.job.grade.grade1

import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Priest: JobData {
    override val id = "priest"
    override val display = "プリースト"
    override val icon = Material.WOODEN_SHOVEL
    override val description = "人を癒し、戦闘をサポートするジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Mace
    )
    override val requirements = setOf(
        Requirement.Point(5)
    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {

    }
}