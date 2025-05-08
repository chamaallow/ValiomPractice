package com.valiom.practice.data;

import com.valiom.practice.model.GameMode;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameModeData {

    private static final Map<String, GameMode> MODES = new LinkedHashMap<>();

    public static void load() {
        MODES.put("NoDebuff", new GameMode("NoDebuff", "§dNoDebuff", Material.POTION, Arrays.asList(
                "",
                "§7Combat à la potion très rapide.",
                "§7Outplay ton adversaire avec tes réflexes.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("BuildUHC", new GameMode("BuildUHC", "§6BuildUHC", Material.LAVA_BUCKET, Arrays.asList(
                "",
                "§7Place, rodes, lave, heals.",
                "§7PvP stratégique avec tout l’attirail.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("Sumo", new GameMode("Sumo", "§eSumo", Material.LEASH, Arrays.asList(
                "",
                "§7Le but ? Faire tomber ton adversaire.",
                "§7Aucun dégât, juste du KB pur.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("Combo", new GameMode("Combo", "§cCombo", Material.IRON_SWORD, Arrays.asList(
                "",
                "§7Speed 2, KB boosté, no cooldown.",
                "§7Combo infini si tu gères.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("Fireball", new GameMode("Fireball", "§4Fireball Fight", Material.FIREBALL, Arrays.asList(
                "",
                "§7Balance des boules de feu pour exploser",
                "§7tes ennemis dans l’arène.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("Pearl", new GameMode("Pearl", "§5Pearl Fight", Material.ENDER_PEARL, Arrays.asList(
                "",
                "§7Déplacement rapide avec perles d’Ender.",
                "§7PvP mobile et imprévisible.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("Classic", new GameMode("Classic", "§7Classic", Material.STONE_SWORD, Arrays.asList(
                "",
                "§7PvP simple, sans rod ni potion.",
                "§7Juste ton épée et ton skill.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("Parkour", new GameMode("Parkour", "§aParkour", Material.LADDER, Arrays.asList(
                "",
                "§7Sauts, réflexes, précision.",
                "§7Maîtrise ton mouvement.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));

        MODES.put("Rush", new GameMode("Rush", "§9Rush Fast", Material.TNT, Arrays.asList(
                "",
                "§7Mini bridge rapide et explosif.",
                "§7Traverse et rush plus vite que l’autre.",
                "",
                "§8En file : §f0",
                "§8En match : §fAucun"
        )));
    }

    public static Map<String, GameMode> getModes() {
        return MODES;
    }
}
