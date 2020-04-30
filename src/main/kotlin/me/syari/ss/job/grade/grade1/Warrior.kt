package me.syari.ss.job.grade.grade1

import me.syari.ss.battle.status.player.StatusType
import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.base.ActiveBaseAttackUp
import me.syari.ss.job.skill.active.grade1.ActiveBerserk
import me.syari.ss.job.skill.passive.PassiveStatusUpAdd
import me.syari.ss.job.skill.passive.PassiveStatusUpMulti
import org.bukkit.Material

object Warrior: JobData {
    override val id = "warrior"
    override val display = "ウォーリア"
    override val icon = Material.IRON_SWORD
    override val description = "高い攻撃力で戦闘を行うジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Sword, WeaponType.Axe
    )
    override val requirements = setOf(
        Requirement.Point(5)
    )
    override val activeSkill = listOf(
        ActiveBaseAttackUp(ActiveBaseAttackUp.Rank.Small), ActiveBerserk
    )
    override val passiveSkill = listOf(
        PassiveStatusUpAdd(
            StatusType.BaseAttack, Material.BLAZE_POWDER, 3F, 1
        ), PassiveStatusUpAdd(
            StatusType.BaseAttack, Material.BLAZE_POWDER, 3F, 5
        ), PassiveStatusUpAdd(
            StatusType.BaseAttack, Material.BLAZE_POWDER, 3F, 10
        ), PassiveStatusUpAdd(
            StatusType.BaseAttack, Material.BLAZE_POWDER, 3F, 15, true
        ), PassiveStatusUpAdd(
            StatusType.BaseAttack, Material.BLAZE_POWDER, 3F, 20
        ), PassiveStatusUpAdd(
            StatusType.BaseAttack, Material.BLAZE_POWDER, 3F, 25
        ), PassiveStatusUpMulti(
            StatusType.BaseAttack, Material.BLAZE_POWDER, 0.05F, 30, true
        )
    )
}