package me.syari.ss.job.grade

import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobGrade.Companion.gradeList
import me.syari.ss.job.player.PlayerData
import me.syari.ss.job.player.PlayerJobData
import me.syari.ss.job.skill.active.ActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkill
import org.bukkit.Material

interface JobData {
    val id: String
    val display: String
    val icon: Material
    val description: String
    val availableWeaponType: Set<WeaponType>
    val requirements: Set<Requirement>?
    val activeSkill: List<ActiveSkill>
    val passiveSkill: List<PassiveSkill>

    fun get(playerData: PlayerData) = PlayerJobData(playerData, this)

    companion object {
        val jobList = gradeList.flatMap { it.jobList.keys }

        fun getById(id: String): JobData? {
            return jobList.firstOrNull { it.id == id }
        }
    }
}