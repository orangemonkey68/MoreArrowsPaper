package me.orangemonkey68.morearrowspaper;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FletchingTableListener implements Listener {

    @EventHandler
    void onFletchingTableClick(PlayerInteractEvent e){
        Block clickedBlock = e.getClickedBlock();
        Material blockType = null;
        if(e.hasBlock()){
            blockType = clickedBlock.getType();
        }
        Bukkit.getLogger().info(e.getHand().toString());
        if(blockType == Material.FLETCHING_TABLE){
            // && e.getHand() == EquipmentSlot.HAND && (e.getMaterial() == Material.AIR || e.getMaterial() == null)
            e.getPlayer().sendMessage("You clicked a fletching table!");
            e.getPlayer().openInventory(temp(e.getPlayer()));
        }else{
            e.getPlayer().sendMessage("Not a fletching table");
        }
    }

    @EventHandler
    void placeNextToFletchingTable(BlockPlaceEvent e){
        if(e.getBlockAgainst().getType() == Material.FLETCHING_TABLE){
            e.setCancelled(true);
        }
    }

    public Inventory temp(Player player){
        Inventory inv = Bukkit.createInventory(player, 9);
        inv.addItem(new ItemStack(Material.GOLD_BLOCK, 8));
        return inv;
    }

}
