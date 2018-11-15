package com.overcraft.test;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityComet extends Entity {

	public EntityComet(World worldIn, int x, int y, int z) {
		this(worldIn);
		this.setPosition(x, y, z);
	}

	public EntityComet(World worldIn) {
		super(worldIn);
		this.setSize(0.8F, 0.8F);
	}

	@Override
	protected void entityInit() {
		System.out.println("w�oghjweg");
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		
		for(double f = 0; f < Math.abs(this.motionY); f+= 0.1){
			double rnda = (Math.random() - 0.5) * 0.5;
			double rndb = (Math.random() - 0.5) * 0.5;
			world.spawnParticle(EnumParticleTypes.FLAME, this.posX + rnda, this.posY + 0.5 + f, this.posZ + rndb, 0, 0.2, 0);
			rnda = (Math.random() - 0.5) * 0.5;
			rndb = (Math.random() - 0.5) * 0.5;
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + rnda, this.posY + 0.5 + f, this.posZ + rndb, 0, 0, 0);
		}
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

		this.motionY -= getGravityVelocity();
		
		if(world.getBlockState(this.getPosition()) != Blocks.AIR.getDefaultState()){
			onImpact();
		}
	}

	public void onImpact() {
		this.setDead();
		world.createExplosion(this, posX, world.getHeight((int) posX, (int) posZ), posZ, 3, true);
		//world.setBlockState(new BlockPos(posX, world.getHeight((int) posX, (int) posZ), posZ), Block.getStateById(1));
	}
	
	protected float getGravityVelocity() {
		return 0.03F;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}

}
