package me.syari.ss.job.data.list

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.data.JobData
import me.syari.ss.job.skill.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Mage: JobData {
    override val id = "mage"
    override val display = "メイジ"
    override val icon = Material.WOODEN_HOE
    override val description = "遠距離から魔法を放つジョブ"
    override val defaultAvailable = true
    override val availableWeaponType = setOf(
        WeaponType.Wand
    )
    override val activeSkill = buildActiveSkill {

    }
}