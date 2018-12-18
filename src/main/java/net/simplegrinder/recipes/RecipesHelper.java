package net.simplegrinder.recipes;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.simplegrinder.SimpleGrinderMod;

public abstract class RecipesHelper implements Recipe {
    public final Identifier id;
    public final String group;
    public final Ingredient input;
    public final ItemStack output;
    public final float experience;
    public final int cookTime;

    public RecipesHelper(Identifier var1, String var2, Ingredient var3, ItemStack var4, float var5, int var6) {
        this.id = new Identifier(SimpleGrinderMod.MODID);
        this.group = var2;
        this.input = var3;
        this.output = var4;
        this.experience = var5;
        this.cookTime = var6;
    }

    public ItemStack craft(Inventory var1) {
        return this.output.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int var1, int var2) {
        return true;
    }

    public DefaultedList<Ingredient> getPreviewInputs() {
        DefaultedList<Ingredient> var1 = DefaultedList.create();
        var1.add(this.input);
        return var1;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    @Environment(EnvType.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public Identifier getId() {
        return this.id;
    }
}
