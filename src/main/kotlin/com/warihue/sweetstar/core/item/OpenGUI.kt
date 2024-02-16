package com.warihue.sweetstar.core.item


import com.warihue.sweetstar.core.item.Enchantment.enchantItem
import com.warihue.sweetstar.core.item.Enhancement.checkEnhanceItem
import com.warihue.sweetstar.core.item.Enhancement.checkEnhanceLevel
import com.warihue.sweetstar.core.item.Enhancement.enhance
import com.warihue.sweetstar.core.item.Enhancement.sliceLore
import io.github.monun.invfx.InvFX.frame
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
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
                        item!!.itemMeta = enhance(item!!.itemMeta, player, clickEvent.currentItem!!.type, true, item!!, clickEvent.currentItem!!)
                        onClick { clickEvents ->
                            clickEvents.currentItem?.itemMeta = enhance(clickEvents.currentItem!!.itemMeta, player, clickEvent.currentItem!!.type, false, item!!, clickEvent.currentItem!!)
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

    fun openEnchantTable(player: Player) {
        var force: Int
        val enchantFrame = frame(2, text("\uEBBB\uECCC", TextColor.color(255, 255, 255))) {
            onClickBottom { clickEvent ->
                if (checkEnhanceItem(clickEvent.currentItem!!.type)) {
                    force = checkEnhanceLevel(clickEvent.currentItem!!.lore()?.get(0).toString())
                    if(force == 0) {
                        slot(4, 0) {
                            this.item = clickEvent.currentItem
                            onClick { clickEvents ->
                                clickEvents.currentItem = enchantItem(clickEvents.currentItem!!, player)
                                clickEvent.currentItem = clickEvents.currentItem
                            }
                        }
                    }
                }
            }
            slot(8, 0) {
                item = gItem
                onClick { openEnchantTable(player) }
            }
        }
        player.invfxOpenFrame(enchantFrame)
    }

    fun openMenu(player: Player) {
        val menuFrame = frame(1, text("", TextColor.color(255, 255, 255))) {
            slot(1, 0) {
                val item = ItemStack(Material.EMERALD)
                val meta:ItemMeta = item.itemMeta
                meta.displayName(text("교환"))
                meta.lore(
                    listOf<Component>(
                        text("30레벨 당:").color(NamedTextColor.BLUE).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false),
                        text("  강화 15개").color(NamedTextColor.DARK_BLUE).decoration(TextDecoration.ITALIC, false),
                        text("  마법부여 15개").color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false)
                    )
                )
                item.itemMeta = meta
                this.item = item
            }
        }
        player.invfxOpenFrame(menuFrame)
    }
}