package com.warihue.sweetstar.events

import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent

object BossEvents: Listener {
    @EventHandler
    fun witherSpawnEvent(e: EntitySpawnEvent) {
        if(e.entityType != EntityType.WITHER) return
        val entity: LivingEntity = e.entity as LivingEntity
        entity.maxHealth = 1500.0
        entity.health = 1500.0
        entity.customName(text("강해진 위더"))
    }
}