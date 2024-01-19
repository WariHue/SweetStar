package com.warihue.abilities.core

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Player
import org.bukkit.entity.Trident
import org.bukkit.util.Vector
import kotlin.random.Random

object LaunchTrident {
    /** 삼지창 다중 발사 함수 */
    fun shotTrident(player: Player, loc: Vector, force:Int){
        if(player.getCooldown(Material.AIR) > 0) return
        if(force != 10) return
        for(i: Int in 1..17){
            //region Make random Vector data
            var rX = Random.nextDouble(0.02, 0.1)
            var rY = Random.nextDouble(0.02, 0.1)
            var rZ = Random.nextDouble(0.02, 0.1)
            var bX = Random.nextBoolean()
            var bY = Random.nextBoolean()
            var bZ = Random.nextBoolean()
            var vector = Vector(if(bX)loc.x.plus(rX);else loc.x.minus(rX), if(bY)loc.y.plus(rY);else loc.y.minus(rY), if(bZ)loc.z.plus(rZ);else loc.z.minus(rZ))
            //endregion
            player.setCooldown(Material.AIR, 3)
            player.launchProjectile(Trident::class.java, vector.multiply(3)).apply {this.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED; this.customName(Component.text(force)) } //launch projectile
        }
    }
}