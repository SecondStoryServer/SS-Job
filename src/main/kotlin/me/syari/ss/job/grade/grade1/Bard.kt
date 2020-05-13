package me.syari.ss.job.grade.grade1

import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Bard: JobData {
    override val id = "bard"
    override val display = "バード"
    override val icon = Material.MUSIC_DISC_CAT
    override val description = ""
    override val availableWeaponType = setOf<WeaponType>(

    )
    override val requirements = setOf<Requirement>(

    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {

    }
}