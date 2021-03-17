package de.loriyt.lobbysystem.Utils;

import de.loriyt.lobbysystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowman;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * JavaDoc this file!
 * Created: 18.12.2020
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */
public class ShopVillager {


    public static final String ShopName = "§6§lRangShop";

    public void spawnMob() {
        final Location loc = new Location(Bukkit.getWorld("world"), -184, 19, 201);
        Snowman shop = (Snowman) loc.getWorld().spawnCreature(loc, EntityType.SNOWMAN);
        shop.setCustomName(ShopName);
        shop.setCustomNameVisible(true);
        shop.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 500));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                shop.teleport(loc);
            }
        }, 0, 20);
    }
}
