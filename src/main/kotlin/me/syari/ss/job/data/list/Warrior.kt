package me.syari.ss.job.data.list

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.data.JobData
import me.syari.ss.job.skill.ActiveSkill
import me.syari.ss.job.skill.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.list.ActiveBerserk
import org.bukkit.Material

object Warrior: JobData {
    override val id = "warrior"
    override val display = "ウォーリア"
    override val icon = Material.IRON_SWORD
    override val description = "高い攻撃力で戦闘を行うジョブ"
    override val defaultAvailable = true
    override val availableWeaponType = setOf(
        WeaponType.Axe
    )
    override val activeSkill = buildActiveSkill {
        set(
            ActiveSkill.Type.KeyF,
            ActiveBerserk
        )
    }
}