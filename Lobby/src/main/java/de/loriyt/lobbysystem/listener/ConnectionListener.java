package de.loriyt.lobbysystem.listener;

import de.loriyt.api.Manager.elo.ELO;
import de.loriyt.lobbysystem.commands.CMD_Build;
import de.loriyt.lobbysystem.Utils.Data;
import de.loriyt.lobbysystem.Main;
import de.loriyt.lobbysystem.effect.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConnectionListener implements Listener {

    private int task;
    private Main pl;

    public ConnectionListener(Main main) {
        this.pl = main;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        ELO.addElo(p,3000);
        if(!p.hasPlayedBefore()) {
            Location loc = new Location(Bukkit.getWorld("LobbyIslandd"), -179.477, 38.5, 843.155);
            loc.setYaw((float) 179);
            loc.setPitch((float) 0.4);
            p.teleport(loc);
        }
        if (!ELO.isRegistered(p)) {
            ELO.register(p);
        }
        Location loc = new Location(Bukkit.getWorld("LobbyIslandd"), -179.477, 38.5, 843.155);
        loc.setYaw((float) 179);
        loc.setPitch((float) 0.4);
        p.teleport(loc);
        p.setLevel(Bukkit.getOnlinePlayers().size());
        p.setExp((float) Bukkit.getOnlinePlayers().size() / Bukkit.getMaxPlayers());
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*10, 1));
        p.playSound(p.getLocation(), Sound.EXPLODE, 2, 2);
        e.setJoinMessage(null);
        Main.getInstance().scoreboardUtil.setBoard(p);
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    Main.getInstance().scoreboardUtil.updateScore(all);
                }
            }
        }, 0, 20);
        if (p.hasPermission("admin")) {
            p.sendMessage(Data.system + "§7Du bist als Owner verbunden!");
        } else {
            p.sendMessage(Data.system + "§7Du bist als Spieler verbunden!");
        }
        Location part = new Location(Bukkit.getWorld("LobbyIslandd"), -88, 120, -1);
        spawnParticles(part);
        p.setHealth(20.0D);
        p.setFoodLevel(20);
        p.setFireTicks(0);
        p.getInventory().clear();
        Main.getInstance().itemManager.setItem(p);
        p.setGameMode(GameMode.SURVIVAL);
        CMD_Build.build.remove(p);
        Main.getInstance().itemManager.lobbyswitcher(p);

    }

    private void spawnParticles(Location location) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 360; i+=360/20) {
                    double angle = (i*Math.PI / 180);
                    double x = 0.5 * Math.cos(angle);
                    double z = 0.5 * Math.sin(angle);
                    Location particleLoc = location.add(x, 0, z);
                    ParticleEffect.SPELL_MOB.display(1,1,1, 3, 0, particleLoc, 100);
                    Bukkit.getScheduler().cancelTask(task);
                }
            }
        }, 0, 20);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}
