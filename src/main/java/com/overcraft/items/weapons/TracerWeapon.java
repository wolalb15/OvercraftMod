package com.tt.overcraft.items.weapons;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TracerWeapon extends Item {
    public TracerWeapon (String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
    }
}
