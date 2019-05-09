package com.rumaruka.simplegrinder.common.items;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemMashPotato extends ItemFood {
    public static final ResourceLocation MASH_POTATO = new ResourceLocation(SimpleGrinder.MODID,"mash_potato");
    public ItemMashPotato(int healAmountIn, float saturation, boolean meat) {
          super(healAmountIn, saturation, meat, new Properties().group(SimpleGrinder.simple_grinder_itemgroup));
          setRegistryName(MASH_POTATO);
    }


    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(!worldIn.isRemote){
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,200,0));
        }
        else {
            super.onFoodEaten(stack, worldIn, player);
        }
    }
}
