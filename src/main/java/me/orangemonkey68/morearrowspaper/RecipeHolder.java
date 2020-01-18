package me.orangemonkey68.morearrowspaper;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeHolder {
    public static ShapedRecipe explosiveArrow(){
        //creates exploding arrow
        ItemStack explosiveArrow = ArrowHolder.explosive();
        //sets amount to 8 arrows
        explosiveArrow.setAmount(8);
        //gets key from keyholder, creates recipe with 8 explosive arrows as the result
        ShapedRecipe arrow = new ShapedRecipe(KeyHolder.explosiveArrowRecipeKey, explosiveArrow);
        //shape: Gunpowder surrounded by 8 arrows
        arrow.shape("aaa", "aga", "aaa");
        arrow.setIngredient('a', Material.ARROW);
        arrow.setIngredient('g', Material.GUNPOWDER);

        return arrow;
    }
}
