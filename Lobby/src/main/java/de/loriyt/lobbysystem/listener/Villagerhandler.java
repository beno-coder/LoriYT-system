package de.loriyt.lobbysystem.listener;

import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.ext.cloudperms.CloudPermissionsPermissionManagement;
import de.loriyt.lobbysystem.Utils.Data;
import de.loriyt.lobbysystem.Utils.ShopVillager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.concurrent.TimeUnit;

/**
 * JavaDoc this file!
 * Created: 18.12.2020
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */
public class Villagerhandler implements Listener {



    @EventHandler
    public void handleShopinteract(PlayerInteractAtEntityEvent event) {
        Player p = event.getPlayer();
        if (event.getRightClicked().getType()== EntityType.SNOWMAN) {
            Snowman shop = (Snowman) event.getRightClicked();

            if (shop.getCustomName().equals(ShopVillager.ShopName)) {

                event.getPlayer().closeInventory();

                Inventory rang = Bukkit.createInventory(null, 6*9, ShopVillager.ShopName);

                ItemStack Gold = new ItemStack(Material.GOLD_BLOCK);
                ItemMeta goldmeta = Gold.getItemMeta();
                goldmeta.setDisplayName("§6§lPremiumRang");
                Gold.setItemMeta(goldmeta);

                rang.setItem(27, Gold);
                p.openInventory(rang);
            }
        }
    }

    /**@EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if(e.getInventory().getName().equalsIgnoreCase(ShopVillager.ShopName)) {
                e.setCancelled(true);
                try {
                    if(e.getCurrentItem().getType() == Material.GOLD_BLOCK) {
                        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPremiumRang")) {
                            if(p.hasPermission("premium")) {
                                p.sendMessage(Data.system + "§cDu hast bereits den Rang!");
                            } else {
                                if(CoinsAPI.getCoins(p.getUniqueId().toString()) >= 500) {
                                    IPermissionUser permissionUser = CloudPermissionsPermissionManagement.getInstance().getUser(p.getUniqueId());
                                    if(permissionUser == null) return;
                                    permissionUser.removeGroup("Admin");
                                    permissionUser.addGroup("Premium", 1, TimeUnit.MINUTES);
                                    CloudPermissionsPermissionManagement.getInstance().updateUser(permissionUser);
                                    p.closeInventory();
                                    CoinsAPI.takeCoins(p.getUniqueId().toString(), 500);
                                }
                            }
                        }
                    }
                } catch (NullPointerException e2) {

                }
            }
        }catch (NullPointerException e1) {

        }
    }*/
}
