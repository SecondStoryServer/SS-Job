package me.syari.ss.job.grade.grade1

import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.JobRequirement
import me.syari.ss.job.skill.active.JobActiveSkill
import me.syari.ss.job.skill.passive.JobPassiveSkill
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
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(

    )
    override val passiveSkill = listOf<JobPassiveSkill>(

    )
}