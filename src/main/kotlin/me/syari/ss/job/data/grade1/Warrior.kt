package me.syari.ss.job.data.grade1

import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
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
}