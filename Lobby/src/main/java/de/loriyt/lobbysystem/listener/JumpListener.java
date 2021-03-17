package de.loriyt.lobbysystem.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


public class  JumpListener implements Listener {

    @EventHandler
    public void wonJumpPad(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getLocation().getBlock().getType() == Material.STONE_PLATE) {
            if(p.getLocation().subtract(0.0D, 2.0D, 0.0D).getBlock().getType() == Material.REDSTONE_BLOCK) {
                Vector v = p.getLocation().getDirection().multiply(3D).setY(1);
                p.setVelocity(v);
            }
        }
    }


    @EventHandler
    public void onToggle$(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.SURVIVAL) {
            Vector v = p.getLocation().getDirection().multiply(3D).setY(1);
            p.setVelocity(v);
        }
    }
}
