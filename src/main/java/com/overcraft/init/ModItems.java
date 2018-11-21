package com.overcraft.init;

import com.overcraft.OvercraftMod;
import com.overcraft.custom.EntityBullet;
import com.overcraft.items.weapons.*;
import com.overcraft.renderer.RenderBullet;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = OvercraftMod.MODID)
public class ModItems {
    public static Item TRACER_WEAPON, TRACER_BULLET, SOLDIER_WEAPON, GENJI_WEAPON_SHURIKEN,
        GENJI_WEAPON_TANTO, GENJI_WEAPON_DRAGONBLADE, LUCIO_WEAPON, REINHARDT_WEAPON;

    public static void preInit(){}

    public static void init() {
        TRACER_WEAPON = new TracerWeapon("tracer_gun");
        TRACER_BULLET = new TracerBullet("tracer_bullet");
        SOLDIER_WEAPON = new SoldierWeapon("soldier_gun");
        GENJI_WEAPON_SHURIKEN = new GenjiWeaponShuriken("genji_shuriken");
        GENJI_WEAPON_TANTO = new GenjiWeaponTanto("genji_tanto");
        GENJI_WEAPON_DRAGONBLADE = new GenjiWeaponDragonblade("genji_dragonblade");
        LUCIO_WEAPON = new LucioWeapon("lucio_gun");
        REINHARDT_WEAPON = new ReinhardtWeapon("reinhardt_hammer");
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBullet::new);
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TRACER_WEAPON,
                TRACER_BULLET, SOLDIER_WEAPON, GENJI_WEAPON_SHURIKEN, GENJI_WEAPON_TANTO, GENJI_WEAPON_DRAGONBLADE,
                LUCIO_WEAPON, REINHARDT_WEAPON);
    }

    @SubscribeEvent
    public static void registerRenderers(ModelRegistryEvent event) {
        registerRenderer(TRACER_BULLET);
        registerRenderer(TRACER_WEAPON);
        registerRenderer(SOLDIER_WEAPON);
        registerRenderer(GENJI_WEAPON_SHURIKEN);
        registerRenderer(GENJI_WEAPON_TANTO);
        registerRenderer(GENJI_WEAPON_DRAGONBLADE);
        registerRenderer(LUCIO_WEAPON);
        registerRenderer(REINHARDT_WEAPON);
    }

    private static void registerRenderer(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
}
