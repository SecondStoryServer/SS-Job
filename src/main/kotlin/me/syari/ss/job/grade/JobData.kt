package me.syari.ss.job.grade

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.battle.Bard
import me.syari.ss.job.grade.battle.Fighter
import me.syari.ss.job.grade.battle.Knight
import me.syari.ss.job.grade.battle.Mage
import me.syari.ss.job.grade.battle.Priest
import me.syari.ss.job.grade.battle.Ranger
import me.syari.ss.job.grade.battle.Warrior
import me.syari.ss.job.skill.active.ActiveSkill
import org.bukkit.Material

interface JobData {
    val id: String
    val display: String
    val icon: Material
    val description: String
    val availableWeaponType: Set<WeaponType>
    val activeSkill: Map<ActiveSkill.Type, ActiveSkill>

    companion object {
        val jobList = listOf(
            Bard,
            Fighter,
            Knight,
            Mage,
            Priest,
            Ranger,
            Warrior
        )

        fun getById(id: String): JobData? {
            return jobList.firstOrNull { it.id == id }
        }
    }
}