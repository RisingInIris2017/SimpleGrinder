package com.rumaruka.simplegrinder.common.items;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemOmlete extends ItemFood {

    public static final ResourceLocation OMLETE = new ResourceLocation(SimpleGrinder.MODID,"omlete");
    public ItemOmlete(int healAmountIn, float saturation, boolean meat) {
          super(healAmountIn, saturation, meat, new Properties().group(SimpleGrinder.simple_grinder_itemgroup));

          setRegistryName(OMLETE);
    }



}
