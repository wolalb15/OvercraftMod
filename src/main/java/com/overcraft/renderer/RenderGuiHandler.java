package com.overcraft.renderer;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGuiHandler
{
    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;
        //new GUINotification(Minecraft.getMinecraft());
    }
}