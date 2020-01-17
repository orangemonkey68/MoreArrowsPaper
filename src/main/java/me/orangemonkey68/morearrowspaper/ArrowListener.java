package me.orangemonkey68.morearrowspaper;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.text.SimpleDateFormat;

public class ArrowListener implements Listener {

    @EventHandler
    void arrowHit(ProjectileHitEvent e){
        executeArrow(e);
    }

    @EventHandler
    //What happens when the arrow is shot
    void arrowShoot(EntityShootBowEvent e){
        Player player = (Player) e.getEntity();
        e.getEntity().sendMessage("You shot an arrow!");
        ItemStack shotItemStack = e.getArrowItem();
        tagProjectile(e);
    }

    void executeArrow(ProjectileHitEvent e){
        String type = getType(e);
        switch (type){
            case "explosive":
                Entity arrowEntity = e.getEntity();
                World world = e.getEntity().getWorld();

                world.createExplosion(arrowEntity.getLocation(), arrowPower(e), false, false, e.getEntity());
        }
    }

    String getType(ProjectileHitEvent e){

        Entity arrowEntity = e.getEntity();
        PersistentDataContainer entityData = arrowEntity.getPersistentDataContainer();

        if (entityData.has(KeyHolder.typeKey, PersistentDataType.STRING)){
            return entityData.get(KeyHolder.typeKey, PersistentDataType.STRING);
        } else{
            String timePattern = "MM-dd-hh-mm-ss";
            SimpleDateFormat time = new SimpleDateFormat(timePattern);
            Bukkit.getLogger().info("There was an error getting the type data on that arrow at " + time);
            return null;
        }
    }

    float arrowPower(ProjectileHitEvent e){
        float draw;
        Entity arrow = e.getEntity();
        int ticksLived = e.getEntity().getTicksLived();

        PersistentDataContainer arrowData = arrow.getPersistentDataContainer();
        if(arrowData.has(KeyHolder.powerKey, PersistentDataType.FLOAT)){
            //noinspection ConstantConditions
            draw = arrowData.get(KeyHolder.typeKey, PersistentDataType.FLOAT);
        }else{
            draw = 0;
        }

        float precheckFloat = (float) ((ticksLived*0.1+2)*draw);

        if(precheckFloat < 10){
            return precheckFloat;
        } else{
            return 10;
        }

    }

    void tagProjectile(EntityShootBowEvent e){
        Entity arrowEntity = e.getProjectile();
        ItemStack arrowItem = e.getArrowItem();

        PersistentDataContainer entityData = arrowEntity.getPersistentDataContainer();
        PersistentDataContainer itemData = arrowItem.getItemMeta().getPersistentDataContainer();

        String type;

        if(itemData.has(KeyHolder.typeKey, PersistentDataType.STRING)){
            type = itemData.get(KeyHolder.typeKey, PersistentDataType.STRING);
            entityData.set(KeyHolder.typeKey, PersistentDataType.STRING, type);
        }else{
            Bukkit.getLogger().info("Error with arrow type");
        }

        itemData.set(KeyHolder.powerKey, PersistentDataType.FLOAT, e.getForce());
    }
}
