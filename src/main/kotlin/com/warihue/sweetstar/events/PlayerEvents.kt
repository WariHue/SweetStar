package com.warihue.sweetstar.events

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.warihue.sweetstar.Main
import com.warihue.sweetstar.core.Team
import com.warihue.sweetstar.core.item.Enhancement.checkEnhanceLevel
import com.warihue.sweetstar.core.item.Enhancement.isSword
import com.warihue.sweetstar.core.item.OpenGUI.openAnvilTable
import com.warihue.sweetstar.core.item.OpenGUI.openEnchantTable
import net.minecraft.world.level.block.BeaconBlock
import org.bukkit.*
import org.bukkit.block.Beacon
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.*
import org.bukkit.inventory.Inventory
import java.util.*


object PlayerEvents: Listener {

    private var playerManager = Main.playerManager

    @EventHandler
    fun onJoin(e: PlayerJoinEvent){
        playerManager?.let {
            it.addPlayer(e.player)
        }
    }

    @EventHandler
    fun onDie(e: PlayerDeathEvent){
        val date = Date()
        e.player.inventory.clear()
        e.player.banPlayer("당신은 죽었습니다!", Date(date.time + 1800000), "death",true)
    }

    @EventHandler
    fun playerJump(e: PlayerJumpEvent){
        if(e.player.gameMode == GameMode.CREATIVE) return
        if(e.player.inventory.boots == null) return
        if(checkEnhanceLevel(e.player.inventory.boots!!.itemMeta!!.lore()?.get(0).toString()) == 10)
        e.player.allowFlight = true
    }

    @EventHandler
    fun doubleJump(e: PlayerToggleFlightEvent){
        val player = e.player
        if(player.gameMode == GameMode.CREATIVE) return
        if(player.getCooldown(Material.NETHERITE_BOOTS) > 0){ e.isCancelled = true; return}
        e.isCancelled = true
        e.player.allowFlight = false
        player.velocity = player.location.direction.multiply(0.6).setY(0.6)
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
        }else if(e.inventory.type == InventoryType.ENCHANTING){
            val player = e.player as Player
            openEnchantTable(player)
            e.isCancelled = true
        }
    }

    @EventHandler
    fun noInDamage(e: EntityDamageByEntityEvent){
        if(e.entity is Player)
            (e.entity as Player).setCooldown(Material.NETHERITE_BOOTS, 200)
        if(e.entity !is LivingEntity) return
        if(e.damager !is Player) return
        val player = e.damager as Player
        if(player.inventory.itemInMainHand.itemMeta == null) return
        val force = checkEnhanceLevel(player.inventory.itemInMainHand.itemMeta.lore()?.get(0).toString())
        if(force == 10 && (e.damager as Player).inventory.itemInMainHand.type.isSword()){ (e.entity as LivingEntity).maximumNoDamageTicks = 0; return}
        (e.entity as LivingEntity).maximumNoDamageTicks = 20
    }

    @EventHandler
    fun goNether(e: PlayerPortalEvent){
        if(e.player.world.environment == World.Environment.NETHER) return
        if(Main.playerManager!!.getPlayerData(e.player)!!.team == Team.OB){
            e.to.world = Bukkit.getWorld("team_OB")
            return
        }
        if(Main.playerManager!!.getPlayerData(e.player)!!.team == Team.YB){
            e.to.world = Bukkit.getWorld("team_YB")
            return
        }
    }
}