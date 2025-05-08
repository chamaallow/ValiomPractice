package com.valiom.practice.listeners;

import com.valiom.practice.queue.QueueManager;
import com.valiom.practice.utils.MenuTitles;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InventoryClickListener implements Listener {

    private static final Map<String, String> modeMappings = new HashMap<>();

    static {
        // Mapping DisplayName ➔ mode (QueueManager)
        modeMappings.put("§dNoDebuff", "nodebuff");
        modeMappings.put("§6BuildUHC", "builduhc");
        modeMappings.put("§cCombo", "combo");
        modeMappings.put("§eSumo", "sumo");
        modeMappings.put("§4Fireball Fight", "fireball");
        modeMappings.put("§5Pearl Fight", "pearl");
        modeMappings.put("§7Classic", "classic");
        modeMappings.put("§aParkour", "parkour"); // optionnel plus tard
        modeMappings.put("§9Rush Fast", "rush");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();

        if (title.equals(MenuTitles.UNRANKED) ||
                title.equals(MenuTitles.RANKED) ||
                title.equals(MenuTitles.EDIT_INVENTORY) ||
                title.equals(MenuTitles.SETTINGS)) {

            event.setCancelled(true);

            // Gérer uniquement Unranked pour la queue
            if (!title.equals(MenuTitles.UNRANKED)) return;

            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || !clicked.hasItemMeta() || !clicked.getItemMeta().hasDisplayName()) return;

            String itemName = clicked.getItemMeta().getDisplayName();

            String mode = modeMappings.get(itemName);
            if (mode != null) {
                player.closeInventory();
                QueueManager.joinQueue(player, mode);
            } else {
                player.sendMessage("§cErreur: Mode de jeu inconnu (" + itemName + ")");
            }
        }
    }
}
