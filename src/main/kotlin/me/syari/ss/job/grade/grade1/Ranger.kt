package me.syari.ss.job.grade.grade1

import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.JobRequirement
import me.syari.ss.job.skill.active.ActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkill
import org.bukkit.Material

object Ranger: JobData {
    override val id = "ranger"
    override val display = "レンジャー"
    override val icon = Material.BOW
    override val description = """
        近距離・遠距離ともに扱え、
        トリッキーな立ち回りが出来るジョブ
    """.trimIndent()
    override val availableWeaponType = setOf(
        WeaponType.Bow, WeaponType.Knife
    )
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<ActiveSkill>(

    )
    override val passiveSkill = listOf<PassiveSkill>(

    )
}