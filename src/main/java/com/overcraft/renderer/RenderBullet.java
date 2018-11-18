package com.overcraft.renderer;

import com.overcraft.custom.EntityBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBullet extends Render<EntityBullet> {

	private ModelBullet model;

	public RenderBullet(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelBullet();
	}

	@Override
	public void doRender(EntityBullet entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(getEntityTexture(entity));
		GlStateManager.translate(x, y, z);
		this.model.render(entity, 0, 0, 0, 0, 0, (1.0F / 16.0F) * (16.0F / 18.0F));
		GlStateManager.translate(-x, -y, -z);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBullet entity)
	{
		return new ResourceLocation("overcraft:textures/items/voxel-00b5ff.png");
	}

}
