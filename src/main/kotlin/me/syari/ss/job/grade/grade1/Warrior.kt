package me.syari.ss.job.grade.grade1

import me.syari.ss.battle.status.player.StatusChange.Type.Add
import me.syari.ss.battle.status.player.StatusChange.Type.Multi
import me.syari.ss.battle.status.player.StatusType.BaseAttack
import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.active.base.ActiveBaseAttackUp
import me.syari.ss.job.skill.active.grade1.ActiveBerserk
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
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
        add(ActiveBaseAttackUp(ActiveBaseAttackUp.Rank.Small))
        add(ActiveBerserk)
    }
    override val passiveSkill = buildPassiveSkill {
        add(1, BaseAttack, 3F, Add)
        add(5, BaseAttack, 3F, Add)
        add(10, BaseAttack, 3F, Add)
        add(15, BaseAttack, 3F, Add, true)
        add(20, BaseAttack, 3F, Add)
        add(25, BaseAttack, 3F, Add)
        add(30, BaseAttack, 0.05F, Multi, true)
    }
}