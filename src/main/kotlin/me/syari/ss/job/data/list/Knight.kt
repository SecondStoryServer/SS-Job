package me.syari.ss.job.data.list

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.data.JobData
import me.syari.ss.job.skill.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Knight: JobData {
    override val id = "knight"
    override val display = "ナイト"
    override val icon = Material.IRON_CHESTPLATE
    override val description = "攻撃と防御のバランスが取れたジョブ"
    override val defaultAvailable = true
    override val availableWeaponType = setOf(
        WeaponType.Sword
    )
    override val activeSkill = buildActiveSkill {

    }
}