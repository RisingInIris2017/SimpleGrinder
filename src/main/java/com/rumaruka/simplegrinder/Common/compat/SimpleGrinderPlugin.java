package com.rumaruka.simplegrinder.Common.compat;

import com.rumaruka.simplegrinder.Init.ItemsCore;
import mezz.jei.api.*;

import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import mezz.jei.config.Config;
import mezz.jei.plugins.jei.ingredients.DebugIngredient;
import mezz.jei.plugins.jei.ingredients.DebugIngredientHelper;
import mezz.jei.plugins.jei.ingredients.DebugIngredientListFactory;
import mezz.jei.plugins.jei.ingredients.DebugIngredientRenderer;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rumaruka.simplegrinder.Client.gui.GuiCoalGrinder;
import com.rumaruka.simplegrinder.Common.compat.grinder.CoalGrinderingCategory;

import com.rumaruka.simplegrinder.Common.compat.grinder.GrinderingRecipeHandler;
import com.rumaruka.simplegrinder.Common.compat.grinder.GrinderingRecipeMaker;
import com.rumaruka.simplegrinder.Init.BlocksCore;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class SimpleGrinderPlugin extends BlankModPlugin implements IModPlugin {
	
	@Nullable
	public static IIngredientRegistry ingrReg;
	@Nullable
	public static IJeiRuntime jeiRun;
	

	@Override
	public void register(IModRegistry registry) {
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		ingrReg = registry.getIngredientRegistry();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		
		
        registry.addRecipeHandlers(
				
				
				new GrinderingRecipeHandler()
				
		);
        registry.addRecipeCategories(
    			
				new CoalGrinderingCategory(guiHelper)
			
				
		);
		registry.addRecipeClickArea(GuiCoalGrinder.class, 78, 32, 28, 23, SimpleGrinderRecipeUID.GRINDER_UID, VanillaRecipeCategoryUid.FUEL);

		
		registry.addDescription(new ItemStack(ItemsCore.i_fuel), "Very Powerful Fuel");
		registry.addRecipes(GrinderingRecipeMaker.getFurnaceRecipes(jeiHelpers));
		jeiHelpers.getItemBlacklist().addItemToBlacklist(new ItemStack(BlocksCore.lit_grinder));

	}
}
