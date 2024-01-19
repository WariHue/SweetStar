package com.warihue.abilities.events

import com.warihue.abilities.Main
import com.warihue.abilities.core.item.Enhancement.checkEnhanceLevel
import com.warihue.abilities.core.item.Enhancement.isSword
import com.warihue.abilities.core.item.OpenGUI.openAnvilTable
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit

import org.bukkit.Material
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent
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

    @EventHandler
    fun opneAnvilEvent(e: InventoryOpenEvent){
        if(e.inventory.type == InventoryType.ANVIL){
            val player = e.player as Player
            openAnvilTable(player)
            e.isCancelled = true
        }
    }

    @EventHandler
    fun noInDamage(e: EntityDamageByEntityEvent){
        if(e.entity !is LivingEntity) return
        if(e.damager !is Player) return
        val player = e.damager as Player
        val force = checkEnhanceLevel(player.inventory.itemInMainHand.itemMeta.lore()?.get(0).toString())
        if(force == 10 && (e.damager as Player).inventory.itemInMainHand.type.isSword()){ (e.entity as LivingEntity).maximumNoDamageTicks = 0; return}
        (e.entity as LivingEntity).maximumNoDamageTicks = 20
    }
}