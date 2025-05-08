package com.valiom.practice.world;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.*;

public class WorldPoolManager {

    private static final Map<String, List<File>> MAPS_BY_MODE = new HashMap<>();

    public static void load(File dataFolder) {
        File mapsFolder = new File(dataFolder, "maps");
        if (!mapsFolder.exists()) {
            Bukkit.getLogger().warning("[ValiomPractice] Aucun dossier /maps trouvé.");
            return;
        }

        for (File modeFolder : Objects.requireNonNull(mapsFolder.listFiles())) {
            if (!modeFolder.isDirectory()) continue;

            List<File> mapList = new ArrayList<>();
            for (File map : Objects.requireNonNull(modeFolder.listFiles())) {
                if (map.isDirectory() && new File(map, "level.dat").exists()) {
                    mapList.add(map);
                }
            }

            if (!mapList.isEmpty()) {
                MAPS_BY_MODE.put(modeFolder.getName().toLowerCase(), mapList);
                Bukkit.getLogger().info("§7Maps chargées pour §e" + modeFolder.getName() + "§7 : " + mapList.size());
            }
        }
    }

    public static File getRandomMap(String mode) {
        List<File> maps = MAPS_BY_MODE.get(mode.toLowerCase());

        if (maps == null || maps.isEmpty()) {
            // Si aucune map trouvée pour ce mode ➔ fallback sur Nodebuff
            maps = MAPS_BY_MODE.get("nodebuff");
            if (maps == null || maps.isEmpty()) {
                return null;
            }
        }

        return maps.get(new Random().nextInt(maps.size()));
    }
}
