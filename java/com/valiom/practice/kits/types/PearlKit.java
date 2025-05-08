package com.valiom.practice.kits.types;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PearlKit {

    public void apply(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

        safeSetItem(player, 0, new ItemStack(Material.DIAMOND_SWORD));
        safeSetItem(player, 1, new ItemStack(Material.ENDER_PEARL, 64));
    }

    private void safeSetItem(Player player, int slot, ItemStack item) {
        if (slot >= 0 && slot < player.getInventory().getSize() && item != null) {
            player.getInventory().setItem(slot, item);
        }
    }
}
