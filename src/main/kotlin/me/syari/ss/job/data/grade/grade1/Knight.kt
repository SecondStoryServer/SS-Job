package me.syari.ss.job.data.grade.grade1

import me.syari.ss.battle.status.player.StatusType
import me.syari.ss.job.data.JobActiveSkill
import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobRequirement
import me.syari.ss.job.data.WeaponType
import me.syari.ss.job.data.skill.passive.PassiveStatusUpAdd
import me.syari.ss.job.data.skill.passive.PassiveStatusUpMulti
import org.bukkit.Material

object Knight: JobData {
    override val id = "knight"
    override val display = "ナイト"
    override val icon = Material.IRON_CHESTPLATE
    override val description = "攻撃と防御のバランスが取れたジョブ"
    override val availableWeaponType = setOf(WeaponType.Sword)
    override val requirements = setOf(
        JobRequirement.Point(5)
    )
    override val activeSkill = listOf<JobActiveSkill>(

    )
    override val passiveSkill = listOf(
        PassiveStatusUpAdd(
            StatusType.BaseDefense, Material.LEATHER_CHESTPLATE, 3F, 1
        ), PassiveStatusUpAdd(
            StatusType.BaseDefense, Material.LEATHER_CHESTPLATE, 3F, 5
        ), PassiveStatusUpAdd(
            StatusType.MaxHealth, Material.GLISTERING_MELON_SLICE, 4F, 10
        ), PassiveStatusUpAdd(
            StatusType.BaseDefense, Material.LEATHER_CHESTPLATE, 3F, 15, true
        ), PassiveStatusUpAdd(
            StatusType.MaxHealth, Material.GLISTERING_MELON_SLICE, 4F, 20
        ), PassiveStatusUpAdd(
            StatusType.BaseDefense, Material.LEATHER_CHESTPLATE, 3F, 25
        ), PassiveStatusUpMulti(
            StatusType.BaseDefense, Material.LEATHER_CHESTPLATE, 0.05F, 30, true
        )
    )
}