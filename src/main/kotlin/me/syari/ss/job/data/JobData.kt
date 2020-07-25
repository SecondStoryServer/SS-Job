package me.syari.ss.job.data

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.data.list.Bard
import me.syari.ss.job.data.list.Fighter
import me.syari.ss.job.data.list.Knight
import me.syari.ss.job.data.list.Mage
import me.syari.ss.job.data.list.Priest
import me.syari.ss.job.data.list.Ranger
import me.syari.ss.job.data.list.Warrior
import me.syari.ss.job.skill.ActiveSkill
import org.bukkit.Material

interface JobData {
    val id: String
    val display: String
    val icon: Material
    val description: String
    val defaultAvailable: Boolean
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