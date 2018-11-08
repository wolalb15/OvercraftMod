package com.overcraft.custom;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomArrow extends EntityTippedArrow {

    public CustomArrow(World worldIn) {
        super(worldIn);
    }

    public CustomArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public CustomArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return ItemStack.EMPTY;
    }
}
