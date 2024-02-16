package com.warihue.sweetstar.core.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
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

    fun enchantItem(itemStack: ItemStack, p:Player): ItemStack{
        var lineLevel: Int = itemStack.enchantments.size
        if(lineLevel == 0) lineLevel = Random.nextInt(1, 3)
        if(Random.nextFloat() > 0.98 && lineLevel < 7) lineLevel++
        for (i in 0 until Enchantment.values().size){
            itemStack.removeEnchantment(Enchantment.values()[i])
        }
        var i = 0
        val enchantments: HashMap<Enchantment, Int> = HashMap()
        RUN@while(i < lineLevel){
            val randEnchant: Enchantment = Enchantment.values()[Random.nextInt(Enchantment.values().size)]
            val randLevel: Int = Random.nextInt(randEnchant.maxLevel) + 1
            if(enchantments[randEnchant] != null) continue@RUN
            itemStack.addUnsafeEnchantment(randEnchant, randLevel)
            enchantments[randEnchant] = randLevel
            i++
        }
        p.location.world.playSound(p.location, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f)
        return itemStack
    }
}