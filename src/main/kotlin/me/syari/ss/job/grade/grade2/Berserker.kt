package me.syari.ss.job.grade.grade2

import me.syari.ss.item.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.grade.grade1.Fighter
import me.syari.ss.job.grade.grade1.Warrior
import me.syari.ss.job.skill.active.ActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkill
import org.bukkit.Material

object Berserker: JobData {
    override val id = "berserker"
    override val display = "バーサーカー"
    override val icon = Material.IRON_AXE
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Axe
    )
    override val requirements = setOf(
        Requirement.Point(5), Requirement.Job(Warrior), Requirement.Job(Fighter)
    )
    override val activeSkill = listOf<ActiveSkill>(

    )
    override val passiveSkill = listOf<PassiveSkill>(

    )
}