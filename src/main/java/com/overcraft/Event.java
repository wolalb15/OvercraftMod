package com.overcraft;


import ibxm.Player;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;

public class Event {
    @Mod.EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
//Define the player
        Player player = (Player) event.getEntity();
//Define the item in their hand
        Item mat = player.getItemInHand().getType();



//If the player right clicks air or a block
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
//Check if it's a stick
            if(mat == Material.STICK){
//Make them chat
                player.chat("I've been forced to say this!");
            }
        }
    }
}
