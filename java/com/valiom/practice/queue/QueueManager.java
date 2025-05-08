package com.valiom.practice.queue;

import com.valiom.ValiomCore;
import com.valiom.practice.ValiomPractice;
import com.valiom.practice.match.MatchInstance;
import com.valiom.practice.world.WorldPoolManager;
import com.valiom.practice.scoreboard.ScoreboardContext;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class QueueManager {

    private static final Map<String, Queue<Player>> queues = new HashMap<>();

    public static void joinQueue(Player player, String mode) {
        ValiomPractice.getInstance().getScoreboardManager().setContext(player, ScoreboardContext.PRACTICE_QUEUE);
        queues.putIfAbsent(mode.toLowerCase(), new LinkedList<>());

        Queue<Player> queue = queues.get(mode.toLowerCase());
        if (queue.contains(player)) {
            player.sendMessage("§cTu es déjà en queue pour ce mode.");
            return;
        }

        queue.add(player);
        player.sendMessage("§aEn attente d'un adversaire en §e" + mode);

        if (queue.size() >= 2) {
            Player p1 = queue.poll();
            Player p2 = queue.poll();

            File map = WorldPoolManager.getRandomMap(mode);
            if (map == null) {
                p1.sendMessage("§cErreur: aucune map trouvée pour " + mode + ".");
                p2.sendMessage("§cErreur: aucune map trouvée pour " + mode + ".");
                return;
            }

            new MatchInstance(p1, p2, mode, map); // On passe le BON mode ici maintenant ✅
        }
    }

    public static void leaveQueue(Player player) {
        for (Queue<Player> queue : queues.values()) {
            queue.remove(player);
        }
    }

    public static boolean isInQueue(Player player) {
        return queues.values().stream().anyMatch(queue -> queue.contains(player));
    }
}
