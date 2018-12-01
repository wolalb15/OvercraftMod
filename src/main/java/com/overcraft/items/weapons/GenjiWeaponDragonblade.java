package com.overcraft.items.weapons;

import com.overcraft.custom.CustomParticle;
import com.overcraft.custom.EntityBullet;
import com.overcraft.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class GenjiWeaponDragonblade extends Item {
    public GenjiWeaponDragonblade(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);

    }
    boolean direction = true;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }

    @SubscribeEvent
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag){


        super.onUpdate(itemstack,world,entity,i,flag);
    }
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        Vec3d aim = entityLiving.getLookVec();
        World world = entityLiving.getEntityWorld();

        CustomParticle cp = new CustomParticle(entityLiving.getEntityWorld(),entityLiving.posX, entityLiving.posY,entityLiving.posZ);
        cp.setSize(20f,20f);
        Minecraft.getMinecraft().effectRenderer.addEffect(cp);
        entityLiving.setPositionAndUpdate(entityLiving.posX,entityLiving.posY,entityLiving.posZ);
        return true;

    }

}
