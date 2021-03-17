package de.loriyt.lobbysystem.commands;

import de.loriyt.lobbysystem.Utils.Data;
import de.loriyt.lobbysystem.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * JavaDoc this file!
 * Created: 10.01.2021
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */
public class CMD_onlinetime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(args.length == 0) {
            int hours = Main.getInstance().getConfig().getInt(p.getName() + ".hours");
            int minutes = Main.getInstance().getConfig().getInt(p.getName() + ".minutes");
            int seconds = Main.getInstance().getConfig().getInt(p.getName() + ".seconds");
            p.sendMessage(Data.info + "§6Deine Online/SpielZeit beträgt: §6§l" + hours + "h " + minutes + "m " + seconds + "s");
        }

        return false;
    }
}
