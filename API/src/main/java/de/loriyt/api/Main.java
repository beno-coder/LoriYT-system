package de.loriyt.api;

import de.loriyt.api.Manager.datenbank.MySQL;
import de.loriyt.api.Manager.elo.ELO;
import de.loriyt.api.Manager.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;


    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        MySQL.connect();
        ELO.createTable();
        System.out.println("API is enabled");
    }


    @Override
    public void onDisable() {

    }


    public static Main getInstance() {
        return instance;
    }
}
