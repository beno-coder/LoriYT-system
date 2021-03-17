package de.loriyt.lobbysystem.listener;

import de.loriyt.lobbysystem.commands.CMD_Build;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class BlockEvents implements Listener {


        @EventHandler
        public void onClick(InventoryClickEvent e) {
            if (!CMD_Build.build.contains(e.getWhoClicked())) {
                    e.setCancelled(true);
                }
            }



        @EventHandler
        public void onRes(PlayerRespawnEvent e) {
            Player p = e.getPlayer();
            Location loc = new Location(Bukkit.getWorld("world"), -9.486D, 120.0D, -17.49D);
            loc.setYaw(89.9F);
            loc.setPitch(0.5F);
            p.teleport(loc);
        }

        @EventHandler
        public void onDeath(PlayerDeathEvent e) {
            Player p = e.getEntity().getPlayer();
            Location loc = new Location(Bukkit.getWorld("world"), -9.486D, 120.0D, -17.49D);
            loc.setYaw(89.9F);
            loc.setPitch(0.5F);
            p.teleport(loc);
        }

        @EventHandler
        public void onDamage(EntityDamageEvent e) {
            e.setCancelled(true);
        }

        @EventHandler
        public void onDamage(EntityDamageByEntityEvent e) {
            e.setCancelled(true);
        }

        @EventHandler
        public void onDamage(EntityDamageByBlockEvent e) {
            e.setCancelled(true);
        }

        @EventHandler
        public void onInteract(PlayerInteractEvent e) {
            try {
                if (!CMD_Build.build.contains(e.getPlayer())) {
                    if (e.getClickedBlock().getType() == Material.ARMOR_STAND) {
                        e.setCancelled(true);
                    }

                }
            } catch (NullPointerException e1) {

            }

        }

        @EventHandler
        public void onMove(PlayerMoveEvent e) {
            Player p = e.getPlayer();
            Location loc = new Location(Bukkit.getWorld("world"), -9.486D, 120.0D, -17.49D);
            loc.setYaw(89.9F);
            loc.setPitch(0.5F);
            if (p.isInsideVehicle()) {
                if (p.getLocation().getY() >= 200) {
                    p.teleport(loc);
                }
            }
        }


        @EventHandler
        public void onHunger(FoodLevelChangeEvent e) {
            e.setCancelled(true);
        }


        @EventHandler
        public void onBread(BlockBreakEvent e) {
            if (!CMD_Build.build.contains(e.getPlayer())) {
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }

        @EventHandler
        public void onDrop(PlayerDropItemEvent e) {
            if(!CMD_Build.build.contains(e.getPlayer())) {
                e.setCancelled(true);
            }
        }

    }
