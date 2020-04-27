package me.syari.ss.job

import me.syari.ss.core.auto.OnEnable
import me.syari.ss.core.command.create.CreateCommand.createCommand
import me.syari.ss.core.command.create.CreateCommand.element
import me.syari.ss.core.command.create.CreateCommand.offlinePlayers
import me.syari.ss.core.command.create.CreateCommand.tab
import me.syari.ss.core.command.create.ErrorMessage
import me.syari.ss.core.player.UUIDPlayer
import me.syari.ss.job.ConfigLoader.loadConfig
import me.syari.ss.job.DatabaseConnector.ActiveJob
import me.syari.ss.job.DatabaseConnector.JobExp
import me.syari.ss.job.DatabaseConnector.JobPoint
import me.syari.ss.job.DatabaseConnector.createTable
import me.syari.ss.job.Main.Companion.jobPlugin
import me.syari.ss.job.gui.JobListGui
import me.syari.ss.job.player.PlayerData.Companion.jobData
import org.bukkit.entity.Player

object CommandCreator: OnEnable {
    override fun onEnable() {
        createCommand(jobPlugin,
            "job",
            "Job",
            tab { _, _ -> element("config", "database", "point", "open") },
            tab("config") { _, _ -> element("reload") },
            tab("database") { _, _ -> element("check", "delete", "clear") },
            tab("point") { _, _ -> element("give", "get") },
            tab("database delete", "point give") { _, _ -> offlinePlayers }) { sender, args ->
            when (args.whenIndex(0)) {
                "config" -> {
                    when (args.whenIndex(1)) {
                        "reload" -> {
                            loadConfig(sender)
                            sendWithPrefix("&fコンフィグをリロードしました")
                        }
                    }
                }
                "database" -> {
                    when (args.whenIndex(1)) {
                        "create" -> {
                            val state = createTable()
                            val builder = StringBuilder()
                            builder.append(if (state.isSuccess) "&f" else "&c")
                            builder.append("テーブル作成に")
                            builder.append(state.message)
                            sendWithPrefix(builder.toString())
                        }
                        "check" -> {
                            val state = DatabaseConnector.checkConnect()
                            val builder = StringBuilder()
                            builder.append(if (state.isSuccess) "&f" else "&c")
                            builder.append("接続に")
                            builder.append(state.message)
                            sendWithPrefix(builder.toString())
                        }
                        "cache" -> {
                            when (args.whenIndex(2)) {
                                "delete" -> {
                                    val player = args.getOfflinePlayer(3, false) ?: return@createCommand
                                    val uuidPlayer = UUIDPlayer(player)
                                    JobExp.deleteCache(uuidPlayer)
                                    ActiveJob.deleteCache(uuidPlayer)
                                    JobPoint.deleteCache(uuidPlayer)
                                    sendWithPrefix("&6${player.name} &fのキャッシュを削除しました")
                                }
                                "clear" -> {
                                    JobExp.clearCache()
                                    ActiveJob.clearCache()
                                    JobPoint.clearCache()
                                    sendWithPrefix("&f全てのキャッシュを削除しました")
                                }
                            }
                        }
                    }
                }
                "point" -> {
                    when (args.whenIndex(1)) {
                        "get" -> {
                            if (sender !is Player) return@createCommand sendError(ErrorMessage.OnlyPlayer)
                            val point = args.getOrNull(2)
                                ?.toIntOrNull() ?: return@createCommand sendError("ポイント入力してください")
                            val jobData = sender.jobData
                            jobData.jobPoint += point
                            sendWithPrefix("&fジョブポイントを &a$point &f取得し &a${jobData.jobPoint} &fになりました")
                        }
                        "give" -> {
                            val player = args.getOfflinePlayer(2, true) ?: return@createCommand
                            val point = args.getOrNull(3)
                                ?.toIntOrNull() ?: return@createCommand sendError("ポイント入力してください")
                            val jobData = player.jobData
                            jobData.jobPoint += point
                            sendWithPrefix("&fジョブポイントを &a${player.name} &fに &a$point &f渡し &a${jobData.jobPoint} &fになりました")
                        }
                    }
                }
                "open" -> {
                    if (sender !is Player) return@createCommand sendError(ErrorMessage.OnlyPlayer)
                    JobListGui.openList(sender, 0)
                }
            }
        }
    }
}