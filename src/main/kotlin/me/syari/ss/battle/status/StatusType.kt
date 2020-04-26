package me.syari.ss.battle.status

import me.syari.ss.battle.ElementType

sealed class StatusType {
    object BaseAttack : StatusType()
    object BaseDefense : StatusType()
    data class ElementAttack(val elementType: ElementType) : StatusType()
    data class ElementDefense(val elementType: ElementType) : StatusType()
    object CriticalDamage : StatusType()
    object CriticalChance : StatusType()
    object MaxDamage : StatusType()
    object MaxHealth : StatusType()
    object RegenHealth : StatusType()
    object MoveSpeed : StatusType()
}