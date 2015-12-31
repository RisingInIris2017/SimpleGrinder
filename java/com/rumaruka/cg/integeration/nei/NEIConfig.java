package com.rumaruka.cg.integeration.nei;

import com.rumaruka.cg.client.registers.ModBlocks;
import com.rumaruka.cg.reference.Reference;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class NEIConfig implements IConfigureNEI{

	
	@Override
	public void loadConfig() {

		API.registerRecipeHandler(new GrindingRecipesHandler());
		API.registerUsageHandler(new GrindingRecipesHandler());
		
		API.registerRecipeHandler(new GrindingRecipesHandlerII());
		API.registerUsageHandler(new GrindingRecipesHandlerII());
		
		API.registerRecipeHandler(new GrindRecipesHandlerIII());
		API.registerUsageHandler(new GrindRecipesHandlerIII());
		
		//Hide method
		API.hideItem(new ItemStack(ModBlocks.lit_grind,1,OreDictionary.WILDCARD_VALUE));
		API.hideItem(new ItemStack(ModBlocks.lit_grindII,1,OreDictionary.WILDCARD_VALUE));
		API.hideItem(new ItemStack(ModBlocks.lit_grindIII,1,OreDictionary.WILDCARD_VALUE));
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Reference.NAME_MOD;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return Reference.VERSION_MOD;
	}

}
