package me.orangemonkey68.morearrowspaper;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class KeyHolder {
    //Holds plugin
    public static Plugin plugin = MoreArrows.getPlugin(MoreArrows.class);
    //Key to get object arrow type
    static public NamespacedKey typeKey = new NamespacedKey(plugin, "typeKey");

}
