package com.valiom.practice.kits.types;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ComboKit {

    public void apply(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

        ItemStack sword = createSword();

        safeSetItem(player, 0, sword);
        safeSetArmor(player);
    }

    private ItemStack createSword() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        sword.setItemMeta(meta);
        return sword;
    }

    private void safeSetArmor(Player player) {
        player.getInventory().setHelmet(createArmorPiece(Material.DIAMOND_HELMET));
        player.getInventory().setChestplate(createArmorPiece(Material.DIAMOND_CHESTPLATE));
        player.getInventory().setLeggings(createArmorPiece(Material.DIAMOND_LEGGINGS));
        player.getInventory().setBoots(createArmorPiece(Material.DIAMOND_BOOTS));
    }

    private ItemStack createArmorPiece(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        item.setItemMeta(meta);
        return item;
    }

    private void safeSetItem(Player player, int slot, ItemStack item) {
        if (slot >= 0 && slot < player.getInventory().getSize() && item != null) {
            player.getInventory().setItem(slot, item);
        }
    }
}
