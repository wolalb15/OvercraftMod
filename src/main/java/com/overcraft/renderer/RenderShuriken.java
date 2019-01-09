package com.overcraft.renderer;

import com.overcraft.custom.EntityShuriken;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderShuriken extends Render<EntityShuriken> {

	private ModelShuriken model;
	private String color;

	public RenderShuriken(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelShuriken();
	}

	@Override
	public void doRender(EntityShuriken entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(getEntityTexture(entity));
		GlStateManager.translate(x, y, z);
		this.model.render(entity, 0, 0, 0, 0, 0, (1.0F / 16.0F) * (16.0F / 18.0F));
		GlStateManager.translate(-x, -y, -z);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityShuriken entity)
	{

		return new ResourceLocation("overcraft:textures/items/green.png");
	}

}
