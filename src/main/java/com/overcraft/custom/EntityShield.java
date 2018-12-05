package com.overcraft.custom;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityShield extends Entity {

	public EntityShield(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		this.motionX = aim.x;
		this.motionY = aim.y;
		this.motionZ = aim.z;
	}


	public EntityShield(World worldIn) {
		super(worldIn);
		this.setSize(0.8F, 0.8F);
	}

	@Override
	protected void entityInit() {

	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}
	
	@Override
	public void onUpdate() {

	}




	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {

	}

}
