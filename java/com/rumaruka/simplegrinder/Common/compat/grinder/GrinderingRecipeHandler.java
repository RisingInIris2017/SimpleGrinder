package com.rumaruka.simplegrinder.Common.compat.grinder;

import javax.annotation.Nonnull;

import com.rumaruka.simplegrinder.Common.compat.SimpleGrinderRecipeUID;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.plugins.vanilla.furnace.SmeltingRecipe;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;

public class GrinderingRecipeHandler implements IRecipeHandler<GrinderingRecipe> {

	@Override
	public Class<GrinderingRecipe> getRecipeClass() {
		// TODO Auto-generated method stub
		return GrinderingRecipe.class;
	}

	@Override
	public String getRecipeCategoryUid(GrinderingRecipe recipe) {
		// TODO Auto-generated method stub
		return SimpleGrinderRecipeUID.GRINDER_UID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(GrinderingRecipe recipe) {
		// TODO Auto-generated method stub
		return recipe;
	}

	@Override
	public boolean isRecipeValid(GrinderingRecipe recipe) {
		if(recipe.getInputs().isEmpty()){
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
			Log.error("Resipe has no inputs. {}", recipeInfo);
		}
		if(recipe.getOutputs().isEmpty()){
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
			Log.error("Recipe has no outputs. {}", recipeInfo);
		}
		return true;
	}

}
