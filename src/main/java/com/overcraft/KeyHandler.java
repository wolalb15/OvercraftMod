package com.overcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleBubble;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.Random;

public class KeyHandler {

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        if(OvercraftMod.ult.isPressed()){
            EntityPlayer player = Minecraft.getMinecraft().player;

            //player.setPositionAndUpdate(player.posX + 0,player.posY+10,player.posZ + 0);
            Random rd = new Random();
            int zz = 0;
            double zzD;
            for (int i = 0; i  < 150; i++){
                zz = rd.nextInt(200) ;
                zzD  = (double) zz / 100;
                System.out.println(zzD);
                player.getEntityWorld().spawnParticle(EnumParticleTypes.WATER_SPLASH,player.posX,player.posY + zzD,player.posZ,1,1,1,1);
            }
        }

    }
}
