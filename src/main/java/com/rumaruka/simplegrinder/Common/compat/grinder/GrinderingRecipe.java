package com.rumaruka.simplegrinder.Common.compat.grinder;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rumaruka.simplegrinder.Common.compat.SimpleGrinderWrapper;
import com.rumaruka.simplegrinder.Crafting.GrinderHandler;
import com.rumaruka.simplegrinder.Init.GrinderRecipes;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;


public class GrinderingRecipe extends SimpleGrinderWrapper{
	
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;
	public GrinderingRecipe(List<ItemStack> inputs, ItemStack output) {
		this.inputs = Collections.singletonList(inputs);
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
		
	}
	public List<List<ItemStack>> getInputs() {
		return inputs;
	}

	public List<ItemStack> getOutputs() {
		return Collections.singletonList(output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		GrinderHandler furnaceRecipes = GrinderHandler.instance();
		float experience = furnaceRecipes.getExperience(output);
		if (experience > 0) {
			String experienceString = Translator.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
			FontRenderer fontRendererObj = minecraft.fontRenderer;
			int stringWidth = fontRendererObj.getStringWidth(experienceString);
			fontRendererObj.drawString(experienceString, recipeWidth - stringWidth, 0, Color.gray.getRGB());
		}
	}
	
}
