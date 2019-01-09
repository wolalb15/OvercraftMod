package com.overcraft.items.weapons;

import com.overcraft.custom.CustomArrow;
import com.overcraft.custom.CustomParticle;
import com.overcraft.custom.EntityBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class TracerWeapon extends Item {
    public TracerWeapon (String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        Vec3d aim = playerIn.getLookVec();
        if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem()==Item.getByNameOrId("overcraft:tracer_bullet")
                && playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getCount() != 0
                || Minecraft.getMinecraft().playerController.getCurrentGameType()==GameType.CREATIVE) {
            ItemStack slot = playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
            slot.setCount(slot.getCount() - 1);

            /*EntityArrow bullet = new CustomArrow(worldIn, playerIn);
            bullet.shoot(playerIn, 0, 0, 0, 100, 1);
            bullet.setPosition(playerIn.posX, playerIn.posY + 1.5, playerIn.posZ);
            bullet.setDamage(1);

            bullet.setVelocity(aim.x * 10, aim.y * 10, aim.z * 10);

            worldIn.spawnEntity(bullet);*/




                if(worldIn.isRemote){
                EntityBullet bullet = new EntityBullet(worldIn, playerIn.posX + aim.x, playerIn.posY + aim.y + 1.5, playerIn.posZ + aim.z,false);

                worldIn.spawnEntity(bullet);}


        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return false;
    }

}
