package com.valiom.practice.gui;

import com.valiom.practice.data.GameModeData;
import com.valiom.practice.model.GameMode;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PracticeMenu {

    public static void openGameModeMenu(Player player, String title) {
        Inventory inv = Bukkit.createInventory(null, 54, title);

        int[] slots = {10, 11, 12, 13, 14, 15, 16, 19, 20};
        int i = 0;
        for (GameMode mode : GameModeData.getModes().values()) {
            if (i >= slots.length) break;

            ItemStack item = new ItemStack(mode.getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(mode.getDisplayName());
            meta.setLore(mode.getLore()); // Plus tard tu pourras update ça dynamiquement
            item.setItemMeta(meta);

            inv.setItem(slots[i++], item);
        }

        player.openInventory(inv);
    }

    public static void openSettingsMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Paramètres");

        inv.setItem(20, createSimpleItem("§eActiver/Désactiver les sons", Material.NOTE_BLOCK));
        inv.setItem(22, createSimpleItem("§dChanger la couleur du pseudo", Material.NAME_TAG));
        inv.setItem(24, createSimpleItem("§6Activer le mode build", Material.ANVIL));
        inv.setItem(31, createSimpleItem("§bVoir mes statistiques", Material.BOOK));

        player.openInventory(inv);
    }

    private static ItemStack createSimpleItem(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
