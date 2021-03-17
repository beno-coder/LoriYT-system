package de.loriyt.lobbysystem.CloudnetAPI.RangSystem;

import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.ext.cloudperms.CloudPermissionsManagement;
import de.loriyt.lobbysystem.Main;
import de.loriyt.lobbysystem.Utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

//cloud perms user <username> add group <group> lifetime
//cloud perms user <username> add group <group> <time in days>

public class RangCommand implements CommandExecutor {

    private int task1;
    private int task;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        String group;
        if(args.length == 0) {
            p.sendMessage(Data.system + "§cDu hast den " + Main.getInstance().getCloudPermission().getRankColor(p.getUniqueId()).replace("&", "§") + Main.getInstance().getCloudPermission().getRankName(p.getUniqueId()) + " §cRang");
            return true;
        }else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            p.sendMessage(Data.system + "§cDer Spieler " + target.getName() +  " hat den" + Main.getInstance().getCloudPermission().getRankColor(target.getUniqueId()).replace("&", "§") + Main.getInstance().getCloudPermission().getRankName(target.getUniqueId()) + " §cRang");
            return true;
        }
        if(p.hasPermission("rang")) {
            if(args.length == 3) {
                if(args[0].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    group = args[2];
                        if(target == null) {
                            p.sendMessage(Data.system + "§cSystem sucht Spieler...");
                            task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    p.sendMessage(Data.system + "§cSystem sucht Spieler...");
                                    Bukkit.getScheduler().cancelTask(task);
                                }
                            }, 20*5);
                            task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    p.sendMessage(Data.system + "§cUnser System konnte den Spieler nicht finden ");
                                    Bukkit.getScheduler().cancelTask(task1);
                                }
                            }, 20*10);

                        }else if(CloudPermissionsManagement.getInstance().getUser(target.getUniqueId()) != null){
                            if(CloudPermissionsManagement.getInstance().getGroup(group) == null) {
                                p.sendMessage(Data.system + "§cSystem sucht die Gruppe...");
                                task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        p.sendMessage(Data.system + "§cSystem sucht die Gruppe...");
                                        Bukkit.getScheduler().cancelTask(task);
                                    }
                                }, 20*5);
                                task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        p.sendMessage(Data.system + "§cUnser System konnte die Gruppe nicht finden ");
                                        Bukkit.getScheduler().cancelTask(task1);
                                    }
                                }, 20*10);
                            }
                        }

                    if(CloudPermissionsManagement.getInstance().getUser(target.getUniqueId()) != null){
                        if(CloudPermissionsManagement.getInstance().getGroup(group) != null) {
                            Main.getInstance().getCloudPermission().addGroup(target.getUniqueId(), group);
                            p.sendMessage(Data.system + "§cDer Spieler §b" + target.getName() + "§c hat die Gruppe §b" + group + " §cerhalten!");
                        }
                        }
                    }
                //REMOVE

                if(args[0].equalsIgnoreCase("remove")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    group = args[2];
                    if(target == null) {
                        p.sendMessage(Data.system + "§cSystem sucht Spieler...");
                        task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                p.sendMessage(Data.system + "§cSystem sucht Spieler...");
                                Bukkit.getScheduler().cancelTask(task);
                            }
                        }, 20*5);
                        task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                p.sendMessage(Data.system + "§cUnser System konnte den Spieler nicht finden ");
                                Bukkit.getScheduler().cancelTask(task1);
                            }
                        }, 20*10);

                    }else if(CloudPermissionsManagement.getInstance().getUser(target.getUniqueId()) != null){
                        if(CloudPermissionsManagement.getInstance().getGroup(group) == null) {
                            p.sendMessage(Data.system + "§cSystem sucht die Gruppe...");
                            task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    p.sendMessage(Data.system + "§cSystem sucht die Gruppe...");
                                    Bukkit.getScheduler().cancelTask(task);
                                }
                            }, 20*5);
                            task1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    p.sendMessage(Data.system + "§cUnser System konnte die Gruppe nicht finden ");
                                    Bukkit.getScheduler().cancelTask(task1);
                                }
                            }, 20*1);
                        }
                    }

                    if(CloudPermissionsManagement.getInstance().getUser(target.getUniqueId()) != null){
                        if(CloudPermissionsManagement.getInstance().getGroup(group) != null) {
                            Main.getInstance().getCloudPermission().removeGroup(target.getUniqueId(), group);
                            p.sendMessage(Data.system + "§cDer Spieler §b" + target.getName() + "§c hat die Gruppe §b" + group + " §cverloren!");

                        }
                    }
                }

                //ADDTEMP


            }else if(args.length == 4 ||args[0].equalsIgnoreCase("add")) {
                Player target = Bukkit.getPlayer(args[1]);
                group = args[2];
                int day = Integer.parseInt(args[3]);
                if(CloudPermissionsManagement.getInstance().getUser(target.getUniqueId()) != null) {
                    if(CloudPermissionsManagement.getInstance().getGroup(group) != null) {
                        Main.getInstance().getCloudPermission().addTempGroup(target.getUniqueId(), group, day, TimeUnit.DAYS);
                        if(day == 1) {
                            p.sendMessage(Data.system + "§cDer Spieler §b" + target.getName() + "§c hat die Gruppe §b" + group + " §cerhalten, für §b" + day + "§cTag!");
                        }else {
                            p.sendMessage(Data.system + "§cDer Spieler §b" + target.getName() + "§c hat die Gruppe §b" + group + " §cerhalten, für §b" + day + "§cTage!");
                        }

                    }
                }
            }
        }

        return false;
    }
}
