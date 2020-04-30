package me.syari.ss.job.grade

import me.syari.ss.job.player.PlayerData

interface JobRequirement {
    val description: String
    fun has(playerData: PlayerData): Boolean
    fun use(playerData: PlayerData)

    class Job(private val id: String): JobRequirement {
        val data by lazy { JobData.getById(id) }

        override val description = "${data?.display} のレベルを最大にする"

        override fun has(playerData: PlayerData): Boolean {
            return data?.let { playerData.getJob(it).isMaxLevel } ?: false
        }

        override fun use(playerData: PlayerData) {}
    }

    class Point(private val point: Int): JobRequirement {
        override val description = "ジョブポイントを $point 使用する"

        override fun has(playerData: PlayerData): Boolean {
            return playerData.hasJobPoint(point)
        }

        override fun use(playerData: PlayerData) {
            playerData.jobPoint -= point
        }
    }
}