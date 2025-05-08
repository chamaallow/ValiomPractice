package com.valiom.practice.commands;

import com.valiom.practice.match.MatchInstance;
import com.valiom.practice.match.MatchManager;
import com.valiom.practice.utils.LobbyUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (MatchManager.isInMatch(player)) {
            MatchInstance match = MatchManager.getMatch(player);
            match.forceEnd(player); // le joueur est déclaré perdant
            return true;
        }

        LobbyUtils.returnToLobby(player);
        player.sendMessage("§aRetour au lobby !");
        return true;
    }
}

