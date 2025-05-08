package com.valiom.practice;

import com.valiom.practice.commands.HubCommand;
import com.valiom.practice.data.GameModeData;
import com.valiom.practice.listeners.*;
import com.valiom.practice.scoreboard.PracticeScoreboardProvider;
import com.valiom.practice.scoreboard.ScoreboardManager;
import com.valiom.practice.world.WorldCleaner;
import com.valiom.practice.world.WorldPoolManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ValiomPractice extends JavaPlugin {

    private static ValiomPractice instance;
    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        instance = this;

        // 🌍 Nettoyage et chargement du monde
        WorldCleaner.cleanTemporaryWorlds();
        WorldPoolManager.load(getDataFolder());

        // 🧠 Chargement de la config et des données
        saveDefaultConfig();
        GameModeData.load();

        // ⚙️ Initialisation core
        scoreboardManager = new ScoreboardManager(this);
        scoreboardManager.setProvider(new PracticeScoreboardProvider(com.valiom.ValiomCore.getAPI()));

        // 🎧 Listeners
        registerListeners();

        // 🔁 Commandes
        getCommand("hub").setExecutor(new HubCommand());

        // ✅ Log
        getLogger().info("§a[ValiomPractice] Plugin activé avec succès !");
        getLogger().info("§7- Modes chargés : §e" + GameModeData.getModes().size());
    }

    @Override
    public void onDisable() {
        getLogger().info("§c[ValiomPractice] Plugin désactivé.");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new ItemInteractListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PracticeQuitListener(), this);
    }

    public static ValiomPractice getInstance() {
        return instance;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

}
