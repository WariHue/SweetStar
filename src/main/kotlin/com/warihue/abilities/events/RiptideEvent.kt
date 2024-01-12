package com.warihue.abilities.events

import com.warihue.abilities.Main
import com.warihue.abilities.core.Ability
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerRiptideEvent
import com.warihue.abilities.core.LaunchTrident.shotTrident
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent

object RiptideEvent: Listener {
    @EventHandler
    fun onTrident(e: PlayerRiptideEvent){
        val player = e.player
        if(Main.playerManager?.getPlayerData(player)?.ability != Ability.AQUAMAN) return
        var loc = player.eyeLocation.direction
        shotTrident(player, loc)
    }

    @EventHandler
    fun onShot(e: ProjectileLaunchEvent){
        if(e.entity.shooter !is Player) return
        if(e.entityType != EntityType.TRIDENT) return
        val player = (e.entity.shooter as Player)
        if(Main.playerManager?.getPlayerData(player)?.ability != Ability.AQUAMAN) return
        var loc = player.eyeLocation.direction
        shotTrident(player, loc)
    }
}