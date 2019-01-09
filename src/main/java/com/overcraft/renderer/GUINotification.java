package com.overcraft.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GUINotification extends Gui {
    String text = "Hello world!";

    public GUINotification(Minecraft mc) {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();

        drawCenteredString(mc.fontRenderer, text, width / 2, (height / 2) - 4, Integer.parseInt("FFAA00", 16));
    }
}