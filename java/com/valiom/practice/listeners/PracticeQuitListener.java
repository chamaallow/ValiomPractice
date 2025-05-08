package com.valiom.practice.listeners;

import com.valiom.practice.match.MatchInstance;
import com.valiom.practice.match.MatchManager;
import com.valiom.practice.queue.QueueManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PracticeQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        QueueManager.leaveQueue(player);
        MatchManager.removePlayer(player);
    }


}
