package me.orangemonkey68.morearrowspaper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ArrowListener implements Listener {
    private Plugin plugin = MoreArrows.getPlugin(MoreArrows.class);

    @EventHandler
    void arrowShoot(EntityShootBowEvent e){
        Player player = (Player) e.getEntity();

        e.getEntity().sendMessage("You shot an arrow!");
        ItemStack shotItemStack = e.getArrowItem();

        tagProjectile(e);
    }

    void tagProjectile(EntityShootBowEvent e){
        Entity arrowEntity = e.getProjectile();
        ItemStack arrowItem = e.getArrowItem();

        PersistentDataContainer entityData = arrowEntity.getPersistentDataContainer();
        PersistentDataContainer itemData = arrowItem.getItemMeta().getPersistentDataContainer();

        String type = null;

        if(itemData.has(KeyHolder.typeKey, PersistentDataType.STRING)){
            type = itemData.get(KeyHolder.typeKey, PersistentDataType.STRING);
            entityData.set(KeyHolder.typeKey, PersistentDataType.STRING, type);
        }else{
            Bukkit.getLogger().info("Error with arrow type");
        }
    }
}
