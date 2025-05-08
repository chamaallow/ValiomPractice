package com.valiom.practice.kits.types;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuildUHCKit {

    public void apply(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        // Equipement principal
        safeSetItem(player, 0, createSword());
        safeSetItem(player, 1, new ItemStack(Material.FISHING_ROD));
        safeSetItem(player, 2, createBow());
        safeSetItem(player, 3, new ItemStack(Material.WATER_BUCKET));
        safeSetItem(player, 4, new ItemStack(Material.WATER_BUCKET));
        safeSetItem(player, 5, new ItemStack(Material.LAVA_BUCKET));
        safeSetItem(player, 6, new ItemStack(Material.LAVA_BUCKET));
        safeSetItem(player, 7, createGoldenApples());
        safeSetItem(player, 8, createGoldenHeads());

        // Equipement secondaire
        player.getInventory().setItem(9, new ItemStack(Material.DIAMOND_PICKAXE));
        player.getInventory().setItem(10, new ItemStack(Material.ARROW, 20));
        player.getInventory().setItem(11, new ItemStack(Material.WOOD, 64)); // Blocs pour construire

        // Armure
        safeSetArmor(player);
    }

    private ItemStack createSword() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true); // Sharpness III
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        sword.setItemMeta(meta);
        return sword;
    }

    private ItemStack createBow() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true); // Optionnel : Infinity pour 1 flèche infinie
        bow.setItemMeta(meta);
        return bow;
    }

    private ItemStack createGoldenApples() {
        return new ItemStack(Material.GOLDEN_APPLE, 6);
    }

    private ItemStack createGoldenHeads() {
        return new ItemStack(Material.GOLDEN_APPLE, 3);
        // On simule ici les "Golden Heads" comme étant des Golden Apple,
        // à toi ensuite d'écouter le PlayerItemConsumeEvent si tu veux mettre un petit boost custom.
    }

    private void safeSetArmor(Player player) {
        player.getInventory().setHelmet(createArmorPiece(Material.DIAMOND_HELMET, true));
        player.getInventory().setChestplate(createArmorPiece(Material.DIAMOND_CHESTPLATE, false));
        player.getInventory().setLeggings(createArmorPiece(Material.DIAMOND_LEGGINGS, false));
        player.getInventory().setBoots(createArmorPiece(Material.DIAMOND_BOOTS, true));
    }

    private ItemStack createArmorPiece(Material material, boolean projectileProtection) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (projectileProtection) {
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true); // Protection contre projectiles II
        } else {
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true); // Protection II normal
        }
        meta.addEnchant(Enchantment.DURABILITY, 3, true); // Unbreaking III pour toutes pièces
        item.setItemMeta(meta);
        return item;
    }

    private void safeSetItem(Player player, int slot, ItemStack item) {
        if (slot >= 0 && slot < player.getInventory().getSize() && item != null) {
            player.getInventory().setItem(slot, item);
        }
    }
}
