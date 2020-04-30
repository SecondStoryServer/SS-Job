package me.syari.ss.job.player

import me.syari.ss.job.DatabaseConnector
import me.syari.ss.job.grade.JobData

data class PlayerJob(
    val playerData: PlayerData, val data: JobData
) {
    var exp: Int?
        get() {
            return DatabaseConnector.JobExp.get(
                playerData.uuidPlayer, data.id
            )
        }
        set(value) {
            DatabaseConnector.JobExp.set(
                playerData.uuidPlayer, data.id, value
            )
            playerData.updateExpBar()
        }

    val level
        get() = getLevelFromExp(exp)

    val levelProgress
        get(): Float {
            return level?.let { notNullLevel ->
                exp?.let { notNullExp ->
                    val beginExp = getExpFromLevel(notNullLevel - 1)
                    val lastExp = if (isMaxLevel) notNullExp else getExpFromLevel(notNullLevel)
                    (notNullExp - beginExp) / (lastExp - beginExp).toFloat()
                }
            } ?: 0F
        }

    inline val isMaxLevel
        get() = level?.let { maxLevel == it } ?: false

    inline val isAvailable
        get() = level != null

    inline val isActive: Boolean
        get() = playerData.activeJob == this

    val canGet
        get() = data.requirements?.all { it.has(playerData) } ?: true

    companion object {
        var maxLevel = 30
        private var expToLevelTable: List<Int>
        private var expToLevelTableReversed: List<Int>

        init {
            val expToLevelTable = mutableListOf<Int>()
            for (level in 1 until 16) {
                expToLevelTable.add(level * level + 6 * level)
            }
            for (level in 16..30) {
                expToLevelTable.add((2.5 * level * level - 40.5 * level + 360).toInt())
            }
            this.expToLevelTable = expToLevelTable
            expToLevelTableReversed = expToLevelTable.reversed()
            maxLevel = expToLevelTable.size
        }

        private fun getLevelFromExp(exp: Int?): Int? {
            if (exp == null) return null
            val index = expToLevelTableReversed.indexOfFirst { it <= exp }
            return if (index == -1) 0 else maxLevel - index
        }

        private fun getExpFromLevel(level: Int): Int {
            return if (level < 0) 0 else expToLevelTable[level]
        }
    }
}