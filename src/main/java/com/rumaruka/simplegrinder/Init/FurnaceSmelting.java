package com.rumaruka.simplegrinder.Init;

import com.rumaruka.simplegrinder.Crafting.RecipeHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnaceSmelting {
	
	public static void Smelting(){
		RecipeHelper.addSmelting(new ItemStack(ItemsCore.dust_iron),  new ItemStack(Items.IRON_INGOT), 0.5F);
		RecipeHelper.addSmelting(new ItemStack(ItemsCore.dust_gold),  new ItemStack(Items.GOLD_INGOT), 0.5F);
		RecipeHelper.addSmelting(new ItemStack(ItemsCore.flour),  new ItemStack(Items.BREAD), 0.5F);
	}

}
