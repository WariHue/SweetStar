package com.warihue.abilities.events

import com.warihue.abilities.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerEvents: Listener {

    private var playerManager = Main.playerManager
    @EventHandler
    fun onJoin(e: PlayerJoinEvent){
        playerManager?.let {
            it.addPlayer(e.player)
        }

    }

    @EventHandler
    fun onPlayerKickEvent(e: PlayerKickEvent){
        playerManager?.let {
            it.deletePlayer(e.player)
        }
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent){
        playerManager?.let {
            it.deletePlayer(e.player)
        }
    }
}