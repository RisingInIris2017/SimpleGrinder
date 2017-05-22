package com.rumaruka.simplegrinder.Common.compat;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import mezz.jei.config.Config;
import mezz.jei.plugins.jei.ingredients.DebugIngredient;
import mezz.jei.plugins.jei.ingredients.DebugIngredientHelper;
import mezz.jei.plugins.jei.ingredients.DebugIngredientListFactory;
import mezz.jei.plugins.jei.ingredients.DebugIngredientRenderer;
import mezz.jei.plugins.vanilla.brewing.BrewingRecipeCategory;

import mezz.jei.plugins.vanilla.brewing.BrewingRecipeMaker;
import mezz.jei.plugins.vanilla.crafting.CraftingRecipeCategory;

import mezz.jei.plugins.vanilla.crafting.TippedArrowRecipeMaker;

import mezz.jei.plugins.vanilla.furnace.FuelRecipeMaker;
import mezz.jei.plugins.vanilla.furnace.FurnaceFuelCategory;
import mezz.jei.plugins.vanilla.furnace.FurnaceSmeltingCategory;

import mezz.jei.plugins.vanilla.furnace.SmeltingRecipeMaker;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rumaruka.simplegrinder.Client.gui.GuiCoalGrinder;
import com.rumaruka.simplegrinder.Common.compat.grinder.CoalGrinderingCategory;
import com.rumaruka.simplegrinder.Common.compat.grinder.GrinderingRecipeCategory;
import com.rumaruka.simplegrinder.Common.compat.grinder.GrinderingRecipeHandler;
import com.rumaruka.simplegrinder.Common.compat.grinder.GrinderingRecipeMaker;
import com.rumaruka.simplegrinder.Init.BlocksCore;

@JEIPlugin
public class SimpleGrinderPlugin extends BlankModPlugin {
	
	@Nullable
	public static IIngredientRegistry ingrReg;
	@Nullable
	public static IJeiRuntime jeiRun;
	/*
	@Override
	public void registerIngredients(IModIngredientRegistration ingredientRegistration) {
		if (Config.isDebugModeEnabled()) {
			ingredientRegistration.register(DebugIngredient.class, DebugIngredientListFactory.create(), new DebugIngredientHelper(), new DebugIngredientRenderer());
		}
	}*/
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

		

		registry.addRecipes(GrinderingRecipeMaker.getFurnaceRecipes(jeiHelpers));
	
	}
}
