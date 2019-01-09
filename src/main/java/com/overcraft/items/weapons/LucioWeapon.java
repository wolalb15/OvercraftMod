package com.overcraft.items.weapons;

import com.overcraft.controller.Controller;
import com.overcraft.custom.CustomParticle;
import com.overcraft.custom.EntityBoop;
import com.overcraft.custom.EntityBullet;
import com.overcraft.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.Collection;

public class LucioWeapon extends Item {
    public PotionEffect healing = new PotionEffect(Potion.getPotionById(10), 5, 0);
    private int shotsAv = 3;

    public LucioWeapon(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        Vec3d aim = playerIn.getLookVec();
        if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem()==Item.getByNameOrId("overcraft:tracer_bullet")
                && playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getCount() != 0
                || Minecraft.getMinecraft().playerController.getCurrentGameType()==GameType.CREATIVE) {
            ItemStack slot = playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
            slot.setCount(slot.getCount() - 1);

            if(worldIn.isRemote){
                EntityBoop bullet = new EntityBoop(worldIn, playerIn.posX + aim.x, playerIn.posY + aim.y + 1.5, playerIn.posZ + aim.z,false);
                worldIn.spawnEntity(bullet);}


        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        World world = Minecraft.getMinecraft().world;
        EntityPlayer playerIn= Minecraft.getMinecraft().player;


        return false;
    }




    @SubscribeEvent
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag){
        try {
            EntityPlayer playerIn = Minecraft.getMinecraft().player;
            if(Controller.lucioSpeed) {
                Collection<PotionEffect> effects = playerIn.getActivePotionEffects();
                for (PotionEffect effect: effects) {
                    playerIn.removePotionEffect(effect.getPotion());
                }
                if (playerIn.inventory.getCurrentItem().isItemEqual((new ItemStack(ModItems.LUCIO_WEAPON))) && playerIn.onGround) {

                    playerIn.motionX = playerIn.motionX * 1.2;
                    playerIn.motionZ = playerIn.motionZ * 1.2;
                }
            }else {

                if (playerIn.ticksExisted % 15 == 0 && playerIn.getHealth() != 0 && playerIn.getHealth() != playerIn.getMaxHealth()){
                    playerIn.addPotionEffect(healing);
                    playerIn.setHealth(playerIn.getHealth() + 0.5f);
                }
            }
        } catch (Exception exc){

        }
        super.onUpdate(itemstack,world,entity,i,flag);
    }


}
