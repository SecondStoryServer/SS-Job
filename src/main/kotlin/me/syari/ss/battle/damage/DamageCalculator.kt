package me.syari.ss.battle.damage

import me.syari.ss.battle.equipment.ElementType
import me.syari.ss.battle.status.player.StatusType
import kotlin.random.Random

object DamageCalculator {
    fun attack(attacker: DamageEventEntity, victim: DamageEventEntity) {
        val damageElementType = attacker.status.damageElementType
        val attackerStatus = attacker.status.get()
        val victimStatus = victim.status.get()
        var damage = if (damageElementType != null) {
            getAttack(damageElementType, attackerStatus) - getDefense(damageElementType, victimStatus)
        } else {
            getAttack(attackerStatus) ?: 0F
        }
        if (0F < damage) {
            attackerStatus[StatusType.CriticalChance]?.let { chance ->
                if (Random.nextFloat() < chance) {
                    damage *= 1.5F
                }
            }
            attackerStatus[StatusType.MaxDamage]?.let { maxDamage ->
                if (maxDamage < damage) {
                    damage = maxDamage
                }
            }
            victim.entity.damage(damage.toDouble(), attacker.entity)
        }
    }

    private fun getDefense(damageElementType: ElementType, victimStatus: Map<StatusType, Float>): Float {
        var defense = 0F
        victimStatus.forEach { (type, value) ->
            if (type is StatusType.ElementDefense) {
                defense += value * ElementType.getDefenseRate(damageElementType, type.elementType)
            } else if (type is StatusType.BaseDefense) {
                defense += value
            }
        }
        return defense / 5F
    }

    private fun getAttack(attackerStatus: Map<StatusType, Float>): Float? {
        return attackerStatus[StatusType.BaseAttack]
    }

    private fun getAttack(damageElementType: ElementType, attackerStatus: Map<StatusType, Float>): Float {
        var attack = 0F
        getAttack(attackerStatus)?.let {
            attack += it
        }
        attackerStatus[StatusType.ElementAttack(damageElementType)]?.let {
            attack += it
        }
        return attack / 2F
    }
}