package com.overcraft;

import com.overcraft.custom.CustomArrow;
import com.overcraft.init.ModItems;
import com.overcraft.test.EntityComet;
import com.overcraft.test.RenderComet;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = OvercraftMod.MODID, name = OvercraftMod.NAME, version = OvercraftMod.VERSION)
public class OvercraftMod
{
    public static final String MODID = "overcraft";
    public static final String NAME = "OvercraftMod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Mod.Instance
    public static OvercraftMod instance;

    public static KeyBinding ULTIMATE = new KeyBinding("Ultimate",Keyboard.KEY_P,"category.OvercraftMod");
    public static KeyBinding ABILITY = new KeyBinding("Ability",Keyboard.KEY_V,"category.OvercraftMod");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
       ModItems.init();
        System.out.println("PreInit Overcraft");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityComet.class, RenderComet::new);
        EntityRegistry.registerModEntity(new ResourceLocation(OvercraftMod.MODID, "comet"), EntityComet.class, "entity_dull_arrow", 0, "overcraft",80, 1, false);
        ClientRegistry.registerKeyBinding(ULTIMATE);
        ClientRegistry.registerKeyBinding(ABILITY);

        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }
}
