package com.overcraft.items.weapons;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TracerWeapon extends Item {
    public TracerWeapon (String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        Vec3d aim = playerIn.getLookVec();
        EntityArrow bullet = new CustomArrow(worldIn,playerIn);
        bullet.shoot(playerIn,0,0,0,100,1);
        bullet.setPosition(playerIn.posX, playerIn.posY + 1.5, playerIn.posZ);
        bullet.setDamage(100);

       bullet.setVelocity(aim.x,aim.y,aim.z);
        worldIn.spawnEntity(bullet);


        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }

}
