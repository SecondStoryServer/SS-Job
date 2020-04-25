package me.syari.ss.job.data.grade.grade1

import me.syari.ss.job.data.JobActiveSkill
import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
import me.syari.ss.job.data.skill.base.DamageUp
import org.bukkit.Material

object Warrior : JobData {
    override val id = "warrior"
    override val display = "ウォーリア"
    override val icon = Material.IRON_SWORD
    override val description = "高い攻撃力で戦闘を行うジョブ"
    override val availableWeaponType = setOf(WeaponType.Sword, WeaponType.Axe)
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(
        DamageUp(DamageUp.Rank.Small)
    )
}