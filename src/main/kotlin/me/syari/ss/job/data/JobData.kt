package me.syari.ss.job.data

import me.syari.ss.job.data.JobGrade.Companion.gradeList
import org.bukkit.Material

interface JobData {
    val id: String
    val display: String
    val icon: Material
    val description: String
    val availableWeaponType: Set<WeaponType>
    val requirements: Set<JobRequirement>?
    val activeSkill: List<JobActiveSkill>

    companion object {
        val jobList = gradeList.flatMap { it.jobList.keys }

        fun getById(id: String): JobData? {
            return jobList.firstOrNull { it.id == id }
        }
    }
}