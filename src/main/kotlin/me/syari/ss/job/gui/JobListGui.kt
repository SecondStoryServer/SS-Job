package me.syari.ss.job.gui

import me.syari.ss.core.inventory.CreateInventory.close
import me.syari.ss.core.inventory.CreateInventory.inventory
import me.syari.ss.core.inventory.CustomInventory
import me.syari.ss.core.item.CustomItemStack
import me.syari.ss.core.message.Message.title
import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobData.Companion.jobList
import me.syari.ss.job.player.PlayerJob
import me.syari.ss.job.player.PlayerJob.Companion.jobData
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag

object JobListGui {
    fun openList(
        player: Player
    ) {
        inventory(
            "&9&lジョブ一覧", 2, "job", "list"
        ) {
            jobList.forEachIndexed { index, job ->
                setJobItem(index, player, job)
            }
            item(
                9..17, Material.GRAY_STAINED_GLASS_PANE
            )
            item(
                13, Material.BARRIER, "&c閉じる"
            ).event {
                close(player)
            }
        }.open(player)
    }

    private fun CustomInventory.setJobItem(
        slot: Int,
        player: Player,
        jobData: JobData
    ) {
        val playerData = player.jobData
        val isActiveJob = playerData.isActiveJob(jobData)
        val icon = CustomItemStack.create(
            jobData.icon, "&6${jobData.display}", buildString {
                appendln("&7&m----&d 説明 &7&m----")
                jobData.description.lines().forEach {
                    appendln("&b$it")
                }
                appendln()
                appendln("&7&m----&d 武器 &7&m----")
                appendln("&a${jobData.availableWeaponType.joinToString(separator = "・") { it.display }}")
                appendln()
                if (isActiveJob) {
                    appendln("&7&m----&d 情報 &7&m----")
                    append("&7現在のレベル: &a${playerData.getJobLevel(jobData)}")
                    appendln()
                }
                appendln()
                if (isActiveJob) {
                    append("&6現在のジョブです")
                } else {
                    append("&6このジョブに変更する")
                }
            }.lines()
        ).apply {
            if (isActiveJob) {
                addEnchant(
                    Enchantment.DURABILITY, 0
                )
                addItemFlag(ItemFlag.HIDE_ENCHANTS)
            }
            addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
        }
        item(
            slot, icon
        ).event {
            if (!isActiveJob) {
                changeJob(player, jobData, playerData)
            }
        }
    }

    private fun changeJob(
        player: Player,
        jobData: JobData,
        playerData: PlayerJob
    ) {
        close(player)
        player.title(
            "&6&l&n${jobData.display}", "&f&lジョブを変更しました", 5, 30, 10
        )
        playerData.activeJob = jobData
    }
}