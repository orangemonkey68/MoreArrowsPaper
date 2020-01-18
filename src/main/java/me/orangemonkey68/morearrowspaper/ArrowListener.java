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

public class ArrowListener implements Listener {

    @EventHandler
    void arrowHit(ProjectileHitEvent e){


        executeArrow(e);
    }

    @EventHandler
    //What happens when the arrow is shot
    void arrowShoot(EntityShootBowEvent e){


        if(e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            e.getEntity().sendMessage("You shot an arrow!");
            ItemStack shotItemStack = e.getArrowItem();
            tagProjectile(e);
        }
    }

    void executeArrow(ProjectileHitEvent e){
        String type = getType(e);
        if(type != null){

            ArrowExecutor executor = new ArrowExecutor();

            switch (type){
                case "explosive":
                    Entity arrowEntity = e.getEntity();
                    World world = e.getEntity().getWorld();

                    double distance = ((Player) e.getEntity().getShooter()).getLocation().distance(arrowEntity.getLocation());

                    if(distance > 6.5){
                        world.createExplosion(arrowEntity.getLocation(), arrowPower(e), false, false, e.getEntity());
                        arrowEntity.remove();
                    }
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

    float arrowPower(ProjectileHitEvent e){
        float draw = 0;

        Entity arrow = e.getEntity();
        int ticksLived = e.getEntity().getTicksLived();

        PersistentDataContainer arrowData = arrow.getPersistentDataContainer();
        if(arrowData.has(KeyHolder.powerKey, PersistentDataType.FLOAT)){
            //noinspection ConstantConditions
            draw = arrowData.get(KeyHolder.powerKey, PersistentDataType.FLOAT);
        }
        Bukkit.getLogger().info(String.valueOf(draw));
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
            entityData.set(KeyHolder.powerKey, PersistentDataType.FLOAT, e.getForce());
        }


    }
}
