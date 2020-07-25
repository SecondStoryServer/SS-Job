package me.syari.ss.job.grade.grade1

import me.syari.ss.item.itemRegister.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkill
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.active.grade1.ActiveBerserk
import org.bukkit.Material

object Warrior: JobData {
    override val id = "warrior"
    override val display = "ウォーリア"
    override val icon = Material.IRON_SWORD
    override val description = "高い攻撃力で戦闘を行うジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Sword, WeaponType.Axe
    )
    override val requirements = setOf<Requirement>(

    )
    override val activeSkill = buildActiveSkill {
        set(ActiveSkill.Type.KeyF, ActiveBerserk)
    }
}