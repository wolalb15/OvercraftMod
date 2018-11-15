package com.overcraft.test;

import com.overcraft.OvercraftMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderComet extends Render<EntityComet> {

	private ModelComet model;
	
	public RenderComet(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelComet();
	}

	@Override
	public void doRender(EntityComet entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(getEntityTexture(entity));
		GlStateManager.translate(x, y, z);
		this.model.render(entity, 0, 0, 0, 0, 0, (1.0F / 16.0F) * (16.0F / 18.0F));
		GlStateManager.translate(-x, -y, -z);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityComet entity) {
		return new ResourceLocation("overcraft:items/voxel-00b5ff.png");
	}

}
