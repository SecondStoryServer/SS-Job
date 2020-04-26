package me.syari.ss.battle.status

import me.syari.ss.battle.Main.Companion.battlePlugin
import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.core.scheduler.CustomScheduler.runLater
import org.bukkit.OfflinePlayer

data class PlayerStatus(val uuidPlayer: UUIDPlayer) {
    private val statusChangeList = mutableMapOf<StatusChangeCause, MutableList<StatusChange>>()

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
        val OfflinePlayer.status
            get() = PlayerStatus(UUIDPlayer(this))
    }
}