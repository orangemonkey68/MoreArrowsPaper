package me.orangemonkey68.morearrowspaper;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ArrowExecutor {
    //TODO: copy over arrow execution functions from ArrowListener



    public void executeExplosive(ProjectileHitEvent e){
        Entity arrowEntity = e.getEntity();
        World world = e.getEntity().getWorld();

        double distance = ((Player) e.getEntity().getShooter()).getLocation().distance(arrowEntity.getLocation());

        if(distance > 6.5){
            world.createExplosion(arrowEntity.getLocation(), arrowPower(e), false, false, e.getEntity());
            arrowEntity.remove();
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

}
