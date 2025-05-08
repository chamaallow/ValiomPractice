package com.valiom.practice.scoreboard;

import com.valiom.ValiomCore;
import com.valiom.api.ValiomAPI;
import com.valiom.models.Profile;
import com.valiom.utils.LuckPermUtils;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PracticeScoreboardProvider implements ScoreboardProvider {

    private final ValiomAPI api;

    public PracticeScoreboardProvider(ValiomAPI api) {
        this.api = api;
    }

    @Override
    public String getTitle(Player player, ScoreboardContext context) {
        return "§9§lValiom §f§lPractice";
    }

    @Override
    public List<String> getLines(Player player, ScoreboardContext context) {
        List<String> lines = new ArrayList<>();
        Profile profile = ValiomCore.getAPI().getProfileManager().getProfile(player.getUniqueId());
        String prefix = LuckPermUtils.getPrefix(player).replace("&", "§");


        if (profile == null) {
            lines.add("§7Chargement du profil...");
            return lines;
        }

        switch (context) {
            case LOBBY:
                lines.add("§8§m----------------");
                lines.add("§7» §6" + "§l"+ player.getName());
                lines.add("§1");
                lines.add("§7» §fGrade: " + prefix);
                lines.add("§7» §fVictoires: §a" + profile.getWins());
                lines.add("§7» §fDéfaites: §c" + profile.getLosses());
                lines.add("§2");
                lines.add("§eplay.valiom.fr");
                lines.add("§8§m----------------");
                break;

            case PRACTICE_QUEUE:
                lines.add("§7§m-------------------");
                lines.add("§eEn file d'attente...");
                lines.add("");
                lines.add("§fMode sélectionné:");
                lines.add("");
                lines.add("§ewww.valiom.fr");
                lines.add("§7§m-------------------");
                break;

            case PRACTICE_MATCH:
                lines.add("§7§m-------------------");
                lines.add("§eMatch en cours");
                lines.add("");
                lines.add("§fToi: §a" + player.getName());
                lines.add("§fPing: §e" + getPing(player) + "ms");
                lines.add("§fAdversaire: §c???");
                lines.add("");
                lines.add("§ewww.valiom.fr");
                lines.add("§7§m-------------------");
                break;
        }

        return lines;
    }


    private int getPing(Player player) {
        try {
            return ((CraftPlayer) player).getHandle().ping;
        } catch (Exception e) {
            return 0;
        }
    }
}
