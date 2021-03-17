package de.loriyt.lobbysystem.listener;


import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.loriyt.lobbysystem.commands.CMD_Build;
import de.loriyt.lobbysystem.Utils.Data;
import de.loriyt.lobbysystem.Main;
import de.loriyt.lobbysystem.manager.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ServiceConfigurationError;

public class InteractListener implements Listener {

    private HashMap<String, Long> tcd = new HashMap<>();
    public HashMap<Player, EnderPearl> enderschmel = new HashMap<>();




    public ArrayList<ItemStack> getLobbys() {
        ArrayList<ItemStack> list = new ArrayList<>();
        Collection<ServiceInfoSnapshot> skywars = CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices("Skywars");
        return null;
    }





    @EventHandler
    public void onKompas(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8〣 §f§lKompass")) {
                    e.setCancelled(true);
                    try {
                        if(p.isInsideVehicle()) {
                            p.sendMessage(Data.system + "§cDu kannst dies erst wenn du auf dem Boden bist!");
                            p.closeInventory();
                        } else {
                            Long time = Long.valueOf(System.currentTimeMillis());
                            if(this.tcd.containsKey(p.getName())) {
                                Long lastuse = this.tcd.get(p.getName());
                                if(lastuse.longValue() + 1400L > time.longValue()) {
                                        p.sendMessage(Data.system + "§f§lWarte noch kurz!");
                                        return;
                                }
                            }
                            this.tcd.put(p.getName(), time);
                            Inventory inv = Bukkit.createInventory(null, 54, "§7Wähle ein Ziel aus...");
                            ItemStack spawn = new ItemStack(Material.NETHER_STAR);
                            ItemMeta spwann = spawn.getItemMeta();
                            spwann.setDisplayName("§8〣 §fSpawn");
                            spawn.setItemMeta(spwann);

                            ItemStack skywars = new ItemStack(Material.GRASS);
                            ItemMeta skywarsmeta = skywars.getItemMeta();
                            skywarsmeta.setDisplayName("§8〣 §fSkyWars");
                            skywars.setItemMeta(skywarsmeta);

                            ItemStack mlgrush = new ItemStack(Material.STICK);
                            ItemMeta mlgrushItemMeta = mlgrush.getItemMeta();
                            mlgrushItemMeta.setDisplayName("§8〣 §fMlg§kRush");
                            mlgrush.setItemMeta(mlgrushItemMeta);

                            ItemStack swrush = new ItemStack(Material.SNOW_BALL);
                            ItemMeta swrushItemMeta = swrush.getItemMeta();
                            swrushItemMeta.setDisplayName("§8〣 §fSW§kRush");
                            swrush.setItemMeta(swrushItemMeta);

                            ItemStack BedWars = new ItemStack(Material.BED);
                            ItemMeta bedWarsItemMeta = BedWars.getItemMeta();
                            bedWarsItemMeta.setDisplayName("§8〣 §fBed§kWars");
                            BedWars.setItemMeta(bedWarsItemMeta);

                            ItemStack caseopening = new ItemStack(Material.ENDER_CHEST);
                            ItemMeta caseItemMeta = caseopening.getItemMeta();
                            caseItemMeta.setDisplayName("§8〣 §fCase Opening");
                            caseopening.setItemMeta(caseItemMeta);

                            ItemStack ingshop = new ItemStack(Material.GOLD_INGOT);
                            ItemMeta ingshopItemMeta = ingshop.getItemMeta();
                            ingshopItemMeta.setDisplayName("§8〣 §fIngame §kShop");
                            ingshop.setItemMeta(ingshopItemMeta);

                            ItemStack kit1vs1 = new ItemStack(Material.GOLD_INGOT);
                            ItemMeta kit1vs1ItemMeta = kit1vs1.getItemMeta();
                            kit1vs1ItemMeta.setDisplayName("§8〣 §fKit §k1vs1");
                            kit1vs1.setItemMeta(kit1vs1ItemMeta);

                            ItemStack fastbridge = new ItemStack(Material.RED_SANDSTONE);
                            ItemMeta fastItemMeta = fastbridge.getItemMeta();
                            fastItemMeta.setDisplayName("§8〣 §fFast B§kuilder");
                            fastbridge.setItemMeta(fastItemMeta);

                            ItemStack nextpage = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData());
                            ItemMeta nextpagemeta = nextpage.getItemMeta();
                            nextpagemeta.setDisplayName("§8〣 §fNEXT PAGE");
                            nextpage.setItemMeta(nextpagemeta);

                            inv.setItem(13, skywars);
                            inv.setItem(33, swrush);
                            inv.setItem(44, BedWars);
                            inv.setItem(27, mlgrush);
                            inv.setItem(10, caseopening);
                            inv.setItem(16, ingshop);
                            inv.setItem(37, kit1vs1);
                            inv.setItem(31, spawn);
                            inv.setItem(53, nextpage);
                            p.openInventory(inv);
                        }
                    } catch (NullPointerException e2) {
                    }
                }
            }

        } catch (NullPointerException e1) {

        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if (e.getInventory().getName().equalsIgnoreCase("§7Wähle ein Ziel aus...")) {
                e.setCancelled(true);
            }
            try {
                if (e.getCurrentItem().getType() == Material.GRASS) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8〣 §fSkywars")) {
                        Location loc = new Location(Bukkit.getWorld("LobbyIslandd"), -179.523, 26, 777.470);
                        loc.setYaw((float) 180);
                        loc.setPitch((float) -1.0);
                        p.teleport(loc);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                        p.closeInventory();
                    }
                }else if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8〣 §fSpawn")) {
                        Location loc = new Location(Bukkit.getWorld("LobbyIslandd"), -179.477, 38.5, 843.155);
                        loc.setYaw((float) 179);
                        loc.setPitch((float) 0.4);
                        p.teleport(loc);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                        p.closeInventory();

                    }
                }else if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8〣 §fNEXT PAGE")) {
                            Inventory inv = Bukkit.createInventory(null, 54, "§7QUICKJOIN");
                            ItemStack skywars = new ItemStack(Material.GRASS);
                            ItemMeta skywarsmeta = skywars.getItemMeta();
                            skywarsmeta.setDisplayName("§8〣 §b[QUICKJOIN] §fSkyWars");
                            skywars.setItemMeta(skywarsmeta);

                            ItemStack mlgrush = new ItemStack(Material.STICK);
                            ItemMeta mlgrushItemMeta = mlgrush.getItemMeta();
                            mlgrushItemMeta.setDisplayName("§8〣 §b[QUICKJOIN] §fMlg§kRush");
                            mlgrush.setItemMeta(mlgrushItemMeta);

                            ItemStack swrush = new ItemStack(Material.SNOW_BALL);
                            ItemMeta swrushItemMeta = swrush.getItemMeta();
                            swrushItemMeta.setDisplayName("§8〣 §b[QUICKJOIN] §fSW§kRush");
                            swrush.setItemMeta(swrushItemMeta);

                            ItemStack BedWars = new ItemStack(Material.BED);
                            ItemMeta bedWarsItemMeta = BedWars.getItemMeta();
                            bedWarsItemMeta.setDisplayName("§8〣 §b[QUICKJOIN] §fBed§kWars");
                            BedWars.setItemMeta(bedWarsItemMeta);


                            inv.setItem(0, skywars);
                            inv.setItem(1, BedWars);
                            inv.setItem(2, mlgrush);
                            inv.setItem(3, swrush);
                            p.openInventory(inv);
                    }
                }
            } catch (NullPointerException e2) {

            }
        } catch (NullPointerException e1) {

        }
    }

    @EventHandler
    public void onClick2(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if (e.getInventory().getName().equalsIgnoreCase("§7Wähle ein Ziel aus...")) {
                e.setCancelled(true);
            }
            try {
                if (e.getCurrentItem().getType() == Material.GRASS) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8〣 §fSpawn")) {
                        Location loc = new Location(Bukkit.getWorld("world"), -9.616, 120, -17.565);
                        loc.setYaw((float) 87);
                        loc.setPitch((float) 13.9);
                        p.teleport(loc);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                        p.closeInventory();
                    }
                }
            } catch (NullPointerException e2) {

            }
        } catch (NullPointerException e1) {

        }
    }

    @EventHandler
    public void onEnder(PlayerInteractEvent e) {
        if(e.getItem() != null && e.getItem().getType() == Material.ENDER_PEARL) {
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8〣 §5§lEnderperle")) {
                Player p = e.getPlayer();
                e.setCancelled(true);


                p.getInventory().setItem(4, Main.getInstance().itemManager.waiting);
                p.updateInventory();

                EnderPearl enderPearl = p.launchProjectile(EnderPearl.class);
                enderPearl.setPassenger(p);
                startEnderCheck(p);
                enderschmel.put(p, enderPearl);


                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        p.getInventory().setItem(4, Main.getInstance().itemManager.enderpaeraad);
                        p.updateInventory();
                    }
                }, 20 * 5);
            }
        }
    }

    @EventHandler
    public void onVehicleLeave(VehicleExitEvent e) {
        if(e.getExited() instanceof Player) {
            if(enderschmel.containsKey(e.getExited())) {
                enderschmel.get(e.getExited()).remove();
            }
        }
    }



    public void startEnderCheck(Player p) {
        if(p.isInsideVehicle()) {
            Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if(p.isOnGround()) {
                        enderschmel.remove(p);
                    }
                }
            }, 20, 0);
        }
    }

    @EventHandler
    public void left(VehicleExitEvent e) {
        if (e.getExited() instanceof Player) {
            if (this.enderschmel.containsKey(e.getExited())) {
               this.enderschmel.get(e.getExited()).remove();
            }
        }
    }

    @EventHandler
    public void onTp (PlayerTeleportEvent e) {
        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onbreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(!CMD_Build.build.contains(p.getName())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onbocks(BlockPlaceEvent e) {
        if(!CMD_Build.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntity(PlayerInteractAtEntityEvent e) {
        if(!CMD_Build.build.contains(e.getPlayer())) {
            if(e.getRightClicked() instanceof ArmorStand) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(false);
        }
    }

}
