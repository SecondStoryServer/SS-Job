package me.syari.ss.battle.status

import me.syari.ss.battle.ElementType
import me.syari.ss.core.scheduler.CustomTask

sealed class StatusChange {
    val removeTask = mutableSetOf<CustomTask>()

    fun cancelAllTask() {
        removeTask.forEach {
            it.cancel()
        }
    }

    class Base(
            val statusType: Type,
            val value: Float,
            val changeType: StatusChangeType
    ) : StatusChange() {
        enum class Type(val dependencyStatusType: StatusType) {
            Attack(StatusType.BaseAttack),
            Defense(StatusType.BaseDefense),
            CriticalDamage(StatusType.CriticalDamage),
            CriticalChance(StatusType.CriticalChance),
            MaxDamage(StatusType.MaxDamage),
            MaxHealth(StatusType.MaxHealth),
            RegenHealth(StatusType.RegenHealth),
            MoveSpeed(StatusType.MoveSpeed),
        }
    }

    class Element(
            val element: ElementType,
            val statusType: Type,
            val value: Float,
            val changeType: StatusChangeType
    ) : StatusChange() {
        enum class Type(val dependencyStatusType: StatusType) {
            Attack(StatusType.ElementAttack),
            Defense(StatusType.ElementDefense)
        }
    }
}