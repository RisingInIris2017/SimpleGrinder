package com.rumaruka.cg.client.recipes;

import com.ibm.icu.util.Output;
import com.rumaruka.cg.client.registers.ModBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class VanillaRecipes {

	public static void loadingInitRecipes() {
		
		ItemStack output;

		output = new ItemStack(ModBlocks.MashineCausing);
		GameRegistry.addRecipe(output, new Object[]{
				"BIB",
				"BSB",
				"BIB",
				'B', Blocks.iron_bars, 'I', Items.iron_ingot, 'S', Blocks.stone
				
});
		output = new ItemStack(ModBlocks.CoalGrinder);
		GameRegistry.addRecipe(output, new Object[]{
				"CRC",
				"IMI",
				"SPS",
				'C', Blocks.cobblestone, 'I', Items.iron_ingot, 'M', ModBlocks.MashineCausing, 'S', Blocks.stone, 'P', Blocks.piston, 'R', Items.redstone
		});
		output = new ItemStack(ModBlocks.CoalGrinderII);
		GameRegistry.addRecipe(output, new Object[]{
				"IMI",
				"IGI",
				"SPS",
				 'I', Items.iron_ingot, 'G', ModBlocks.CoalGrinder, 'S', Blocks.stone, 'P', Blocks.piston, 'M', ModBlocks.MashineCausing
		});
	}
	
}
