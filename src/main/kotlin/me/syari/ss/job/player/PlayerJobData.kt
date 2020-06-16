package me.syari.ss.job.player

import me.syari.ss.job.DatabaseConnector
import me.syari.ss.job.grade.JobData

data class PlayerJobData(
    val playerData: PlayerData,
    val data: JobData
) {
    var level = getLevelFromExp(exp)
    private var expToLevel = getExpFromLevel(level - 1)
    private var expToNextLevel = if (isMaxLevel) exp else getExpFromLevel(level)

    private val nullableExp: Int?
        get() {
            return DatabaseConnector.JobExp.get(
                playerData.uuidPlayer, data
            )
        }

    var exp: Int
        get() {
            return nullableExp ?: 0
        }
        set(value) {
            DatabaseConnector.JobExp.set(
                playerData.uuidPlayer, data, value
            )
            playerData.updateExpBar()
            if (expToNextLevel <= value) {
                level++
                expToLevel = expToNextLevel
                expToNextLevel = if (isMaxLevel) exp else getExpFromLevel(level)
            }
        }

    val levelProgress
        get() = (exp - expToLevel) / (expToNextLevel - expToLevel).toFloat()

    inline val isMaxLevel
        get() = maxLevel == level

    inline val isActive: Boolean
        get() = playerData.activeJob == this

    val canGet
        get() = data.requirements?.all { it.has(playerData) } ?: true

    val isAvailable
        get() = nullableExp != null

    companion object {
        val maxLevel: Int
        private val expToLevelTable: List<Int>
        private val expToLevelTableReversed: List<Int>

        init {
            val expToLevelTable = mutableListOf<Int>()
            for (level in 1 until 16) {
                expToLevelTable.add(level * level + 6 * level)
            }
            for (level in 16 until 31) {
                expToLevelTable.add((2.5 * level * level - 40.5 * level + 360).toInt())
            }
            this.expToLevelTable = expToLevelTable
            expToLevelTableReversed = expToLevelTable.reversed()
            maxLevel = expToLevelTable.size
        }

        private fun getLevelFromExp(exp: Int): Int {
            val index = expToLevelTableReversed.indexOfFirst { it <= exp }
            return if (index == -1) 0 else maxLevel - index
        }

        private fun getExpFromLevel(level: Int): Int {
            return if (level < 0) 0 else expToLevelTable[level]
        }
    }
}