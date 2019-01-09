package com.overcraft.custom;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityBoop extends EntityThrowable {
	boolean realBoop = false;
	int birthTick;
	public EntityBoop(World worldIn, double x, double y, double z, boolean realBoop) {
		this(worldIn);
		this.setPosition(x, y, z);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		this.motionX = aim.x;
		this.motionY = aim.y;
		this.motionZ = aim.z;
		this.realBoop = realBoop;
		this.birthTick = Minecraft.getMinecraft().player.ticksExisted;
	}

	public EntityBoop(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		this.motionX = aim.x;
        this.motionY = aim.y;
		this.motionZ = aim.z;
	}

	public EntityBoop(World worldIn) {
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
		super.onUpdate();
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		if(birthTick + 5 < Minecraft.getMinecraft().player.ticksExisted && realBoop){
			this.setDead();
		}
		//this.motionY -= getGravityVelocity();


	}


	public void setSpeed(double x, double z){
		motionX = x;
		motionZ = z;
	}
	protected float getGravityVelocity() {
		return 0.01F;
	}

	@SubscribeEvent
	public void onImpact(RayTraceResult result) {
		this.setDead();
		if(result.entityHit instanceof Entity) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)10);
			System.out.println("IMPACT");
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {

	}

}
