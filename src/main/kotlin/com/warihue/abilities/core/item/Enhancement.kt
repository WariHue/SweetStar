package com.warihue.abilities.core.item


import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.inventory.meta.ItemMeta
import kotlin.random.Random

object Enhancement {
    private fun Int.toStarString() = when(this){
        0 -> "☆☆☆☆☆☆☆☆☆☆"
        1 -> "★☆☆☆☆☆☆☆☆☆"
        2 -> "★★☆☆☆☆☆☆☆☆"
        3 -> "★★★☆☆☆☆☆☆☆"
        4 -> "★★★★☆☆☆☆☆☆"
        5 -> "★★★★★☆☆☆☆☆"
        6 -> "★★★★★★☆☆☆☆"
        7 -> "★★★★★★★☆☆☆"
        8 -> "★★★★★★★★☆☆"
        9 -> "★★★★★★★★★☆"
        10 -> "★★★★★★★★★★"
        else -> "☆☆☆☆☆☆☆☆☆☆"
    }

    fun checkEnhanceLevel(string: String): Int{
        var i = 0
        var force = 0
        while (i < string.length) {
            if (string[i].code == 9733) {
                ++force
            }
            ++i
        }
        return force
    }

    fun enhanceItem(item: Material):Boolean = when(item){
        Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
        Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.TURTLE_HELMET,
        Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
        Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
        Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
        Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
        Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
        Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS -> true
        else -> false
    }

    fun enhance(item: ItemMeta): ItemMeta {
        var force: Int
        val forceString = item.lore()?.get(0)
        force = checkEnhanceLevel(forceString.toString())
        val a = Random.nextBoolean()
        if(a) force++ else force--
        item.lore(
            listOf<Component>(
                Component.text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false).content(force.toStarString()).build()
            )
        )
        return item
    }
}