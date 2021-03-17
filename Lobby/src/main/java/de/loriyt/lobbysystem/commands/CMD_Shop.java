package de.loriyt.lobbysystem.commands;

import de.loriyt.lobbysystem.Utils.ShopVillager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CMD_Shop implements CommandExecutor {
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cs instanceof Player) {
            final Player player = (Player) cs;
            if(player.hasPermission("shop.create")) {
                if(args.length == 0) {
                    new ShopVillager().spawnMob();
                    player.sendMessage("§aDer Shop wurde erstellt/gesetzt");
                } else
                    player.sendMessage("§cBitte benutze §6/shop§c");
            } else
                player.sendMessage("§cDazu hast du keine Rechte");
        }
        return false;
    }
}
