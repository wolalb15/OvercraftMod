package com.overcraft.controller;

import com.overcraft.custom.EntityShield;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Controller {
    private static EntityShield shield;

    public static EntityShield getShield(){
        if(shield == null)
            createShield();

        return shield;
    }

    public static void createShield() {
        try {
            World world = Minecraft.getMinecraft().world;
            EntityPlayer player = Minecraft.getMinecraft().player;
            shield = new EntityShield(world, player.posX, player.posY, player.posZ);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
