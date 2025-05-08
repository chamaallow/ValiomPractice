package com.valiom.practice.kits.types;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RushKit {

    public void apply(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        safeSetItem(player, 0, new ItemStack(Material.STONE_SWORD));
        safeSetItem(player, 1, new ItemStack(Material.TNT, 16));
        safeSetItem(player, 2, new ItemStack(Material.FLINT_AND_STEEL));
        safeSetItem(player, 3, new ItemStack(Material.SANDSTONE, 64));
    }

    private void safeSetItem(Player player, int slot, ItemStack item) {
        if (slot >= 0 && slot < player.getInventory().getSize() && item != null) {
            player.getInventory().setItem(slot, item);
        }
    }
}
