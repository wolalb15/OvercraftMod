package com.overcraft.custom;

import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import static net.minecraft.util.EnumParticleTypes.WATER_SPLASH;

public class CustomParticle extends Particle {


    public  CustomParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
    }

    public CustomParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }

    public void setSize(float d1,float d2){
        super.setSize(d1,d2);
        super.particleScale=100;
    }
    public void setTexture()
    {
        super.setParticleTextureIndex(WATER_SPLASH.getParticleID());
        super.setRBGColorF(255,255,0);
    }

}
