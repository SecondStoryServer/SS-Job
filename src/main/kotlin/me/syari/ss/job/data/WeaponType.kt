package me.syari.ss.job.data

import me.syari.ss.core.item.CustomItemStack
import org.bukkit.Material

enum class WeaponType(
    val id: String,
    val icon: Material,
    val display: String
) {
    Sword("sword", Material.IRON_SWORD, "剣"),
    Axe("axe", Material.IRON_AXE, "斧"),
    Bow("bow", Material.BOW, "弓"),
    Knife("knife", Material.STONE_SWORD, "短剣"),
    Wand("wand", Material.WOODEN_HOE, "杖"),
    Mace("mace", Material.WOODEN_SHOVEL, "棍");

    val item by lazy {
        CustomItemStack.create(icon, "&b$display")
    }

    companion object {
        fun getById(id: String): WeaponType? {
            return values().firstOrNull { it.id == id }
        }
    }
}