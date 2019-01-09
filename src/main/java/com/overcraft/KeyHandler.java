package com.overcraft;

import com.overcraft.controller.Controller;
import com.overcraft.custom.EntityBoop;
import com.overcraft.custom.EntityFirestrike;
import com.overcraft.custom.EntityShield;
import com.overcraft.init.ModItems;
import com.overcraft.items.weapons.GenjiWeaponDragonblade;
import com.overcraft.renderer.GUINotification;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.particle.ParticleBubble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import scala.collection.parallel.ParIterableLike;

import java.util.List;
import java.util.Random;

public class KeyHandler {
    boolean hasDoubleJumped = false, canDoubleJump = false, reverse = false, worldLoaded = false;
    private boolean lucioShots = false;
    private int lucioAv = 3;


    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        try {
            EntityPlayer playerIn = Minecraft.getMinecraft().player;
            if (Minecraft.getMinecraft().world != null && playerIn != null &&
                    (isInHand(ModItems.GENJI_WEAPON_DRAGONBLADE) || isInHand(ModItems.GENJI_WEAPON_SHURIKEN) || isInHand(ModItems.GENJI_WEAPON_TANTO))) {

                IBlockState block = getWall(Minecraft.getMinecraft().world, playerIn);
                if (getWall(Minecraft.getMinecraft().world, playerIn) != null && block.getMaterial().blocksMovement()) {
                    playerIn.motionY = 0.1;
                    if (Minecraft.getMinecraft().world.getBlockState(playerIn.getPosition()).getMaterial() == Material.AIR)
                        playerIn.addVelocity(0, 0.1, 0);
                    //    playerIn.setPositionAndUpdate(playerIn.posX + playerIn.getLookVec().x,playerIn.posY + 0.25 ,playerIn.posZ + playerIn.getLookVec().z);
                    System.out.println("Wallclimbing");
                }
            }
        } catch (Exception ec) {
            System.out.println(ec);
        }
    }

    private static IBlockState getWall(World world, EntityPlayer player) {
        return world.getBlockState(getWallTarget(player));
    }

    private static BlockPos getWallTarget(EntityPlayer player) {
        BlockPos pos = new BlockPos(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());

        return pos.offset(Minecraft.getMinecraft().getRenderViewEntity().getHorizontalFacing());
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        Vec3d aim = player.getLookVec();
        /*****************************************************************************
         IF ABILITY IS PRESSED
         *********************************************************************************/

        World worldIn = player.getEntityWorld();

        if (OvercraftMod.ABILITY.isPressed()) {

            //_____________________________________________
            //TRACER

            if (isInHand(ModItems.TRACER_WEAPON)) {


                Random rd = new Random();
                int zz = 0;
                double zzD;
                for (int i = 0; i < 150; i++) {
                    zz = rd.nextInt(50) - 25;
                    zzD = (double) zz / 100;
                    player.getEntityWorld().spawnParticle(EnumParticleTypes.WATER_SPLASH, player.posX + zzD, player.posY + 1.25 + zzD, player.posZ + zzD, 1, 1, 1, 1);
                }
                if (!reverse) {
                    player.setPositionAndUpdate(player.posX + (aim.x * 5), player.posY, player.posZ + (aim.z * 5));
                } else {
                    player.setPositionAndUpdate(player.posX + (aim.x * -5), player.posY, player.posZ + (aim.z * -5));
                }
            }
            //_____________________________________________
            //LUCIO
            if (isInHand(ModItems.LUCIO_WEAPON)) {
                Controller.lucioSpeed = !Controller.lucioSpeed;
                FontRenderer fRender = Minecraft.getMinecraft().fontRenderer;
                new GUINotification(Minecraft.getMinecraft());

            }

            //_________________________________________________
            //REINHARDT
            if (isInHand(ModItems.REINHARDT_WEAPON)) {
                worldIn.spawnEntity(new EntityFirestrike(worldIn, player.posX + aim.x , player.posY + 0.5, player.posZ + aim.z));
            }

        //___________________________________________
        //SOLDIER
        if (isInHand(ModItems.SOLDIER_WEAPON)) {
            player.motionX *= 1.2;
            player.motionZ *= 1.2;
        }
    }
        /*****************************************************************************
         IF ULTIMATE IS PRESSED
         *********************************************************************************/
        if (OvercraftMod.ULTIMATE.isPressed()) {
            EntityTNTPrimed tnt = new EntityTNTPrimed(worldIn, player.posX, player.posY, player.posZ, player);
            worldIn.spawnEntity(tnt);
            if (tnt.isDead)
                worldIn.createExplosion(player, tnt.posX, tnt.posY, tnt.posZ, 0, true);
        }

        /*****************************************************************************
         IF SPACE IS PRESSED
         *********************************************************************************/
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
    public void onRenderGui(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE && OvercraftMod.ABILITY.isPressed())
            new GUINotification(Minecraft.getMinecraft());
        else return;
    }

    /*****************************************************************************
     IF KEY IS REE
     *********************************************************************************/
    @SubscribeEvent
    public void onKeyReleased(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKey() == Keyboard.KEY_S)
            reverse = true;
        else
            reverse = false;
    }


    @SubscribeEvent
    public void onMouseEvent(MouseEvent evt) {
        if (!Mouse.isButtonDown(Mouse.getButtonIndex("BUTTON1")))
            Controller.removeShield();
        else {
            if (isInHand(ModItems.REINHARDT_WEAPON))
                Controller.setShieldActive(true);
        }

        if (isInHand(ModItems.LUCIO_WEAPON) && Mouse.isButtonDown(Mouse.getButtonIndex("BUTTON0"))) {
            System.out.println("Left click");
            lucioShots = true;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer playerIn = Minecraft.getMinecraft().player;
        if (lucioShots) {
            if (lucioAv != 0) {
                if (playerIn.ticksExisted % 2 == 0) {

                    if (!playerIn.world.isRemote)
                        Minecraft.getMinecraft().world.spawnEntity(new EntityBoop(playerIn.world, playerIn.posX, playerIn.posY, playerIn.posZ));
                    lucioAv--;
                }
            } else {
                lucioShots = false;
                lucioAv = 3;
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        Controller.createShield();
        worldLoaded = true;
    }

    public boolean isInHand(Item item) {
        return Minecraft.getMinecraft().player.inventory.getCurrentItem().isItemEqual(new ItemStack(item));
    }
}

