package com.rumaruka.simplegrinder.Common.compat.grinder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rumaruka.simplegrinder.Common.compat.SimpleGrinderRecipeUID;
import com.rumaruka.simplegrinder.Init.BlocksCore;
import com.rumaruka.simplegrinder.Reference.Reference;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.config.Constants;
import mezz.jei.util.Translator;

import net.minecraft.util.ResourceLocation;

public class CoalGrinderingCategory extends GrinderingRecipeCategory<GrinderingRecipe> {

	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final String localizedName;
	//private final IDrawable icon;
	
	public CoalGrinderingCategory(IGuiHelper guiHelper) {
		super(guiHelper);
		ResourceLocation location = new ResourceLocation(Reference.MODID,"textures/gui/container/grind.png");
		background = guiHelper.createDrawable(location, 55, 16, 82, 54);
		localizedName = Translator.translateToLocal("gui.jei.category.grinder");
		
		
		
	}

	@Override
	public String getUid() {
		// TODO Auto-generated method stub
		return SimpleGrinderRecipeUID.GRINDER_UID;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return localizedName;
	}

	@Override
	public String getModName() {
		return Reference.MODNAME;
	}

	@Override
	public IDrawable getBackground() {
		// TODO Auto-generated method stub
		return background;
	}
	/*@Nullable
	@Override
	public IDrawable getIcon() {
		return icon;
	}*/

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, GrinderingRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(inputSlot, true, 0, 0);
		guiItemStacks.init(outputSlot, false, 60, 18);
		guiItemStacks.set(ingredients);
		
	}
	

}
