package com.overcraft.items.weapons;

import com.google.common.collect.Multimap;
import com.overcraft.custom.CustomParticle;
import com.overcraft.custom.EntityBullet;
import com.overcraft.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class GenjiWeaponDragonblade extends ItemSword {
    public static final Item.ToolMaterial GENJI_DRAGONBLADE_MATERIAL = EnumHelper.addToolMaterial("GENJI_MATERIAL", 0, -1,
            0, 10, 0).setRepairItem(new ItemStack(Items.IRON_INGOT));


    public GenjiWeaponDragonblade(String name) {
        super(GENJI_DRAGONBLADE_MATERIAL);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);
        setMaxDamage(100);
    }

    boolean direction = true;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.inventory.getCurrentItem();
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }

    @SubscribeEvent
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        super.onUpdate(itemstack, world, entity, i, flag);

    }


    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return false;

    }
    @Override
    public boolean hitEntity(ItemStack item, EntityLivingBase entity, EntityLivingBase player)
    {
        item.damageItem(0, player);
        return true;
    }
}