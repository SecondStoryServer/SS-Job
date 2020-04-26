package me.syari.ss.job.data.grade.grade1

import me.syari.ss.job.data.JobActiveSkill
import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
import org.bukkit.Material

object Priest : JobData {
    override val id = "priest"
    override val display = "プリースト"
    override val icon = Material.WOODEN_SHOVEL
    override val description = "人を癒し、戦闘をサポートするジョブ"
    override val availableWeaponType = setOf(WeaponType.Mace)
    override val requirements = setOf(
            JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(

    )
}