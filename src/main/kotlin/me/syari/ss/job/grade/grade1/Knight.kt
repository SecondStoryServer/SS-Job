package me.syari.ss.job.grade.grade1

import me.syari.ss.battle.status.player.StatusChange.Type.Add
import me.syari.ss.battle.status.player.StatusChange.Type.Multi
import me.syari.ss.battle.status.player.StatusType
import me.syari.ss.job.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkill
import org.bukkit.Material

object Knight: JobData {
    override val id = "knight"
    override val display = "ナイト"
    override val icon = Material.IRON_CHESTPLATE
    override val description = "攻撃と防御のバランスが取れたジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Sword
    )
    override val requirements = setOf(
        Requirement.Point(5)
    )
    override val activeSkill = listOf<ActiveSkill>(

    )
    override val passiveSkill = listOf(
        PassiveSkill(
            1, StatusType.BaseDefense, 3F, Add
        ), PassiveSkill(
            5, StatusType.BaseDefense, 3F, Add
        ), PassiveSkill(
            10, StatusType.MaxHealth, 4F, Add
        ), PassiveSkill(
            15, StatusType.BaseDefense, 3F, Add, true
        ), PassiveSkill(
            20, StatusType.MaxHealth, 4F, Add
        ), PassiveSkill(
            25, StatusType.BaseDefense, 3F, Add
        ), PassiveSkill(
            30, StatusType.BaseDefense, 0.05F, Multi, true
        )
    )
}