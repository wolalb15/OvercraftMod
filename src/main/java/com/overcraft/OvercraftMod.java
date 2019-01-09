package com.overcraft;

import com.overcraft.controller.Controller;
import com.overcraft.custom.EntityBullet;
import com.overcraft.custom.EntityFirestrike;
import com.overcraft.custom.EntityShield;
import com.overcraft.custom.EntityShuriken;
import com.overcraft.renderer.RenderBullet;
import com.overcraft.init.ModItems;

import com.overcraft.renderer.RenderGuiHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import java.rmi.registry.Registry;

@Mod(modid = OvercraftMod.MODID, name = OvercraftMod.NAME, version = OvercraftMod.VERSION)
public class OvercraftMod
{
    public static final String MODID = "overcraft";
    public static final String NAME = "OvercraftMod";
    public static final String VERSION = "1.0";

    public static EntityEntry BULLET;

    private static Logger logger;

    @Mod.Instance
    public static OvercraftMod instance;

    public Controller controller = new Controller();

    public static KeyBinding ULTIMATE = new KeyBinding("Ultimate",Keyboard.KEY_C,"category.OvercraftMod");
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

        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBullet::new);
        EntityRegistry.registerModEntity(new ResourceLocation(OvercraftMod.MODID, "bullet"), EntityBullet.class, "entity_bullet", 0, "overcraft",80, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(OvercraftMod.MODID, "shield"), EntityShield.class, "entity_shield", 0, "overcraft",80, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(OvercraftMod.MODID, "firestrike"), EntityFirestrike.class, "entity_firestrike", 0, "overcraft",80, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(OvercraftMod.MODID, "shuriken"), EntityShuriken.class, "entity_shuriken", 0, "overcraft",80, 1, false);

        ClientRegistry.registerKeyBinding(ULTIMATE);
        ClientRegistry.registerKeyBinding(ABILITY);
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }
    @EventHandler
    public void postInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
    }

    public static void load(){
               MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void registerEntity(RegistryEvent.Register<EntityEntry> event){
        IForgeRegistry<EntityEntry> registry = event.getRegistry();

        registry.register(BULLET);
    }
}
