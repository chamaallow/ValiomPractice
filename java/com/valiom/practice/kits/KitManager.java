package com.valiom.practice.kits;

import com.valiom.practice.kits.types.*;
import org.bukkit.entity.Player;

public class KitManager {

    public static void giveKit(Player player, String mode) {
        switch (mode.toLowerCase()) {
            case "nodebuff":
                new NoDebuffKit().apply(player);
                break;
            case "builduhc":
                new BuildUHCKit().apply(player);
                break;
            case "combo":
                new ComboKit().apply(player);
                break;
            case "sumo":
                new SumoKit().apply(player);
                break;
            case "classic":
                new ClassicKit().apply(player);
                break;
            case "fireball":
                new FireballKit().apply(player);
                break;
            case "pearl":
                new PearlKit().apply(player);
                break;
            case "rush":
                new RushKit().apply(player);
                break;
            default:
                player.sendMessage("§cErreur: Kit inconnu pour le mode '" + mode + "'.");
                break;
        }
    }
}
