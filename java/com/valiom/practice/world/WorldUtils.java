package com.valiom.practice.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.UUID;

public class WorldUtils {

    public static String generateInstanceName(String mode) {
        return "duel_" + mode.toLowerCase() + "_" + UUID.randomUUID().toString().substring(0, 6);
    }

    public static World cloneAndLoadWorld(File source, String instanceName) {
        File target = new File(Bukkit.getWorldContainer(), instanceName);

        try {
            copyFolder(source.toPath(), target.toPath());

            // Supprimer uid.dat après clonage pour éviter les conflits
            File uid = new File(target, "uid.dat");
            if (uid.exists()) {
                uid.delete();
                Bukkit.getLogger().info("[ValiomPractice] uid.dat supprimé dans " + instanceName);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("[ValiomPractice] Erreur pendant la copie de la map: " + e.getMessage());
            return null;
        }

        WorldCreator creator = new WorldCreator(instanceName);
        creator.type(WorldType.FLAT);
        creator.generateStructures(false);
        return creator.createWorld();
    }

    public static void unloadAndDeleteWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            boolean unloaded = Bukkit.unloadWorld(world, false);
            if (!unloaded) {
                Bukkit.getLogger().warning("[ValiomPractice] Le monde n'a pas pu être déchargé: " + worldName);
                return;
            }
        }

        File folder = new File(Bukkit.getWorldContainer(), worldName);
        if (!folder.exists()) {
            Bukkit.getLogger().warning("[ValiomPractice] Le dossier du monde n'existe pas: " + worldName);
            return;
        }

        try {
            deleteFolder(folder.toPath());
            Bukkit.getLogger().info("✔️ Monde supprimé avec succès : " + worldName);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("[ValiomPractice] Erreur lors de la suppression du monde: " + worldName);
        }
    }

    private static void copyFolder(Path source, Path target) throws IOException {
        Files.walk(source).forEach(path -> {
            try {
                Path dest = target.resolve(source.relativize(path));
                Files.copy(path, dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void deleteFolder(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(file -> Bukkit.getLogger().info("Deleting: " + file.getPath()))
                .forEach(File::delete);
    }
}