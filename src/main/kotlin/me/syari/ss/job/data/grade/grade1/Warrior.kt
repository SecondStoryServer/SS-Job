package me.syari.ss.job.data.grade.grade1

import me.syari.ss.battle.status.player.StatusType
import me.syari.ss.job.data.JobActiveSkill
import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
import me.syari.ss.job.data.skill.active.base.ActiveBaseAttackUp
import me.syari.ss.job.data.skill.passive.PassiveStatusUpAdd
import me.syari.ss.job.data.skill.passive.PassiveStatusUpMulti
import org.bukkit.Material

object Warrior: JobData {
    override val id = "warrior"
    override val display = "ウォーリア"
    override val icon = Material.IRON_SWORD
    override val description = "高い攻撃力で戦闘を行うジョブ"
    override val availableWeaponType = setOf(WeaponType.Sword, WeaponType.Axe)
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(
        ActiveBaseAttackUp(ActiveBaseAttackUp.Rank.Small)
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