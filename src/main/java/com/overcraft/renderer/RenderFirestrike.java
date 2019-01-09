package com.overcraft.renderer;

import com.overcraft.custom.EntityFirestrike;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFirestrike extends Render<EntityFirestrike> {

	private ModelFirestrike model;

	public RenderFirestrike(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelFirestrike();
	}

	@Override
	public void doRender(EntityFirestrike entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(getEntityTexture(entity));
		GlStateManager.translate(x, y, z);
		this.model.render(entity, 0, 0, 0, 0, 0, 1);
		GlStateManager.translate(-x, -y, -z);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFirestrike entity)
	{
		return new ResourceLocation("overcraft:textures/items/red.png");
	}

}

