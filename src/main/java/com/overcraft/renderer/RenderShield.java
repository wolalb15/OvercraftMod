package com.overcraft.renderer;

import com.overcraft.custom.EntityBullet;
import com.overcraft.custom.EntityShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderShield extends Render<EntityShield> {

	private ModelShield model;

	public RenderShield(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelShield();
	}

	@Override
	public void doRender(EntityShield entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(getEntityTexture(entity));
		GlStateManager.translate(x, y, z);
		this.model.render(entity, 0, 0, 0, 0, 0, 2);
		GlStateManager.translate(-x, -y, -z);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityShield entity)
	{
		return new ResourceLocation("overcraft:textures/items/voxel-00b5ff.png");
	}

}
