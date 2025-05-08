package com.valiom.practice.match;

import com.valiom.practice.kits.KitManager;
import com.valiom.practice.queue.QueueManager;
import com.valiom.practice.utils.LobbyUtils;
import com.valiom.practice.utils.TitleUtils;
import com.valiom.practice.world.WorldUtils;
import com.valiom.practice.ValiomPractice;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class MatchInstance implements Listener {

    private final Player p1;
    private final Player p2;
    private final World world;
    private final String instanceName;
    private boolean started = false;
    private boolean ended = false;

    public MatchInstance(Player p1, Player p2, String mode, File sourceMap) {
        this.p1 = p1;
        this.p2 = p2;

        this.instanceName = WorldUtils.generateInstanceName(mode);
        this.world = WorldUtils.cloneAndLoadWorld(sourceMap, instanceName);

        if (world == null) {
            p1.sendMessage("§cErreur lors du chargement de l'arène.");
            p2.sendMessage("§cErreur lors du chargement de l'arène.");
            return;
        }

        Location spawn1 = new Location(world, 8.446, 9, -15.486, 0, 0);
        Location spawn2 = new Location(world, 8.601, 9, 39.529, 180, 0);

        p1.teleport(spawn1);
        p2.teleport(spawn2);

        KitManager.giveKit(p1, mode);
        KitManager.giveKit(p2, mode);

        MatchManager.addMatch(p1, this);
        MatchManager.addMatch(p2, this);

        startCountdown();

        Bukkit.getPluginManager().registerEvents(this, ValiomPractice.getInstance());

        Bukkit.broadcastMessage("§aMatch lancé entre §e" + p1.getName() + " §aet §e" + p2.getName() + " §7(mode: " + mode + ")");
    }

    private void startCountdown() {
        freezePlayer(p1);
        freezePlayer(p2);

        final int[] countdown = {5};

        new BukkitRunnable() {
            @Override
            public void run() {
                if (countdown[0] == 0) {
                    started = true;
                    unfreezePlayer(p1);
                    unfreezePlayer(p2);

                    TitleUtils.sendTitle(p1, "§aGO!", "", 5, 15, 5);
                    TitleUtils.sendTitle(p2, "§aGO!", "", 5, 15, 5);

                    p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 2f);
                    p2.playSound(p2.getLocation(), Sound.LEVEL_UP, 1f, 2f);

                    Bukkit.getScheduler().runTaskLater(ValiomPractice.getInstance(), () -> {
                        TitleUtils.sendTitle(p1, "", "", 0, 1, 0);
                        TitleUtils.sendTitle(p2, "", "", 0, 1, 0);
                    }, 20L);

                    cancel();
                } else {
                    TitleUtils.sendTitle(p1, "§e" + countdown[0], "", 0, 20, 0);
                    TitleUtils.sendTitle(p2, "§e" + countdown[0], "", 0, 20, 0);

                    p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 1f, 1f);
                    p2.playSound(p2.getLocation(), Sound.NOTE_PLING, 1f, 1f);

                    countdown[0]--;
                }
            }
        }.runTaskTimer(ValiomPractice.getInstance(), 0L, 20L);
    }

    private void freezePlayer(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 255, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 128, false, false));
        player.setWalkSpeed(0f);
    }

    private void unfreezePlayer(Player player) {
        player.removePotionEffect(PotionEffectType.SLOW);
        player.removePotionEffect(PotionEffectType.JUMP);
        player.setWalkSpeed(0.2f);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player damaged = (Player) event.getEntity();

        if (!damaged.equals(p1) && !damaged.equals(p2)) return;
        if (!started || ended) {
            event.setCancelled(true);
            return;
        }

        double finalHealth = damaged.getHealth() - event.getFinalDamage();
        if (finalHealth <= 0) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(ValiomPractice.getInstance(), () -> {
                damaged.setHealth(20.0);
                endMatch(damaged);
            }, 1L);
        }
    }

    public void forceEnd(Player loser) {
        endMatch(loser);
    }

    private void endMatch(Player loser) {
        if (ended) return;
        ended = true;

        HandlerList.unregisterAll(this);

        Player winner = (loser.equals(p1)) ? p2 : p1;

        QueueManager.leaveQueue(p1);
        QueueManager.leaveQueue(p2);

        LobbyUtils.returnToLobby(loser);
        LobbyUtils.returnToLobby(winner);

        MatchManager.removePlayer(p1);
        MatchManager.removePlayer(p2);

        Bukkit.broadcastMessage("§6[Practice] §e" + winner.getName() + " §aa gagné contre §c" + loser.getName() + "§7 !");

        Bukkit.getScheduler().runTaskLater(ValiomPractice.getInstance(), () ->
                WorldUtils.unloadAndDeleteWorld(instanceName), 40L);
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }
}
