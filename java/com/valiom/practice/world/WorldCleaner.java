package com.valiom.practice.world;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class WorldCleaner {

    public static void cleanTemporaryWorlds() {
        File worldContainer = Bukkit.getWorldContainer();
        File[] folders = worldContainer.listFiles();

        if (folders == null) {
            Bukkit.getLogger().warning("[ValiomPractice] Aucun dossier trouvé dans le world container.");
            return;
        }

        for (File folder : folders) {
            if (folder.isDirectory() && folder.getName().startsWith("duel_")) {
                String worldName = folder.getName();

                // Déchargement du monde s'il est encore actif
                World world = Bukkit.getWorld(worldName);
                if (world != null) {
                    boolean success = Bukkit.unloadWorld(world, false);
                    if (!success) {
                        Bukkit.getLogger().warning("[ValiomPractice] Impossible de décharger le monde : " + worldName);
                        continue;
                    }
                }

                try {
                    deleteFolder(folder.toPath());
                    Bukkit.getLogger().info("[ValiomPractice] Monde orphelin supprimé : " + worldName);
                } catch (IOException e) {
                    Bukkit.getLogger().warning("[ValiomPractice] Erreur suppression monde : " + worldName + " - " + e.getMessage());
                }
            }
        }
    }

    private static void deleteFolder(Path path) throws IOException {
        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }
}
