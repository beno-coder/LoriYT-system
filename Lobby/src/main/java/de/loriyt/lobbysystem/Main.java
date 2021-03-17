package de.loriyt.lobbysystem;

import de.loriyt.lobbysystem.CloudnetAPI.CloudPermission;
import de.loriyt.lobbysystem.CloudnetAPI.RangSystem.RangCommand;
import de.loriyt.lobbysystem.Utils.ScoreboardUtil;
import de.loriyt.lobbysystem.listener.*;
import de.loriyt.lobbysystem.commands.CMD_Build;
import de.loriyt.lobbysystem.commands.CMD_Shop;
import de.loriyt.lobbysystem.commands.CMD_onlinetime;
import de.loriyt.lobbysystem.Utils.Onlinetimer;
import de.loriyt.lobbysystem.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    public ScoreboardUtil scoreboardUtil = new ScoreboardUtil();
    public Onlinetimer onlinetimer = new Onlinetimer();
    public ItemManager itemManager = new ItemManager();
    private CloudPermission cloudPermission;

    private static Main instance;

    public void onEnable() {
        instance = this;
        loadConfig();
        onlinetimer.startTimer();
        scoreboardUtil.start();
        this.cloudPermission = new CloudPermission();
        //Commands
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
        Bukkit.getPluginManager().registerEvents(new JumpListener(), this );
        Bukkit.getPluginManager().registerEvents(new Villagerhandler(), this);
        getCommand("spawnshop").setExecutor(new CMD_Shop());
        getCommand("build").setExecutor(new CMD_Build());
        getCommand("onlinetime").setExecutor(new CMD_onlinetime());
        getCommand("rank").setExecutor(new RangCommand());
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().getWorld("LobbyIslandd").setDifficulty(Difficulty.PEACEFUL);
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {

    }

    //loadconfig
    public void loadConfig() {
    getConfig().options().copyDefaults(true);
        saveConfig();
    }


    public static Main getInstance() {
        return instance;
    }


    public CloudPermission getCloudPermission() {
        return this.cloudPermission;
    }
}
