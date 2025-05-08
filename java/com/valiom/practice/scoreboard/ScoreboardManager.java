package com.valiom.practice.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreboardManager {

    private final JavaPlugin plugin;
    private final Map<Player, org.bukkit.scoreboard.Scoreboard> boards = new HashMap<>();
    private ScoreboardProvider provider;

    public ScoreboardManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setProvider(ScoreboardProvider provider) {
        this.provider = provider;
    }

    public void createScoreboard(Player player, ScoreboardContext context) {
        if (provider == null) {
            plugin.getLogger().warning("§c[ValiomPractice] ScoreboardProvider non défini !");
            return;
        }

        Scoreboard bukkitBoard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = bukkitBoard.registerNewObjective("valiom", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(provider.getTitle(player, context));

        List<String> lines = provider.getLines(player, context);
        int score = lines.size();
        for (String line : lines) {
            Score scoreLine = objective.getScore(line);
            scoreLine.setScore(score--);
        }

        player.setScoreboard(bukkitBoard);
        boards.put(player, bukkitBoard);
    }

    public void removeScoreboard(Player player) {
        boards.remove(player);
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public void setContext(Player player, ScoreboardContext context) {
        createScoreboard(player, context);
    }

    public void updateAll(ScoreboardContext context) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            createScoreboard(player, context);
        }
    }
}
