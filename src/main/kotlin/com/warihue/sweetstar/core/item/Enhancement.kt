@file:Suppress("UNUSED_EXPRESSION")

package com.warihue.sweetstar.core.item


import com.warihue.sweetstar.core.item.Enchantment.enchantItem
import com.warihue.sweetstar.core.item.OpenGUI.openAnvilTable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*
import kotlin.random.Random

object Enhancement {

    val forceStoneItem: ItemStack = ItemStack(Material.PAPER).apply {
        this.itemMeta.displayName(text("스타-포스 주문서").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false))
        this.itemMeta.setCustomModelData(2134567)
        this.itemMeta.lore(listOf<Component>(
            text("강화 주문서")
        ))
    }

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

    private fun Material.toAttackDamageDouble(): Double = when(this){
        Material.WOODEN_SWORD -> 4.0
        Material.GOLDEN_SWORD -> 4.0
        Material.STONE_SWORD -> 5.0
        Material.DIAMOND_SWORD -> 7.0
        Material.NETHERITE_SWORD -> 8.0
        Material.WOODEN_AXE -> 7.0
        Material.GOLDEN_AXE -> 7.0
        Material.STONE_AXE -> 9.0
        Material.IRON_AXE -> 9.0
        Material.DIAMOND_AXE -> 9.0
        Material.NETHERITE_AXE -> 10.0
        Material.TRIDENT -> 9.0
        else -> 0.0
    }
    private fun Material.toAttackSpeedDouble():Double = when(this){
        Material.WOODEN_SWORD, Material.GOLDEN_SWORD, Material.STONE_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD -> 1.6
        Material.WOODEN_AXE -> 0.8
        Material.GOLDEN_AXE -> 1.0
        Material.STONE_AXE -> 0.8
        Material.IRON_AXE -> 0.9
        Material.DIAMOND_AXE -> 1.0
        Material.NETHERITE_AXE -> 1.0
        Material.TRIDENT -> 1.1
        else -> 0.0
    }

    fun sliceLore(item: ItemMeta): ItemMeta{
        item.lore(
            item.lore()!!.subList(0, item.lore()!!.size - 6)
        )
        return item
    }

    private fun Material.toArmorDouble():Double = when(this){
        Material.LEATHER_HELMET -> 1.0
        Material.LEATHER_CHESTPLATE -> 3.0
        Material.LEATHER_LEGGINGS -> 2.0
        Material.LEATHER_BOOTS -> 1.0
        Material.CHAINMAIL_HELMET -> 2.0
        Material.CHAINMAIL_CHESTPLATE -> 5.0
        Material.CHAINMAIL_LEGGINGS -> 4.0
        Material.CHAINMAIL_BOOTS -> 1.0
        Material.IRON_HELMET -> 2.0
        Material.IRON_CHESTPLATE -> 6.0
        Material.IRON_LEGGINGS -> 5.0
        Material.IRON_BOOTS -> 2.0
        Material.DIAMOND_HELMET, Material.NETHERITE_HELMET -> 3.0
        Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE -> 8.0
        Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS -> 6.0
        Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS -> 3.0
        Material.TURTLE_HELMET -> 2.0
        else -> 0.0
    }

    private fun Material.toArmorToughness():Double = when(this){
        Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS -> 2.0
        Material.NETHERITE_HELMET , Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS -> 3.0
        else -> 0.0
    }
    private fun Material.isDiamond():Boolean = when(this){
        Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS -> true
        else -> false
    }
    private fun Material.isNetherite():Boolean = when(this){
        Material.NETHERITE_HELMET , Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS -> true
        else -> false
    }
    private fun Material.isHelmet():Boolean = when(this){
        Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET -> true
        else -> false
    }
    private fun Material.isChestplate():Boolean = when(this){
        Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE -> true
        else -> false
    }
    private fun Material.isLeggings():Boolean = when(this){
        Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS -> true
        else -> false
    }
    private fun Material.isBoots():Boolean = when(this){
        Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS -> true
        else -> false
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

    fun Material.isSword():Boolean = when(this){
        Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD -> true
        else -> false
    }

    fun checkEnhanceItem(item: Material):Boolean = when(item) {
        Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
        Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE,
        Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.TURTLE_HELMET,
        Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
        Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
        Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
        Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
        Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
        Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS -> true
        else -> false
    }

    fun enhance(item: ItemMeta, player: Player, material: Material, isL:Boolean, itemStack: ItemStack, ditem: ItemStack): ItemMeta {
        var force: Int
        val forceString = item.lore()?.get(0)
        force = checkEnhanceLevel(forceString.toString())
        if(isL){
            enhanceModifier(material ,item , force)
            return item
        }
        if(force >= 10){
            player.location.world.playSound(player.location, Sound.BLOCK_ANVIL_LAND, 1f, 0.4f)
            return item
        }
        val rand = Random.nextFloat()
        if(rand > 1 - (0.1 * force)){
            if(Random.nextBoolean()) force--
            if(force < 0) force = 0
            player.location.world.playSound(player.location, Sound.ENTITY_ITEM_BREAK, 1f, 1f)
        } else if(rand < (0.95 - 0.1 * force)){
            force++
            player.location.world.playSound(player.location, Sound.BLOCK_ANVIL_USE, 1f, 1f)
        }else{
            itemStack.amount--
            ditem.amount--
            player.closeInventory()
            openAnvilTable(player)
        }
        player.sendMessage(text(rand))
        enhanceModifier(material ,item , force)
        return item
    }

    private fun enhanceModifier(item: Material ,meta: ItemMeta, forceVelue:Int){
        when(item){
            Material.WOODEN_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD,Material.STONE_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD -> swordProfile(meta, item, forceVelue)
            Material.BOW, Material.CROSSBOW -> bowProfile(meta, forceVelue)
            Material.TRIDENT -> tridentProfile(meta, item, forceVelue)
            Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
            Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
            Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
            Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
            Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
            Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS -> armorProfile(meta, item, forceVelue)
            else -> meta
        }
    }

    private fun swordProfile(item: ItemMeta, material: Material, force: Int) {
        item.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE)
        item.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED)
        var ench = item.getEnchantLevel(Enchantment.DAMAGE_ALL).toDouble() / 2 + 0.5
        if(item.getEnchantLevel(Enchantment.DAMAGE_ALL) == 0) ench = 0.0
        val gs1 = AttributeModifier(
            UUID.randomUUID(),
            "generic.attack_damage",
            material.toAttackDamageDouble() + (force.toDouble() * 0.5) + ench,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        )
        val gs2 = AttributeModifier(
            UUID.randomUUID(),
            "generic.attack_speed",
             if(force >= 10) 100.0 else material.toAttackSpeedDouble() + force.toDouble() * 0.1,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        )
        item.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, gs1)
        item.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, gs2)
        if(force >= 10) {
            item.lore(
                listOf<Component>(
                    text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false)
                        .content(force.toStarString()).build(),
                    text(""),
                    text("무적 무시").color(NamedTextColor.AQUA).decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
            )
            item.isUnbreakable = true
        }else{
            item.lore(
                listOf<Component>(
                    text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false)
                        .content(force.toStarString()).build(),
                    text(""),
                    text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
            )
        }
    }

    private fun bowProfile(item: ItemMeta, force: Int) {
        if(force >= 10) {
            item.lore(
                listOf<Component>(
                    text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false).content(force.toStarString()).build(),
                    text(""),
                    text("${force * 10}% 추가 피해").color(TextColor.color(84, 84, 252)).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
            )
            item.isUnbreakable = true
        }else{
            item.lore(
                listOf<Component>(
                    text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false).content(force.toStarString()).build(),
                    text(""),
                    text("${force * 10}% 추가 피해").color(TextColor.color(84, 84, 252)).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)

                )
            )
        }
    }

    private fun tridentProfile(meta: ItemMeta, material: Material, force: Int){
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE)
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED)
        val gs1 = AttributeModifier(
            UUID.randomUUID(),
            "generic.attack_damage",
            material.toAttackDamageDouble() + force.toDouble() * 0.5,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        )
        val gs2 = AttributeModifier(
            UUID.randomUUID(),
            "generic.attack_speed",
            material.toAttackSpeedDouble() + force.toDouble() * 0.1,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        )
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, gs1)
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, gs2)
        meta.lore(
            listOf<Component>(
                text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false).content(force.toStarString()).build(),
                text(""),
                text("${force * 10}% 투사체 추가 피해").color(TextColor.color(84, 84, 252)).decoration(TextDecoration.ITALIC, false),
                text(""),
                text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                text(""),
                text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                text(""),
                text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
            )
        )
        if(force == 10){
            meta.lore(
                listOf<Component>(
                    text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false).content(force.toStarString()).build(),
                    text(""),
                    text("${force * 10}% 투사체 추가 피해").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false),
                    text("일제 사격").color(NamedTextColor.AQUA).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
            )
            meta.isUnbreakable = true
        }
    }

    private fun armorProfile(meta: ItemMeta, material: Material, force: Int){
        meta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE)
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS)
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR)
        meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED)
        meta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH)
        var equipmentSlot = EquipmentSlot.HEAD
        if(material.isHelmet()) equipmentSlot = EquipmentSlot.HEAD
        if(material.isChestplate()) equipmentSlot = EquipmentSlot.CHEST
        if(material.isLeggings()) equipmentSlot = EquipmentSlot.LEGS
        if(material.isBoots()) equipmentSlot = EquipmentSlot.FEET

        val ga = AttributeModifier(UUID.randomUUID(),
            "generic.armor",
            material.toArmorDouble() + force.toDouble() * 0.5,
            AttributeModifier.Operation.ADD_NUMBER,
            equipmentSlot
        )
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, ga)
        if(material.isNetherite() || material.isDiamond()){
            val gat = AttributeModifier(UUID.randomUUID(),
                "generic.armor_toughnees",
                material.toArmorToughness() + force.toDouble() * 0.2,
                AttributeModifier.Operation.ADD_NUMBER,
                equipmentSlot
            )
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, gat)
        }
        if(material.isChestplate()){
            val gmh = AttributeModifier(UUID.randomUUID(),
                "generic.max_health",
                force.toDouble() * 2,
                AttributeModifier.Operation.ADD_NUMBER,
                equipmentSlot
            )
            meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, gmh)
        }
        if(material.isBoots()){
            val speed = AttributeModifier(UUID.randomUUID(),
                "generic.movement_speed",
                force.toDouble() * 0.05,
                AttributeModifier.Operation.MULTIPLY_SCALAR_1,
                equipmentSlot
            )
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, speed)
        }
        if(material.isNetherite()){
            val gmh = AttributeModifier(UUID.randomUUID(),
                "generic.knockback_resistance",
                0.1,
                AttributeModifier.Operation.ADD_NUMBER,
                equipmentSlot
            )
            meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, gmh)
        }
        if(force >= 10) {
            meta.lore(
                listOf<Component>(
                    text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false)
                        .content(force.toStarString()).build(),
                    text(""),
                    text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
            )
            if(material.isBoots()){
                meta.lore(
                    listOf<Component>(
                        text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false)
                            .content(force.toStarString()).build(),
                        text(""),
                        text("더블 점프").color(NamedTextColor.AQUA).decoration(TextDecoration.BOLD, true)
                            .decoration(TextDecoration.ITALIC, false),
                        text(""),
                        text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                        text(""),
                        text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                        text(""),
                        text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                    )
                )
            }
            meta.isUnbreakable = true
        }else{
            meta.lore(
                listOf<Component>(
                    text().color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false)
                        .content(force.toStarString()).build(),
                    text(""),
                    text("성공 : ${95 - 10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("실패 : ${10 * force}%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                    text(""),
                    text("파괴 : 5%").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
            )
        }
    }
}