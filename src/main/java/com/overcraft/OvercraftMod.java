package com.overcraft;

import com.overcraft.init.ModItems;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

    public static KeyBinding ult = new KeyBinding("Ultimate",Keyboard.KEY_P,"category.OvercraftMod");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
       ModItems.init();
        System.out.println("PreInit Overcraft");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientRegistry.registerKeyBinding(ult);
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }
}
