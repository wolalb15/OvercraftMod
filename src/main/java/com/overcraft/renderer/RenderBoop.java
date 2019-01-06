package com.overcraft.renderer;

import com.overcraft.custom.EntityBoop;
import com.overcraft.custom.EntityBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBoop extends Render<EntityBoop> {

	private ModelBoop model;
	private String color;

	public RenderBoop(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelBoop();
	}

	@Override
	public void doRender(EntityBoop entity, double x, double y, double z, float entityYaw, float partialTicks) {
		float yaw = Minecraft.getMinecraft().player.cameraYaw;
		super.doRender(entity, x, y, z, yaw, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(getEntityTexture(entity));
		GlStateManager.translate(x, y, z);
		this.model.render(entity, 0, 0, 0, 0, 0, (1.0F / 16.0F) * (16.0F / 18.0F));
		GlStateManager.translate(-x, -y, -z);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBoop entity)
	{

		return new ResourceLocation("overcraft:textures/items/green.png");
	}

}
