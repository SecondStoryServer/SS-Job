package me.syari.ss.job.grade.grade1

import me.syari.ss.battle.status.player.StatusChange.Type.Add
import me.syari.ss.battle.status.player.StatusChange.Type.Multi
import me.syari.ss.battle.status.player.StatusType
import me.syari.ss.item.equip.weapon.WeaponType
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.Requirement
import me.syari.ss.job.skill.active.ActiveSkillBuilder.Companion.buildActiveSkill
import me.syari.ss.job.skill.passive.PassiveSkillBuilder.Companion.buildPassiveSkill
import org.bukkit.Material

object Knight: JobData {
    override val id = "knight"
    override val display = "ナイト"
    override val icon = Material.IRON_CHESTPLATE
    override val description = "攻撃と防御のバランスが取れたジョブ"
    override val availableWeaponType = setOf(
        WeaponType.Sword
    )
    override val requirements = setOf<Requirement>(

    )
    override val activeSkill = buildActiveSkill {

    }
    override val passiveSkill = buildPassiveSkill {
        add(1, StatusType.BaseDefense, 3F, Add)
        add(5, StatusType.BaseDefense, 3F, Add)
        add(10, StatusType.MaxHealth, 4F, Add)
        add(15, StatusType.BaseDefense, 3F, Add, true)
        add(20, StatusType.MaxHealth, 4F, Add)
        add(25, StatusType.BaseDefense, 3F, Add)
        add(30, StatusType.BaseDefense, 0.05F, Multi, true)
    }
}