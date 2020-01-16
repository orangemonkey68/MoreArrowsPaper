package me.orangemonkey68.morearrowspaper;

import com.sun.istack.internal.NotNull;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArrowCommand implements CommandExecutor {
    @Override
    @NotNull
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            //create arrow itemstack
            ItemStack explosiveArrow = ArrowHolder.explosive();
            //Parse string for integer, alert player if failed
            int number = 1;
            try{
                number = Integer.parseInt(args[0]);
            }catch(Exception e){
                player.sendMessage(ChatColor.RED + "Sorry, that's either fractional or not a number");
            }
            //Set the number of arrows to given value unless more than 1 stack, when it sets it to 64
            if(number < 65){
                explosiveArrow.setAmount(number);
            }else{
                explosiveArrow.setAmount(64);
                number = 64;
            }
            player.getInventory().addItem(explosiveArrow);
            if(number == 1){
                player.sendMessage(ChatColor.RED + "Have an arrow! Don't drop it!");
            } else{
                player.sendMessage(ChatColor.RED + "Have " + number + " arrows! Don't drop them!");
            }


        }else{
            sender.sendMessage("Can't execute from console.");
        }
        return true;
    }
}
