package com.warihue.abilities

import com.warihue.abilities.EventManager.registerEvents
import com.warihue.abilities.commands.RegisterCommand.registerCommands
import com.warihue.abilities.core.PlayerManager
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main: JavaPlugin() {

    companion object{
        var playerManager : PlayerManager? = null
        var instance : Main? = null
        var datafolder: File? = null
    }


    override fun onEnable() {
        logger.info("\n" +
                "****************************\n" +
                "*         :D Hello         *\n" +
                "* WariHue's Ability Plugin *\n" +
                "*     made by WariHue      *\n" +
                "****************************\n")
        instance = this
        playerManager = PlayerManager()
        registerEvents()
        registerCommands()
        datafolder = dataFolder
    }
}