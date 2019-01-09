package com.overcraft.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShuriken extends ModelBase {

	ModelRenderer box1;


	public ModelShuriken() {
		textureWidth = 16;
		textureHeight = 16;

		float x = 1.0F;
		float y = -11.0F;
		float z = 0.5F;
		
		box1 = new ModelRenderer(this, 0, 0);
		box1.addBox(0, 0, 0, 2, 1, 1);
		box1.addBox(0,0,0,1,1,2);
		box1.setRotationPoint(0F, 0F, 0F);
		box1.setTextureSize(16, 16);
		box1.mirror = true;
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.box1.render(scale);}

}
