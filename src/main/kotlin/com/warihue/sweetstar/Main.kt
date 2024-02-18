package com.warihue.sweetstar

import com.warihue.sweetstar.EventManager.registerEvents
import com.warihue.sweetstar.commands.RegisterCommand.registerCommands
import com.warihue.sweetstar.core.PlayerManager
import com.warihue.sweetstar.saves.dataFile
import com.warihue.sweetstar.saves.deleteFile
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
        var OBEnder:Boolean = false
        var YBEnder:Boolean = false
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
        val oc = dataFile(File("${dataFolder}/config.yml"), false, false)
        OBEnder = oc.OBEnder
        YBEnder = oc.YBEnder
        val wcOB = WorldCreator("team_OB")
        wcOB.environment(World.Environment.NETHER)
        wcOB.createWorld()
        val wcYB = WorldCreator("team_YB")
        wcYB.environment(World.Environment.NETHER)
        wcYB.createWorld()
    }

    override fun onDisable() {
        deleteFile(File("${dataFolder}/config.yml"))
        dataFile(File("${dataFolder}/config.yml"), OBEnder, YBEnder)
    }
}