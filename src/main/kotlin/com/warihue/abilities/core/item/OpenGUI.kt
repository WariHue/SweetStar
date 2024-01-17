package com.warihue.abilities.core.item


import com.warihue.abilities.core.item.Enhancement.checkEnhanceLevel
import com.warihue.abilities.core.item.Enhancement.enhance
import io.github.monun.invfx.InvFX.frame
import io.github.monun.invfx.openFrame
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import kotlin.random.Random

object OpenGUI {
    fun openAnvilTable(player: Player){
        val anvilFrame = frame(1, text("\uEBBB\uEAAA", TextColor.color(255,255,255))){
            onOpen { openEvent ->
                openEvent.player.sendMessage("a")
            }
            onClickBottom { clickEvent ->
                var force = checkEnhanceLevel(clickEvent.currentItem!!.lore()?.get(0).toString())
                if (force == 10) {
                    clickEvent.whoClicked.sendMessage("이미 10강임")
                } else {
                    slot(4, 0) {
                        item = clickEvent.currentItem
                        onClick { clickEvents ->
                            clickEvents.currentItem?.itemMeta = enhance(clickEvents.currentItem!!.itemMeta)
                            clickEvent.currentItem = clickEvents.currentItem!!
                            }
                        }
                    }
                }
            }
        player.openFrame(anvilFrame)
    }
}