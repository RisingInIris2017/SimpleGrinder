package com.rumaruka.cg.client.recipes;

import com.rumaruka.cg.client.registers.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FurnaceSmeltingRecipe {
	
	public static void smeltingDust()
	{
		GameRegistry.addSmelting(new ItemStack(ModItems.dustIron), new ItemStack(Items.iron_ingot), 0.5f);
		GameRegistry.addSmelting(new ItemStack(ModItems.dustGold), new ItemStack(Items.gold_ingot), 0.5f);
		GameRegistry.addSmelting(new ItemStack(ModItems.Flour), new ItemStack(Items.bread), 0.5f);
	}
 
}
