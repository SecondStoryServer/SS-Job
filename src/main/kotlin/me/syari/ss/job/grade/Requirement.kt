package me.syari.ss.job.grade

import me.syari.ss.job.player.PlayerData

interface Requirement {
    val description: String
    fun has(playerData: PlayerData): Boolean

    class Job(private val jobData: JobData): Requirement {
        override val description = "${jobData.display} のレベルを最大にする"

        override fun has(playerData: PlayerData): Boolean {
            return playerData.getJob(jobData).isMaxLevel
        }
    }
}