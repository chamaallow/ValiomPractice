package com.valiom.practice.utils;

import com.valiom.practice.gui.PracticeMenu;
import org.bukkit.entity.Player;

public class MenuDispatcher {

    public static void open(Player player, String title) {
        switch (title) {
            case "Unranked":
            case "Ranked":
            case "Modifier votre inventaire":
                PracticeMenu.openGameModeMenu(player, title);
                break;

            case "Paramètres":
                PracticeMenu.openSettingsMenu(player);
                break;

            default:
                // Si besoin, envoie un message d’erreur ou ignore
                break;
        }
    }
}
