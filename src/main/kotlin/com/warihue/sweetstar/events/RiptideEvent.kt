package com.warihue.sweetstar.events


import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerRiptideEvent
import com.warihue.sweetstar.core.LaunchTrident.shotTrident
import com.warihue.sweetstar.core.item.Enhancement.checkEnhanceLevel
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.ProjectileLaunchEvent

object RiptideEvent: Listener {
    @EventHandler
    fun onTrident(e: PlayerRiptideEvent){
        val player = e.player
        val loc = player.eyeLocation.direction
        val force = checkEnhanceLevel(player.inventory.itemInMainHand.itemMeta.lore()?.get(0).toString())
        shotTrident(player, loc, force)
    }

    @EventHandler
    fun onShotTrident(e: ProjectileLaunchEvent){
        if(e.entity.shooter !is Player) return
        if(e.entityType != EntityType.TRIDENT) return
        val player = (e.entity.shooter as Player)
        val force = checkEnhanceLevel(player.inventory.itemInMainHand.itemMeta.lore()?.get(0).toString())
        val loc = player.eyeLocation.direction
        e.entity.customName(text(force))
        shotTrident(player, loc, force)
    }

    @EventHandler
    fun onShotArrow(e: ProjectileLaunchEvent){
        if(e.entity.shooter !is Player) return
        if(e.entityType != EntityType.ARROW) return
        val player = (e.entity.shooter as Player)
        val force = checkEnhanceLevel(player.inventory.itemInMainHand.itemMeta.lore()?.get(0).toString())
        e.entity.customName(text(force))
    }

    @EventHandler
    fun onHit(e: EntityDamageByEntityEvent){
        if(e.cause == EntityDamageEvent.DamageCause.PROJECTILE){
            when(e.damager.customName()){
                text(0) -> e.damage *= 1.0
                text(1) -> e.damage *= 1.1
                text(2) -> e.damage *= 1.2
                text(3) -> e.damage *= 1.3
                text(4) -> e.damage *= 1.4
                text(5) -> e.damage *= 1.5
                text(6) -> e.damage *= 1.6
                text(7) -> e.damage *= 1.7
                text(8) -> e.damage *= 1.8
                text(9) -> e.damage *= 1.9
                text(10) -> e.damage *= 2.0
                else -> e.damage *= 1.0
            }
        }
    }
}