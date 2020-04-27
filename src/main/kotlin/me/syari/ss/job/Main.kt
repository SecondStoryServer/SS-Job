package me.syari.ss.job

import me.syari.ss.core.auto.OnEnable
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    companion object {
        lateinit var jobPlugin: JavaPlugin
    }

    override fun onEnable() {
        jobPlugin = this
        OnEnable.register(ConfigLoader, DatabaseConnector, CommandCreator)
    }
}