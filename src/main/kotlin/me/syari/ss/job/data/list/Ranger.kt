package me.syari.ss.job.data.list

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.data.JobData
import me.syari.ss.job.skill.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Ranger: JobData {
    override val id = "ranger"
    override val display = "レンジャー"
    override val icon = Material.BOW
    override val description = """
        近距離・遠距離ともに扱え、
        トリッキーな立ち回りが出来るジョブ
    """.trimIndent()
    override val defaultAvailable = true
    override val availableWeaponType = setOf(
        WeaponType.Bow, WeaponType.Knife
    )
    override val activeSkill = buildActiveSkill {

    }
}