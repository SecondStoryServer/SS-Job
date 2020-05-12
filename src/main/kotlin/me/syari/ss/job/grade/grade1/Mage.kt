package me.syari.ss.job.grade.grade1

import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Mage: JobData {
    override val id = "mage"
    override val display = "メイジ"
    override val icon = Material.WOODEN_HOE
    override val description = "遠距離から魔法を放つジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Wand
    )
    override val requirements = setOf(
        Requirement.Point(5)
    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {

    }
}