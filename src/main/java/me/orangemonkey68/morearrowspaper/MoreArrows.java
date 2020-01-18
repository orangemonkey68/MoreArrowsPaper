package me.orangemonkey68.morearrowspaper;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoreArrows extends JavaPlugin {

    static public void debugTell(Player player, String message){
        if(player.hasPermission("morearrows.debug")){
            player.sendMessage(message);
        }
    }

    @Override
    public void onEnable() {
        //Logs it loaded then registers listeners
        getLogger().info(ChatColor.RED + "MoreArrows LOADED");
        getServer().getPluginManager().registerEvents(new ArrowListener(), this);
        getServer().getPluginManager().registerEvents(new FletchingTableListener(), this);

        //registers command
        this.getCommand("explosivearrow").setExecutor(new ArrowCommand());

        this.getServer().addRecipe(RecipeHolder.explosiveArrow());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
