package me.syari.ss.job.grade

import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobGrade.Companion.gradeList
import me.syari.ss.job.skill.active.JobActiveSkill
import me.syari.ss.job.skill.passive.JobPassiveSkill
import org.bukkit.Material

interface JobData {
    val id: String
    val display: String
    val icon: Material
    val description: String
    val availableWeaponType: Set<WeaponType>
    val requirements: Set<JobRequirement>?
    val activeSkill: List<JobActiveSkill>
    val passiveSkill: List<JobPassiveSkill>

    companion object {
        val jobList = gradeList.flatMap { it.jobList.keys }

        fun getById(id: String): JobData? {
            return jobList.firstOrNull { it.id == id }
        }
    }
}