package com.warihue.abilities.core.item


import com.warihue.abilities.core.item.Enhancement.checkEnhanceItem
import com.warihue.abilities.core.item.Enhancement.checkEnhanceLevel
import com.warihue.abilities.core.item.Enhancement.enhance
import com.warihue.abilities.core.item.Enhancement.sliceLore
import io.github.monun.invfx.InvFX.frame
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import io.github.monun.invfx.openFrame as invfxOpenFrame

object OpenGUI {

    private val gItem =
        ItemStack(Material.BARRIER).apply {
            itemMeta = itemMeta.apply {
                displayName(
                    text().color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                        .decorate(TextDecoration.BOLD).content("아이템 빼기").build()
                )
            }
        }
    fun openAnvilTable(player: Player) {
        var force: Int


        val anvilFrame = frame(1, text("\uEBBB\uEAAA", TextColor.color(255, 255, 255))) {
            onClickBottom { clickEvent ->
                force = checkEnhanceLevel(clickEvent.currentItem!!.lore()?.get(0).toString())
                if (force != 10 && checkEnhanceItem(clickEvent.currentItem!!.type)) {
                    slot(4, 0) {
                        this.item = clickEvent.currentItem
                        item!!.itemMeta = enhance(item!!.itemMeta, player, clickEvent.currentItem!!.type, true)
                        onClick { clickEvents ->
                            clickEvents.currentItem?.itemMeta = enhance(clickEvents.currentItem!!.itemMeta, player, clickEvent.currentItem!!.type, false)
                            clickEvent.currentItem!!.itemMeta = sliceLore(clickEvents.currentItem!!.itemMeta)
                        }
                    }
                }
            }
            slot(8, 0) {
                item = gItem
                onClick { openAnvilTable(player) }
            }
        }
        player.invfxOpenFrame(anvilFrame)
    }
}