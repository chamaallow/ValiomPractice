package com.valiom.practice.kits.types;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

public class NoDebuffKit {

    public void apply(Player player) {
        // Clear l'inventaire et l'armure
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        // Crée les items
        ItemStack sword = createSword();
        ItemStack pearls = new ItemStack(Material.ENDER_PEARL, 16);
        ItemStack speedPotion = createSpeedPotion();
        ItemStack fireResPotion = createFireResPotion();
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack splashHeal = createSplashHeal();

        // Set hotbar
        safeSetItem(player, 0, sword);
        safeSetItem(player, 1, pearls);
        safeSetItem(player, 2, speedPotion);
        safeSetItem(player, 3, fireResPotion);
        safeSetItem(player, 8, steak);

        // Set armure
        safeSetArmor(player);

        // Remplir tout l'inventaire de splash heal
        for (int slot = 9; slot <= 35; slot++) {
            safeSetItem(player, slot, splashHeal.clone());
        }
    }

    private ItemStack createSword() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        sword.setItemMeta(meta);
        return sword;
    }

    private void safeSetArmor(Player player) {
        ItemStack helmet = createArmorPiece(Material.DIAMOND_HELMET);
        ItemStack chestplate = createArmorPiece(Material.DIAMOND_CHESTPLATE);
        ItemStack leggings = createArmorPiece(Material.DIAMOND_LEGGINGS);
        ItemStack boots = createArmorPiece(Material.DIAMOND_BOOTS);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
    }

    private ItemStack createArmorPiece(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createSplashHeal() {
        return new ItemStack(Material.POTION, 1, (short) 16421);
    }

    private ItemStack createSpeedPotion() {
        return new ItemStack(Material.POTION, 1, (short) 8194);
    }

    private ItemStack createFireResPotion() {
        return new ItemStack(Material.POTION, 1, (short) 8259);
    }

    private void safeSetItem(Player player, int slot, ItemStack item) {
        if (slot >= 0 && slot < player.getInventory().getSize() && item != null) {
            player.getInventory().setItem(slot, item);
        }
    }
}
