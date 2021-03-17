package de.loriyt.api.Manager.listeners;

import de.loriyt.api.Main;
import de.loriyt.api.Manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {

    int task;
    int task1;
    int task2;

    @EventHandler
    public void onjoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        Manager.sendM(p,Manager.system + "§cBitte gebe uns einen Moment, wir müssen deine §b§lDATEN überprüfen");
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*10, 1000));

        task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Manager.sendM(p, "§b§lDATEN §cwerden in §b§lDATENBANK §ceingetragen!");
                Bukkit.getScheduler().cancelTask(task);

            }
        }, 20*5);

        task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Manager.sendM(p, "§cDeine §b§lDATEN §cwurden mit erfolg geprüft und in der §b§lDATENBANK §ceingetragen");
                Bukkit.getScheduler().cancelTask(task1);
            }
        }, 20*10);

        task2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Manager.sendM(p, "§c§lVIELSPAß auf dem §b§lNETZWERk | §f§lLoriYT.de");
                Bukkit.getScheduler().cancelTask(task2);
            }
        }, 20*15);


    }
}
