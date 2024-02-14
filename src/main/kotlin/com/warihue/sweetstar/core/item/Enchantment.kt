package com.warihue.sweetstar.core.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

object Enchantment {
    val randStoneItem: ItemStack = ItemStack(Material.PAPER).apply {
        this.itemMeta.displayName(
            Component.text("마법 부여 주문서").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(
                TextDecoration.ITALIC, false))
        this.itemMeta.setCustomModelData(2134568)
        this.itemMeta.lore(listOf<Component>(
            Component.text("마법 부여 주문서")
        ))
    }

    fun enchantItem(itemStack: ItemStack){
        val enchantment: HashMap<Enchantment, Int> = HashMap<Enchantment, Int>()
        var lineLevel: Int = itemStack.enchantments.size
        if(Random.nextFloat() > 0.95) lineLevel++
        for (i in 0 until Enchantment.values().size){
            itemStack.removeEnchantment(Enchantment.values()[i])
        }
        for (i in 0 until  lineLevel){
            val randEnchant: Enchantment = Enchantment.values()[Random.nextInt(Enchantment.values().size)]
            if(enchantment[randEnchant] == null) return
            val randLevel: Int = Random.nextInt(randEnchant.maxLevel) + 1
            itemStack.addEnchantment(randEnchant, randLevel)
            enchantment[randEnchant] = randLevel
        }
    }
}