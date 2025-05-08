package com.valiom.practice.listeners;

import com.valiom.practice.utils.MenuDispatcher;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || !item.hasItemMeta()) return;

        // 👉 Vérifie d'abord : seulement clic droit
        Action action = event.getAction();
        if (!(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            return; // Clic gauche = on ignore
        }

        String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
        if (name == null) return;

        event.setCancelled(true);

        // Détection de l'item cliqué et ouverture du bon menu
        switch (name) {
            case "➤ Unranked":
            case "➤ Ranked":
            case "➤ Modifier votre inventaire":
            case "➤ Paramètres":
                MenuDispatcher.open(player, name.replace("➤ ", ""));
                break;
            default:
                break;
        }
    }
}
