package com.overcraft.custom;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityShield extends Entity {

	public EntityShield(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		this.motionX = aim.x;
		this.motionY = aim.y;
		this.motionZ = aim.z;
		this.setSize(6,2);
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

	@SubscribeEvent
	public void setPosition(double par1, double par2, double par3) {
		AxisAlignedBB b = this.getEntityBoundingBox();
		double boxSX = b.maxX - b.minX;
		double boxSY = b.maxY - b.minY;
		double boxSZ = b.maxZ - b.minZ;
		this.setEntityBoundingBox((new AxisAlignedBB(posX - boxSX/2D, posY, posZ - boxSZ/2D, posX + boxSX/2D, posY + boxSY, posZ + boxSZ/2D)));
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {

	}


}
