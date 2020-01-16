package me.orangemonkey68.morearrowspaper;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ArrowHolder {
    public static ItemStack explosive(){

        ItemStack arrow = new ItemStack(Material.TIPPED_ARROW);
        //gets item metadata
        ItemMeta meta = arrow.getItemMeta();
        //sets the custom name on the arrow
        meta.setDisplayName(ChatColor.RED + "Explosive Arrow");
        //sets lore of item
        arrow.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        arrow.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        //gives item value explosive
        meta.getPersistentDataContainer().set(KeyHolder.typeKey, PersistentDataType.STRING, "explosive");
        //gets potion meta
        PotionMeta potionMeta = (PotionMeta) meta;
        PotionData potionData = new PotionData(PotionType.NIGHT_VISION, false, false);
        potionMeta.setBasePotionData(potionData);
        //sets potion data
        Color arrowColor = Color.fromRGB(221, 65, 25);
        potionMeta.setColor(arrowColor);
        //pushes potion data to item (I think)
        arrow.setItemMeta(potionMeta);
        //pushes meta to arrow
        arrow.setItemMeta(meta);
        //returns the itemstack
        return arrow;
    }


}
