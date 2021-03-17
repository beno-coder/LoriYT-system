package de.loriyt.lobbysystem.manager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

    public ItemStack waiting;
    public ItemStack enderpaeraad;

    public void setItem(Player p) {
        ItemStack kompass = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = kompass.getItemMeta();
        itemMeta.setDisplayName("§8〣 §f§lKompass");
        kompass.setItemMeta(itemMeta);
        p.getInventory().setItem(0, kompass);
        p.updateInventory();


        enderpaeraad = new ItemStack(Material.ENDER_PEARL);
        ItemMeta itemMeta2 = enderpaeraad.getItemMeta();
        itemMeta2.setDisplayName("§8〣 §5§lEnderperle");
        enderpaeraad.setItemMeta(itemMeta2);
        p.getInventory().setItem(4, enderpaeraad);
        p.updateInventory();
    }

    public void lobbyswitcher(Player p) {
        ItemStack timer = new ItemStack(Material.WATCH);
        ItemMeta timemeta = timer.getItemMeta();
        timemeta.setDisplayName("§8〣 §f§lLobbySwitcher");
        timer.setItemMeta(timemeta);
        p.getInventory().setItem(8, timer);
        p.updateInventory();
    }

    public void setWaiting(Player p) {
        waiting = new ItemStack(Material.FIREWORK_CHARGE);
        ItemMeta itemMeta1 = waiting.getItemMeta();
        itemMeta1.setDisplayName("§8〣 §8§lEnderperle");
        waiting.setItemMeta(itemMeta1);
    }

}
