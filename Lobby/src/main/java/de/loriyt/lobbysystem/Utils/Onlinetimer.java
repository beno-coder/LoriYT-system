package de.loriyt.lobbysystem.Utils;

import de.loriyt.lobbysystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Onlinetimer {



    public void startTimer() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    int hours = Main.getInstance().getConfig().getInt(players.getName() + ".hours");
                    int minutes = Main.getInstance().getConfig().getInt(players.getName() + ".minutes");
                    int seconds = Main.getInstance().getConfig().getInt(players.getName() + ".seconds");
                    int days = Main.getInstance().getConfig().getInt(players.getName() + ".days");

                    seconds++;
                    Main.getInstance().getConfig().set(players.getName() + ".seconds", seconds);
                    Main.getInstance().saveConfig();

                    if(seconds == 60) {
                        Main.getInstance().getConfig().set(players.getName() + ".seconds", 0);
                        minutes++;
                    }
                    Main.getInstance().getConfig().set(players.getName() + ".minutes", minutes);
                    Main.getInstance().saveConfig();

                    if(minutes == 60) {
                        Main.getInstance().getConfig().set(players.getName() + ".minutes", 0);
                        hours++;
                    }
                    Main.getInstance().getConfig().set(players.getName() + ".hours", hours);
                    Main.getInstance().saveConfig();
                    if(hours == 24) {
                        Main.getInstance().getConfig().set(players.getName() + ".hours", 0);
                        Main.getInstance().saveConfig();
                    }
                }
            }
        }, 20*1, 20*1);
    }
}
