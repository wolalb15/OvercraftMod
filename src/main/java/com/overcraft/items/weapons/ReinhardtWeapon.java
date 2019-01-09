package com.overcraft.items.weapons;

import com.overcraft.controller.Controller;
import com.overcraft.custom.CustomParticle;
import com.overcraft.custom.EntityBullet;
import com.overcraft.custom.EntityShield;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

public class ReinhardtWeapon extends ItemSword {
    public static final Item.ToolMaterial REINHARDT_MATERIAL = EnumHelper.addToolMaterial("REINHARDT_MATERIAL", 0, -1,
            0, 8, 0).setRepairItem(new ItemStack(Items.IRON_INGOT));

    public ReinhardtWeapon(String name) {
        super(REINHARDT_MATERIAL);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);
    }
    boolean direction = true;
    EntityShield shield = null;
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        try {
            Vec3d aim = playerIn.getLookVec();
            if (shield == null) {
                shield = Controller.getShield();
            } else {
                shield.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
            }
            worldIn.spawnEntity(shield);
        } catch(Exception e){

        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        Vec3d aim = entityLiving.getLookVec();
        World world = entityLiving.getEntityWorld();

        CustomParticle cp = new CustomParticle(entityLiving.getEntityWorld(),entityLiving.posX, entityLiving.posY,entityLiving.posZ);
        cp.setSize(20f,20f);
        Minecraft.getMinecraft().effectRenderer.addEffect(cp);
        entityLiving.setPositionAndUpdate(entityLiving.posX,entityLiving.posY,entityLiving.posZ);
        return false;
    }


    @SubscribeEvent
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag){
            try {
                if(Controller.isShieldActive) {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    shield.setPositionAndUpdate(player.posX, player.posY, player.posZ);
                }
                else{
                    world.removeEntity(shield);
                }
            } catch (NullPointerException e) {
            }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {

    }

}
