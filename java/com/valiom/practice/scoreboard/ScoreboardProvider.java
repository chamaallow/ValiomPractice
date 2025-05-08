package com.valiom.practice.scoreboard;

import org.bukkit.entity.Player;
import java.util.List;

public interface ScoreboardProvider {

    /**
     * Retourne le titre du scoreboard.
     */
    String getTitle(Player player, ScoreboardContext context);

    /**
     * Retourne les lignes à afficher dans le scoreboard.
     */
    List<String> getLines(Player player, ScoreboardContext context);
}
