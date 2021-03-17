package de.loriyt.api.Manager;

import de.loriyt.api.Manager.elo.ELO;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Manager {



    public static String info = "§8[§l§fINFO§8] ";
    public static String system = "§8[§l§fSystem§8] ";

    //sendet eine Nachricht in die Konsole

    public static void sendConsole(String msg) {
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    //sendet dem Spieler eine nachricht


    public static void sendM(Player p, String msg) {
        p.sendMessage(msg);
    }

    //ELO

}
