package me.syari.ss.battle.damage

import me.syari.ss.battle.status.EntityStatus
import me.syari.ss.battle.status.mob.MobStatus
import me.syari.ss.battle.status.player.PlayerStatus.Companion.status
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

data class DamageEventEntity(
        val entity: LivingEntity,
        val status: EntityStatus
) {
    companion object {
        fun fromMob(entity: LivingEntity): DamageEventEntity? {
            val metaDataList = entity.getMetadata("ss-mob-id")
            if (metaDataList.isEmpty()) return null
            return MobStatus.getMobStatus(metaDataList.first().asString())?.let {
                DamageEventEntity(entity, it)
            }
        }

        fun fromPlayer(player: Player): DamageEventEntity {
            return DamageEventEntity(player, player.status)
        }
    }
}