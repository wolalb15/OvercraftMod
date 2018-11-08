package com.overcraft.items.weapons;

import com.overcraft.custom.CustomArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
        bullet.setPosition(playerIn.posX, playerIn.posY + 0.6, playerIn.posZ);
        bullet.setDamage(100);

       bullet.setVelocity(aim.x * 10,aim.y,aim.z * 10);
        worldIn.spawnEntity(bullet);


        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        Vec3d aim = entityLiving.getLookVec();
        World world = entityLiving.getEntityWorld();


        world.spawnParticle(EnumParticleTypes.WATER_SPLASH,false,entityLiving.posX,entityLiving.posY + 0.8,entityLiving.posZ,10,10, 10,1,1,1);
        entityLiving.setPositionAndUpdate(entityLiving.posX,entityLiving.posY,entityLiving.posZ);
        return false;
    }

}
