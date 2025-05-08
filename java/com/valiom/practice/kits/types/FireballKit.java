package com.valiom.practice.kits.types;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FireballKit {

    public void apply(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        ItemStack fireballs = new ItemStack(Material.FIREBALL, 64);

        safeSetItem(player, 0, fireballs);
    }

    private void safeSetItem(Player player, int slot, ItemStack item) {
        if (slot >= 0 && slot < player.getInventory().getSize() && item != null) {
            player.getInventory().setItem(slot, item);
        }
    }
}
