package me.syari.ss.job.grade

import me.syari.ss.job.player.PlayerData

interface Requirement {
    val description: String
    fun has(playerData: PlayerData): Boolean
    fun use(playerData: PlayerData)

    class Job(private val jobData: JobData): Requirement {
        override val description = "${jobData.display} のレベルを最大にする"

        override fun has(playerData: PlayerData): Boolean {
            return playerData.getJob(jobData).isMaxLevel
        }

        override fun use(playerData: PlayerData) {}
    }

    class Point(private val point: Int): Requirement {
        override val description = "ジョブポイントを $point 使用する"

        override fun has(playerData: PlayerData): Boolean {
            return playerData.hasJobPoint(point)
        }

        override fun use(playerData: PlayerData) {
            playerData.jobPoint -= point
        }
    }
}