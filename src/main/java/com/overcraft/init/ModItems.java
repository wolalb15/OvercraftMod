package com.overcraft.init;

import com.overcraft.OvercraftMod;
import com.overcraft.items.weapons.TracerBullet;
import com.overcraft.items.weapons.TracerWeapon;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = OvercraftMod.MODID)
public class ModItems {
    public static Item TRACER_WEAPON, TRACER_BULLET;
    public static void init() {
        TRACER_WEAPON = new TracerWeapon("tracer_gun");
        TRACER_BULLET = new TracerBullet("tracer_bullet");

    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TRACER_WEAPON,TRACER_BULLET);
        event.getRegistry().registerAll(TRACER_BULLET);
    }

    @SubscribeEvent
    public static void registerRenderers(ModelRegistryEvent event) {
        registerRenderer(TRACER_BULLET);
        registerRenderer(TRACER_WEAPON);
    }

    private static void registerRenderer(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
}
