package me.syari.ss.battle.status
import me.syari.ss.core.scheduler.CustomTask

data class StatusChange(
        val statusType: StatusType,
        val value: Float,
        val changeType: StatusChangeType
) {
    val removeTask = mutableSetOf<CustomTask>()

    fun cancelAllTask() {
        removeTask.forEach {
            it.cancel()
        }
    }
}