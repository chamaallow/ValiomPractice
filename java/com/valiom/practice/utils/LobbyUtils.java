package com.valiom.practice.utils;

import com.valiom.practice.ValiomPractice;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LobbyUtils {

    public static void returnToLobby(Player player) {
        FileConfiguration config = ValiomPractice.getInstance().getConfig();

        World world = Bukkit.getWorld(config.getString("lobby.world"));
        double x = config.getDouble("lobby.x");
        double y = config.getDouble("lobby.y");
        double z = config.getDouble("lobby.z");
        float yaw = (float) config.getDouble("lobby.yaw");
        float pitch = (float) config.getDouble("lobby.pitch");

        if (world != null) {
            Location loc = new Location(world, x, y, z, yaw, pitch);
            player.teleport(loc);
        } else {
            player.sendMessage("§cLe monde du lobby est introuvable.");
            return;
        }

        // Reset du joueur
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setWalkSpeed(0.2f);
        player.setFireTicks(0);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));

        // Items du lobby
        player.getInventory().setItem(0, LobbyItems.getUnrankedItem());
        player.getInventory().setItem(1, LobbyItems.getRankedItem());
        player.getInventory().setItem(7, LobbyItems.getEditInventoryItem());
        player.getInventory().setItem(8, LobbyItems.getSettingsItem());

        player.updateInventory();
    }
}
