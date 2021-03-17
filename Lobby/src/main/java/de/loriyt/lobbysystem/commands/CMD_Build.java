package de.loriyt.lobbysystem.commands;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.driver.permission.PermissionUserGroupInfo;
import de.dytanic.cloudnet.ext.cloudperms.CloudPermissionsPermissionManagement;
import de.loriyt.lobbysystem.Utils.Data;
import de.loriyt.lobbysystem.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CMD_Build implements CommandExecutor {
        public static ArrayList<Player> build = new ArrayList<>();

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                IPermissionUser permsuser = CloudPermissionsPermissionManagement.getInstance().getUser(p.getUniqueId());
                if (p.hasPermission("build")) {
                    if (build.contains(p)) {
                        build.remove(p);
                        p.sendMessage(Data.system + "§cDu kannst nichtmehr bauen!");
                        p.setGameMode(GameMode.SURVIVAL);

                        ItemStack kompass = new ItemStack(Material.COMPASS);
                        ItemMeta itemMeta = kompass.getItemMeta();
                        itemMeta.setDisplayName("§8〣 §f§lKompass");
                        kompass.setItemMeta(itemMeta);
                        p.getInventory().setItem(0, kompass);

                        Main.getInstance().itemManager.waiting = new ItemStack(Material.FIREWORK_CHARGE);
                        ItemMeta itemMeta1 = Main.getInstance().itemManager.waiting.getItemMeta();
                        itemMeta1.setDisplayName("§8〣 §8§lEnderperle");
                        Main.getInstance().itemManager.waiting.setItemMeta(itemMeta1);
                        Main.getInstance().itemManager.enderpaeraad = new ItemStack(Material.ENDER_PEARL);
                        ItemMeta itemMeta2 = Main.getInstance().itemManager.enderpaeraad.getItemMeta();
                        itemMeta2.setDisplayName("§8〣 §5§lEnderperle");
                        Main.getInstance().itemManager.enderpaeraad.setItemMeta(itemMeta2);
                        p.getInventory().setItem(4, Main.getInstance().itemManager.enderpaeraad);
                        p.updateInventory();
                    } else {
                        build.add(p);
                        p.sendMessage(Data.system + "§aDu kannst nun bauen!");
                        p.sendMessage(Data.system + permsuser.getGroups().stream().map(PermissionUserGroupInfo::getGroup).collect(Collectors.joining(",")));
                        p.getInventory().clear();
                        p.setGameMode(GameMode.CREATIVE);
                    }
                }
            }

            return false;
        }
    }
