package com.overcraft.test;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.Entity;

public class ModelComet extends ModelBase {

	ModelRenderer box1;
	ModelRenderer box2;
	ModelRenderer box3;
	ModelRenderer box5;
	ModelRenderer box6;
	ModelRenderer box7;
	ModelRenderer box8;
	ModelRenderer box4;
	ModelRenderer box9;
	ModelRenderer box10;

	public ModelComet() {
		textureWidth = 32;
		textureHeight = 32;

		float x = 1.0F;
		float y = -11.0F;
		float z = 0.5F;
		
		box1 = new ModelRenderer(this, 0, 0);
		box1.addBox(0, 0, 0, 2, 2, 1);
		box1.setRotationPoint(0F, 0F, 0F);
		box1.setTextureSize(32, 32);
		box1.mirror = true;
		/*
		box2 = new ModelRenderer(this, 0, 0);
		box2.addBox(x, y, z, 7, 4, 7);
		box2.setRotationPoint(-3F, 21F, -4F);
		box2.setTextureSize(128, 64);
		box2.mirror = true;
		box3 = new ModelRenderer(this, 0, 0);
		box3.addBox(x, y, z, 7, 3, 7);
		box3.setRotationPoint(-6F, 21F, -7F);
		box3.setTextureSize(128, 64);
		box3.mirror = true;
		box5 = new ModelRenderer(this, 0, 0);
		box5.addBox(x, y, z, 5, 5, 5);
		box5.setRotationPoint(-4F, 19F, 0F);
		box5.setTextureSize(128, 64);
		box5.mirror = true;
		box6 = new ModelRenderer(this, 0, 0);
		box6.addBox(x, y, z, 10, 3, 9);
		box6.setRotationPoint(-5F, 18F, -5F);
		box6.setTextureSize(128, 64);
		box6.mirror = true;
		box7 = new ModelRenderer(this, 0, 0);
		box7.addBox(x, y, z, 7, 3, 8);
		box7.setRotationPoint(-8F, 18F, -8F);
		box7.setTextureSize(128, 64);
		box7.mirror = true;
		box8 = new ModelRenderer(this, 0, 0);
		box8.addBox(x, y, z, 12, 3, 12);
		box8.setRotationPoint(-8F, 16F, -6F);
		box8.setTextureSize(128, 64);
		box8.mirror = true;
		box4 = new ModelRenderer(this, 0, 0);
		box4.addBox(x, y, z, 6, 3, 6);
		box4.setRotationPoint(-4F, 13F, -1F);
		box4.setTextureSize(128, 64);
		box4.mirror = true;
		box9 = new ModelRenderer(this, 0, 0);
		box9.addBox(x, y, z, 6, 2, 6);
		box9.setRotationPoint(-7F, 14F, -4F);
		box9.setTextureSize(128, 64);
		box9.mirror = true;
		box10 = new ModelRenderer(this, 0, 0);
		box10.addBox(x, y, z, 7, 4, 2);
		box10.setRotationPoint(-4F, 19F, 5F);
		box10.setTextureSize(128, 64);
		box10.mirror = true;*/
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.box1.render(scale);
		/*this.box2.render(scale);
		this.box3.render(scale);
		this.box4.render(scale);
		this.box5.render(scale);
		this.box6.render(scale);
		this.box7.render(scale);
		this.box8.render(scale);
		this.box9.render(scale);
		this.box10.render(scale);*/
	}

}
