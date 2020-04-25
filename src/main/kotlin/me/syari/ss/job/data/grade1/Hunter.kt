package me.syari.ss.job.data.grade1

import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
import org.bukkit.Material

object Hunter : JobData {
    override val id = "hunter"
    override val display = "ハンター"
    override val icon = Material.BOW
    override val description = """
        近距離・遠距離ともに扱え、
        トリッキーな立ち回りが出来るジョブ
    """.trimIndent()
    override val availableWeaponType = setOf(WeaponType.Bow, WeaponType.Knife)
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
}