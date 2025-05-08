package com.valiom.practice.utils;

import com.valiom.practice.utils.MenuTitles;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyItems {

    public static ItemStack getUnrankedItem() {
        return createItem(Material.IRON_SWORD, "§7➤ " + MenuTitles.UNRANKED);
    }

    public static ItemStack getRankedItem() {
        return createItem(Material.DIAMOND_SWORD, "§b➤ " + MenuTitles.RANKED);
    }

    public static ItemStack getEditInventoryItem() {
        return createItem(Material.BOOK, "§d➤ " + MenuTitles.EDIT_INVENTORY);
    }

    public static ItemStack getSettingsItem() {
        return createItem(Material.ANVIL, "§e➤ " + MenuTitles.SETTINGS);
    }

    private static ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
