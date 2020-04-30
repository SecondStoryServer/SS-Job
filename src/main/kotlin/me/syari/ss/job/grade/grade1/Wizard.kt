package me.syari.ss.job.grade.grade1

import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.JobRequirement
import me.syari.ss.job.skill.active.JobActiveSkill
import me.syari.ss.job.skill.passive.JobPassiveSkill
import org.bukkit.Material

object Wizard: JobData {
    override val id = "wizard"
    override val display = "ウィザード"
    override val icon = Material.WOODEN_HOE
    override val description = "遠距離から魔法を放つジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Wand
    )
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(

    )
    override val passiveSkill = listOf<JobPassiveSkill>(

    )
}