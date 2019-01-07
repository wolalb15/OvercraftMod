package com.overcraft;

import com.overcraft.controller.Controller;
import com.overcraft.custom.EntityShield;
import com.overcraft.init.ModItems;
import com.overcraft.items.weapons.GenjiWeaponDragonblade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.particle.ParticleBubble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.List;
import java.util.Random;

public class KeyHandler {
    boolean hasDoubleJumped = false, canDoubleJump = false, reverse = false;

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        World worldIn = player.getEntityWorld();
        if (OvercraftMod.ABILITY.isPressed()) {
            if (player.inventory.getCurrentItem().isItemEqual(new ItemStack(ModItems.TRACER_WEAPON))) {

                Vec3d aim = player.getLookVec();

                Random rd = new Random();
                int zz = 0;
                double zzD;
                for (int i = 0; i < 150; i++) {
                    zz = rd.nextInt(50) - 25;
                    zzD = (double) zz / 100;
                    System.out.println(zzD);
                    player.getEntityWorld().spawnParticle(EnumParticleTypes.WATER_SPLASH, player.posX + zzD, player.posY + 1.25 + zzD, player.posZ + zzD, 1, 1, 1, 1);
                }
                if (!reverse) {
                    player.setPositionAndUpdate(player.posX + (aim.x * 5), player.posY, player.posZ + (aim.z * 5));
                } else {
                    player.setPositionAndUpdate(player.posX + (aim.x * -5), player.posY, player.posZ + (aim.z * -5));
                }
            }

            if(isInHand(ModItems.LUCIO_WEAPON)){
                Controller.lucioSpeed = !Controller.lucioSpeed;
                FontRenderer fRender = Minecraft.getMinecraft().fontRenderer;
                fRender.drawString("Lucio Ability Switch",5,5,0);
            }
        }
        if (OvercraftMod.ULTIMATE.isPressed()) {
            EntityTNTPrimed tnt = new EntityTNTPrimed(worldIn, player.posX, player.posY, player.posZ, player);
            worldIn.spawnEntity(tnt);
            if (tnt.isDead)
                worldIn.createExplosion(player, tnt.posX, tnt.posY, tnt.posZ, 0, true);
        }

        if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
            try {
                EntityPlayer playerIn = Minecraft.getMinecraft().player;
                if (playerIn.inventory.getCurrentItem().isItemEqual((new ItemStack(ModItems.GENJI_WEAPON_DRAGONBLADE)))) {
                    if (!player.onGround) {
                        if (!hasDoubleJumped && canDoubleJump) {
                            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                                player.motionY = 0;
                                playerIn.addVelocity(0, 0.65, 0);
                                hasDoubleJumped = true;
                                System.out.println("Double Jump");
                            }
                        } else {
                            canDoubleJump = true;
                        }
                    } else {
                        hasDoubleJumped = false;
                        canDoubleJump = true;
                    }
                } else {

                }

            } catch (Exception exc) {

            }
        }

    }


    @SubscribeEvent
    public void onKeyReleased(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKey() == Keyboard.KEY_S)
            reverse = true;
        else
            reverse = false;
    }

    @SubscribeEvent
    public void onMousePressed(InputEvent.MouseInputEvent event) {
        System.out.println("Mouse Input Event");

    }

    @SubscribeEvent
    public void onMouseEvent(MouseEvent evt) {
        if (!Mouse.isButtonDown(Mouse.getButtonIndex("BUTTON1")))
            Controller.removeShield();
        else {
            Controller.setShieldActive(true);
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        Controller.createShield();
    }

    public boolean isInHand(Item item) {
        return Minecraft.getMinecraft().player.inventory.getCurrentItem().isItemEqual(new ItemStack(item));
    }
}

