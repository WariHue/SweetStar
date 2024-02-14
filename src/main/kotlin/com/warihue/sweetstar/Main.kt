package com.warihue.sweetstar

import com.warihue.sweetstar.EventManager.registerEvents
import com.warihue.sweetstar.commands.RegisterCommand.registerCommands
import com.warihue.sweetstar.core.PlayerManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import org.bukkit.World
import org.bukkit.WorldCreator

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
        val wcOB = WorldCreator("team_OB")
        wcOB.environment(World.Environment.NETHER)
        wcOB.createWorld()
        val wcYB = WorldCreator("team_YB")
        wcYB.environment(World.Environment.NETHER)
        wcYB.createWorld()
    }
}