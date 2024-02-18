package com.warihue.sweetstar.core.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta



fun starItem():ItemStack{
    val randStoneItem: ItemStack = ItemStack(Material.PAPER)
    val meta: ItemMeta = randStoneItem.itemMeta
    meta.setCustomModelData(3)
    meta.displayName(text("스타-포스 주문서").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false))
    meta.lore(listOf<Component>(
        text("강화 주문서")
    ))
    randStoneItem.itemMeta = meta
    return randStoneItem
}
fun cubeItem():ItemStack{
    val randStoneItem: ItemStack = ItemStack(Material.PAPER)
    val meta: ItemMeta = randStoneItem.itemMeta
    meta.setCustomModelData(2)
    meta.displayName(text("마법 부여 주문서").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false))
    meta.lore(listOf<Component>(
        text("마법 부여 주문서")
    ))
    randStoneItem.itemMeta = meta
    return randStoneItem
}
fun slotFounder(player:Player, itemStack: ItemStack):Int {
    for (i:Int in 1..64){
        val a = itemStack
        a.amount = i
        if(player.inventory.first(a) != -1){
            return player.inventory.first(a)
        }
    }
    return -1
}