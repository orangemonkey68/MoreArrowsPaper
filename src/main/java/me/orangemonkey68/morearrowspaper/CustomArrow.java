package me.orangemonkey68.morearrowspaper;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;

public class CustomArrow extends ItemStack {
    public CustomArrow(String type, Color color){
        this.setType(Material.TIPPED_ARROW);

        ItemMeta meta = this.getItemMeta();
        //sets the custom name on the arrow
        meta.setDisplayName(ChatColor.RED + StringUtils.capitalize(type) + " Arrow");
        //sets lore of item
        this.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        this.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.getPersistentDataContainer().set(KeyHolder.typeKey, PersistentDataType.STRING, type);
        //gets potion meta
        PotionMeta potionMeta = (PotionMeta) meta;
        potionMeta.setColor(color);
        //pushes potion data to item (I think)
        this.setItemMeta(potionMeta);
        //pushes meta to arrow
        this.setItemMeta(meta);
    }
}
