package com.overcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyHandler {

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        if(OvercraftMod.ult.isPressed()){
            EntityPlayer player = Minecraft.getMinecraft().player;

            player.setPositionAndUpdate(player.posX + 0,player.posY+10,player.posZ + 0);

        }

    }
}
