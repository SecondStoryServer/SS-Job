package me.syari.ss.job.grade.grade1

import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.JobRequirement
import me.syari.ss.job.skill.active.JobActiveSkill
import me.syari.ss.job.skill.passive.JobPassiveSkill
import org.bukkit.Material

object Fighter: JobData {
    override val id = "fighter"
    override val display = "ファイター"
    override val icon = Material.BLACK_DYE
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Knuckle
    )
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(

    )
    override val passiveSkill = listOf<JobPassiveSkill>(

    )
}