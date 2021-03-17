package de.loriyt.lobbysystem.Utils;

import org.bukkit.inventory.ItemStack;

public class Data {

    public static String info = "§8[§l§fINFO§8] ";
    private static String prefix = "§7[§cSoupTraining§7] ";
    private static ItemStack item;

    public static void setItem(ItemStack itemStack){
        item = itemStack;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static ItemStack getItem() {
        return item;
    }

    public static String system = "§8[§l§fSystem§8] ";
}
