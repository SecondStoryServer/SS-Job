package me.syari.ss.job.data.list

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.data.JobData
import me.syari.ss.job.skill.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Priest: JobData {
    override val id = "priest"
    override val display = "プリースト"
    override val icon = Material.WOODEN_SHOVEL
    override val description = "人を癒し、戦闘をサポートするジョブ"
    override val defaultAvailable = true
    override val availableWeaponType = setOf(
        WeaponType.Mace
    )
    override val activeSkill = buildActiveSkill {

    }
}