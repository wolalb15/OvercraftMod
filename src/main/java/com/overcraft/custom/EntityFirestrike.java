package com.overcraft.custom;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityFirestrike extends EntityThrowable {
	boolean isExplosive = false;
	public EntityFirestrike(World worldIn, double x, double y, double z, boolean isExplosive) {
		this(worldIn);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		Vec3d rotAim = aim.rotateYaw(90);
		this.setPosition(x + (rotAim.x/2), y, z + (rotAim.z/2));
		this.motionX = aim.x;
		this.motionY = aim.y;
		this.motionZ = aim.z;
		this.isExplosive = isExplosive;
	}

	public EntityFirestrike(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		this.motionX = aim.x;
        this.motionY = aim.y;
		this.motionZ = aim.z;
	}

	public EntityFirestrike(World worldIn) {
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
		if(result.entityHit instanceof Entity) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)10);
			System.out.println("IMPACT");
		}

		if(world.getBlockState(this.getPosition()) != Blocks.AIR.getDefaultState()){
			this.setDead();
		}
		if(isExplosive){
			world.createExplosion(this,posX,posY,posZ,1,false);
		}

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {

	}

}
