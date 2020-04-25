package me.syari.ss.job.data.grade1

import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
import org.bukkit.Material

object Knight : JobData {
    override val id = "knight"
    override val display = "ナイト"
    override val icon = Material.IRON_CHESTPLATE
    override val description = "攻撃と防御のバランスが取れたジョブ"
    override val availableWeaponType = setOf(WeaponType.Sword)
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
}