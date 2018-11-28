package com.overcraft.custom;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import paulscode.sound.Vector3D;

public class EntityBullet extends Entity {
	boolean isExplosive = false;
	public EntityBullet(World worldIn, double x, double y, double z,boolean isExplosive) {
		this(worldIn);
		this.setPosition(x, y, z);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		this.motionX = aim.x;
		this.motionY = aim.y;
		this.motionZ = aim.z;
		this.isExplosive = isExplosive;
	}

	public EntityBullet(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
		Vec3d aim = Minecraft.getMinecraft().player.getLookVec();
		this.motionX = aim.x;
        this.motionY = aim.y;
		this.motionZ = aim.z;
	}

	public EntityBullet(World worldIn) {
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
		
		if(world.getBlockState(this.getPosition()) != Blocks.AIR.getDefaultState()){
			onImpact();
		}
	}

	public void onImpact() {
		this.setDead();
		if(isExplosive){
			world.createExplosion(this,posX,posY,posZ,1,false);
		}
	}
	public void setSpeed(double x, double z){
		motionX = x;
		motionZ = z;
	}
	protected float getGravityVelocity() {
		return 0.01F;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}

}
