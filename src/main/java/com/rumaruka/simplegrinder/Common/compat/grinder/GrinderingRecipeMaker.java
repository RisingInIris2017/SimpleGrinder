package com.rumaruka.simplegrinder.Common.compat.grinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.rumaruka.simplegrinder.Crafting.GrinderHandler;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;

import net.minecraft.item.ItemStack;


public class GrinderingRecipeMaker {
	public static List<GrinderingRecipe> getFurnaceRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		GrinderHandler furnaceRecipes = GrinderHandler.instance();
		Map<ItemStack, ItemStack> smeltingMap = furnaceRecipes.getSmeltingList();

		List<GrinderingRecipe> recipes = new ArrayList<GrinderingRecipe>();

		for (Map.Entry<ItemStack, ItemStack> entry : smeltingMap.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			GrinderingRecipe recipe = new GrinderingRecipe(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}

}
