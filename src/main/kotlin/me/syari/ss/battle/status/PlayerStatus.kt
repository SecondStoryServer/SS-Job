package me.syari.ss.battle.status

import me.syari.ss.battle.Main.Companion.battlePlugin
import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.core.scheduler.CustomScheduler.runLater
import org.bukkit.OfflinePlayer

class PlayerStatus {
    private val statusChangeList = mutableMapOf<StatusChangeCause, MutableList<StatusChange>>()

    fun get(): Map<StatusType, Float> {
        val status = defaultStatus.toMutableMap()
        val multi = mutableMapOf<StatusType, Float>()
        statusChangeList.values.forEach { list ->
            list.forEach { statusChange ->
                fun MutableMap<StatusType, Float>.increase(type: StatusType, value: Float) {
                    put(type, getOrDefault(type, 0F) + value)
                }

                when (statusChange.changeType) {
                    StatusChangeType.Add -> status
                    StatusChangeType.Multi -> multi
                }.increase(statusChange.statusType, statusChange.value)
            }
        }
        multi.forEach { (type, value) ->
            status[type]?.let {
                status[type] = it * value
            }
        }
        return status
    }

    fun add(cause: StatusChangeCause, data: StatusChange) {
        statusChangeList.getOrPut(cause) { mutableListOf() }.add(data)
    }

    fun add(cause: StatusChangeCause, data: StatusChange, effectTime: Int) {
        add(cause, data)
        runLater(battlePlugin, effectTime.toLong()) {
            statusChangeList[cause]?.remove(data)
        }?.let { data.removeTask.add(it) }
    }

    fun clear(cause: StatusChangeCause) {
        statusChangeList[cause]?.forEach { it.cancelAllTask() }
        statusChangeList.clear()
    }

    companion object {
        private val defaultStatus = mapOf(
                StatusType.BaseAttack to 1F,
                StatusType.MaxDamage to 1F,
                StatusType.MaxHealth to 20F,
                StatusType.RegenHealth to 1F,
                StatusType.MoveSpeed to 0.2F
        )

        private val statusMap = mutableMapOf<UUIDPlayer, PlayerStatus>()

        val OfflinePlayer.status
            get(): PlayerStatus {
                val uuidPlayer = UUIDPlayer(this)
                return statusMap.getOrPut(uuidPlayer) { PlayerStatus() }
            }
    }
}