package me.syari.ss.job.data.grade.grade1

import me.syari.ss.job.data.JobActiveSkill
import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
import org.bukkit.Material

object Wizard : JobData {
    override val id = "wizard"
    override val display = "ウィザード"
    override val icon = Material.WOODEN_HOE
    override val description = "遠距離から魔法を放つジョブ"
    override val availableWeaponType = setOf(WeaponType.Wand)
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(

    )
}