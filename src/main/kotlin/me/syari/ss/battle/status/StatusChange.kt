package me.syari.ss.battle.status

import me.syari.ss.core.scheduler.CustomTask
import java.lang.annotation.ElementType

data class StatusChange(
    val element: ElementType?,
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