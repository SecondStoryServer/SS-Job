package me.syari.ss.job.grade.battle

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Knight: JobData {
    override val id = "knight"
    override val display = "ナイト"
    override val icon = Material.IRON_CHESTPLATE
    override val description = "攻撃と防御のバランスが取れたジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Sword
    )
    override val requirements = setOf<Requirement>(

    )
    override val activeSkill = buildActiveSkill {

    }
}