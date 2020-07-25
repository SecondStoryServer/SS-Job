package me.syari.ss.job.grade.battle

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import org.bukkit.Material

object Bard: JobData {
    override val id = "bard"
    override val display = "バード"
    override val icon = Material.MUSIC_DISC_CAT
    override val description = ""
    override val availableWeaponType = setOf(
        WeaponType.Harp
    )
    override val activeSkill = buildActiveSkill {

    }
}