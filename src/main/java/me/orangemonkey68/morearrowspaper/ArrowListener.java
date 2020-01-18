package me.orangemonkey68.morearrowspaper;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ArrowListener implements Listener {

    @EventHandler
    void arrowHit(ProjectileHitEvent e){


        executeArrow(e);
    }

    @EventHandler
    //What happens when the arrow is shot
    void arrowShoot(EntityShootBowEvent e){


        if(e.getEntity() instanceof Player){
            tagProjectile(e);
        }
    }

    void executeArrow(ProjectileHitEvent e){
        String type = getType(e);
        if(type != null){

            ArrowExecutor executor = new ArrowExecutor();

            switch (type){
                case "explosive":
                    executor.executeExplosive(e);
                    break;
                case "normal":
                    break;
            }
        }
    }

    String getType(ProjectileHitEvent e){

        Entity arrowEntity = e.getEntity();
        PersistentDataContainer entityData = arrowEntity.getPersistentDataContainer();

        if (entityData.has(KeyHolder.typeKey, PersistentDataType.STRING)){
            return entityData.get(KeyHolder.typeKey, PersistentDataType.STRING);
        } else{
            return "normal";
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
            entityData.set(KeyHolder.powerKey, PersistentDataType.FLOAT, e.getForce());
        }


    }
}
