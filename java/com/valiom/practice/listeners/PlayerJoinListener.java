package com.valiom.practice.listeners;

import com.valiom.practice.ValiomPractice;
import com.valiom.practice.scoreboard.ScoreboardContext;
import com.valiom.practice.utils.LobbyItems;
import com.valiom.practice.utils.LobbyUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerJoinListener implements Listener {

    public static void setupLobbyItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(0, LobbyItems.getUnrankedItem());
        player.getInventory().setItem(1, LobbyItems.getRankedItem());
        player.getInventory().setItem(7, LobbyItems.getEditInventoryItem());
        player.getInventory().setItem(8, LobbyItems.getSettingsItem());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // 🛑 Important : nouveau scoreboard, pas le main
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);

        // 🏠 Retour lobby + inventaire
        LobbyUtils.returnToLobby(player);
        setupLobbyItems(player);

        // 📊 Scoreboard latéral après chargement profil
        Bukkit.getScheduler().runTaskLater(ValiomPractice.getInstance(), () ->
                ValiomPractice.getInstance().getScoreboardManager().setContext(player, ScoreboardContext.LOBBY), 20L);
    }
}
