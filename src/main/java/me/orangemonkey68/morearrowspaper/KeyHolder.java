package me.orangemonkey68.morearrowspaper;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class KeyHolder {
    //Holds plugin
    public static Plugin plugin = MoreArrows.getPlugin(MoreArrows.class);
    //Key to get object arrow type
    static public NamespacedKey typeKey = new NamespacedKey(plugin, "typeKey");
    //Key to get bow power
    static public NamespacedKey powerKey = new NamespacedKey(plugin, "powerKey");
    //explosive arrow recipe
    static public NamespacedKey explosiveArrowRecipeKey = new NamespacedKey(plugin, "explosiveArrowRecipeKey");

}
