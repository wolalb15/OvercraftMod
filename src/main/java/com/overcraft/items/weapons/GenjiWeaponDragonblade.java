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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class GenjiWeaponDragonblade extends ItemSword {
    public GenjiWeaponDragonblade(String name) {
        super(ToolMaterial.IRON);
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
        itemStack.getTagCompound().setInteger( "Damage", 200);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }

    @SubscribeEvent
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag){
        super.onUpdate(itemstack,world,entity,i,flag);

    }


    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return false;

    }


    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        final Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);

        if (slot == EntityEquipmentSlot.MAINHAND) {
            replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, 100); // 2 1.5
            replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, 10); // 4 0.625
        }

        return modifiers;
    }

    /**
     * Replace a modifier in the {@link Multimap} with a copy that's had
     * {@code multiplier} applied to its value.
     *
     * @param modifierMultimap
     *            The MultiMap
     * @param attribute
     *            The attribute being modified
     * @param id
     *            The ID of the modifier
     * @param multiplier
     *            The multiplier to apply
     */
    private void replaceModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id,
                                 double multiplier) {
        // Get the modifiers for the specified attribute
        final Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());

        // Find the modifier with the specified ID, if any
        final Optional<AttributeModifier> modifierOptional = modifiers.stream()
                .filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

        if (modifierOptional.isPresent()) { // If it exists,
            final AttributeModifier modifier = modifierOptional.get();

            modifiers.remove(modifier); // Remove it
            modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), multiplier,
                    modifier.getOperation())); // Add the new modifier
        }
    }
}
