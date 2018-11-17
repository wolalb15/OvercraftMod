package com.overcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleBubble;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.Random;

public class KeyHandler {

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        if(OvercraftMod.ULTIMATE.isPressed()){
            EntityPlayer player = Minecraft.getMinecraft().player;
            Vec3d aim = player.getLookVec();

            Random rd = new Random();
            int zz = 0;
            double zzD;
            for (int i = 0; i  < 150; i++){
                zz = rd.nextInt(50) - 25    ;
                zzD  = (double) zz / 100;
                System.out.println(zzD);
                player.getEntityWorld().spawnParticle(EnumParticleTypes.WATER_SPLASH,player.posX + zzD,player.posY + 1.25 + zzD,player.posZ + zzD,1,1,1,1);
            }
            player.setPositionAndUpdate(player.posX + (aim.x * 5),player.posY,player.posZ + (aim.z * 5));
        }


    }
}
