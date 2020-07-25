package me.syari.ss.job.grade

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobGrade.Companion.gradeList
import me.syari.ss.job.skill.active.ActiveSkill
import org.bukkit.Material

interface JobData {
    val id: String
    val display: String
    val icon: Material
    val description: String
    val availableWeaponType: Set<WeaponType>
    val requirements: Set<Requirement>?
    val activeSkill: Map<ActiveSkill.Type, ActiveSkill>

    companion object {
        val jobList = gradeList.flatMap { it.jobList.keys }

        fun getById(id: String): JobData? {
            return jobList.firstOrNull { it.id == id }
        }
    }
}