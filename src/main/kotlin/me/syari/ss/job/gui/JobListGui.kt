package me.syari.ss.job.gui

import me.syari.ss.core.inventory.CreateInventory.close
import me.syari.ss.core.inventory.CreateInventory.inventory
import me.syari.ss.core.inventory.CustomInventory
import me.syari.ss.core.item.CustomItemStack
import me.syari.ss.core.message.Message.title
import me.syari.ss.job.grade.JobData
import me.syari.ss.job.grade.JobGrade
import me.syari.ss.job.grade.JobGrade.Companion.getByIndex
import me.syari.ss.job.player.PlayerData
import me.syari.ss.job.player.PlayerData.Companion.jobData
import me.syari.ss.job.player.PlayerJobData
import me.syari.ss.job.player.PlayerJobData.Companion.maxLevel
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag

object JobListGui {
    fun openList(
        player: Player, page: Int
    ) {
        getByIndex(page)?.let {
            openList(
                player, page, it
            )
        }
    }

    private fun openList(
        player: Player, page: Int, grade: JobGrade
    ) {
        inventory(
            "&9&lジョブ一覧 &7- &9&l${grade.groupName}", 2, "job", "list", grade.groupName
        ) {
            grade.jobList.forEach { (job, slot) ->
                setJobItem(
                    slot, player, job, page, grade
                )
            }
            item(
                9..17, Material.GRAY_STAINED_GLASS_PANE
            )
            getByIndex(page - 1)?.let {
                item(
                    9, Material.BOOK, "&6前のページ"
                ).event {
                    openList(
                        player, page - 1, it
                    )
                }
            }
            item(
                13, Material.BARRIER, "&c閉じる"
            ).event {
                close(player)
            }
            getByIndex(page + 1)?.let {
                item(
                    17, Material.BOOK, "&6次のページ"
                ).event {
                    openList(
                        player, page + 1, it
                    )
                }
            }
        }.open(player)
    }

    private fun CustomInventory.setJobItem(
        slot: Int, player: Player, jobData: JobData, page: Int, grade: JobGrade
    ) {
        val playerData = player.jobData
        val playerJob = playerData.getJob(jobData)
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
                if (playerData.isAvailableJob(jobData)) {
                    appendln("&7&m----&d 情報 &7&m----")
                    append("&7現在のレベル: &a${playerJob.level} &7/ &a$maxLevel")
                    appendln()
                } else {
                    appendln("&7&m----&d 条件 &7&m----")
                    jobData.requirements?.let {
                        it.forEach { req ->
                            append(if (req.has(playerData)) "&a" else "&c")
                            appendln(req.description)
                        }
                    }
                }
                appendln()
                if (playerJob.isActive) {
                    append("&6現在のジョブです")
                } else {
                    append("&6このジョブに変更する")
                }
            }.lines()
        ).apply {
            if (playerJob.isActive) {
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
            when {
                playerJob.isActive -> {

                }
                playerJob.isAvailable -> {
                    changeJob(
                        player, jobData, playerData, playerJob
                    )
                    playerData.updateExpBar()
                }
                playerJob.canGet -> {
                    inventory(
                        "&9&lジョブ変更 &7- &9&l${jobData.display}", 2, "job", "list", grade.groupName, jobData.id
                    ) {
                        item(
                            listOf(
                                0, 1, 2, 3, 9, 10, 11, 12
                            ), Material.LIME_STAINED_GLASS_PANE, "&a変更する"
                        ).event {
                            changeJob(
                                player, jobData, playerData, playerJob
                            )
                            jobData.requirements?.forEach {
                                it.use(playerData)
                            }
                            playerData.getJob(jobData).exp = 0
                        }
                        item(
                            4, 13, material = Material.GRAY_STAINED_GLASS_PANE
                        )
                        item(
                            listOf(
                                5, 6, 7, 8, 14, 15, 16, 17
                            ), Material.RED_STAINED_GLASS_PANE, "&cやめる"
                        ).event {
                            openList(
                                player, page, grade
                            )
                        }
                    }.open(player)
                }
            }
        }
    }

    private fun changeJob(
        player: Player, jobData: JobData, playerData: PlayerData, playerJobData: PlayerJobData
    ) {
        close(player)
        player.title(
            "&6&l&n${jobData.display}", "&f&lジョブを変更しました", 5, 30, 10
        )
        playerData.activeJob = playerJobData
    }
}