package com.valiom.practice.match;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchManager {

    private static final Map<UUID, MatchInstance> activeMatches = new HashMap<>();

    public static void addMatch(Player player, MatchInstance match) {
        activeMatches.put(player.getUniqueId(), match);
    }

    public static void removePlayer(Player player) {
        MatchInstance match = activeMatches.get(player.getUniqueId());
        if (match != null) {
            // ⚠️ Finir le match avec l’autre joueur comme gagnant
            match.forceEnd(player);

            // Supprimer les deux joueurs du cache
            activeMatches.remove(match.getP1().getUniqueId());
            activeMatches.remove(match.getP2().getUniqueId());
        }
    }

    public static boolean isInMatch(Player player) {
        return activeMatches.containsKey(player.getUniqueId());
    }

    public static MatchInstance getMatch(Player player) {
        return activeMatches.get(player.getUniqueId());
    }
}
