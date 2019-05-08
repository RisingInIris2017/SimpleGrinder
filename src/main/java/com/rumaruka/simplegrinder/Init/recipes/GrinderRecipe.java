package com.rumaruka.simplegrinder.init.recipes;

import com.google.gson.JsonObject;
import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.util.SGUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GrinderRecipe implements IRecipe {
    private final ResourceLocation ID;
    private final String GROUP;
    private final Ingredient INPUT;
    private final ItemStack OUTPUT;
    private final int COOKING_TIME;

    public GrinderRecipe(ResourceLocation id, String group, Ingredient input, ItemStack output, int cookingTime) {
        this.ID = id;
        this.GROUP = group;
        this.INPUT = input;
        this.OUTPUT = output;
        this.COOKING_TIME = cookingTime;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.INPUT.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.OUTPUT.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.INPUT);
        return nonnulllist;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SGRecipe.Serializers.CRUSHING;
    }

    /**
     * Get the result of this recipes, usually for display purposes (e.g. recipes book). If your recipes has more than one
     * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
     */
    @Override
    public ItemStack getRecipeOutput() {
        return this.OUTPUT;
    }

    /**
     * Recipes with equal group are combined into one button in the recipes book
     */
    @Override
    public String getGroup() {
        return this.GROUP;
    }

    public int getCookingTime() {
        return this.COOKING_TIME;
    }

    @Override
    public ResourceLocation getId() {
        return this.ID;
    }

    @Override
    public net.minecraftforge.common.crafting.RecipeType<GrinderRecipe> getType() {
        return SGRecipe.Types.CRUSHER;
    }

    public static class Serializer implements IRecipeSerializer<GrinderRecipe> {

        private static ResourceLocation NAME = new ResourceLocation(SimpleGrinder.MODID, "grinder");

        public GrinderRecipe read(ResourceLocation recipeId, JsonObject json) {
            String group = JsonUtils.getString(json, "group", "");
            Ingredient ingredient;
            if (JsonUtils.isJsonArray(json, "ingredient")) {
                ingredient = Ingredient.fromJson(JsonUtils.getJsonArray(json, "ingredient"));
            } else {
                ingredient = Ingredient.fromJson(JsonUtils.getJsonObject(json, "ingredient"));
            }

            String itemResourceName = JsonUtils.getString(json, "result");
            int count = JsonUtils.getInt(json,"count");
            Item item = SGUtils.getItemFromResource(itemResourceName);
            if (item != null) {
                ItemStack itemstack = new ItemStack(item,count);
                int cookingTime = JsonUtils.getInt(json, "cookingtime", 200);
                return new GrinderRecipe(recipeId, group, ingredient, itemstack, cookingTime);
            } else {
                throw new IllegalStateException(itemResourceName + " did not exist");
            }
        }

        public GrinderRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            Ingredient ingredient = Ingredient.fromBuffer(buffer);
            ItemStack itemstack = buffer.readItemStack();
            int i = buffer.readVarInt();
            return new GrinderRecipe(recipeId, s, ingredient, itemstack, i);
        }

        public void write(PacketBuffer buffer, GrinderRecipe recipe) {
            buffer.writeString(recipe.GROUP);
            recipe.INPUT.writeToBuffer(buffer);
            buffer.writeItemStack(recipe.OUTPUT);
            buffer.writeVarInt(recipe.COOKING_TIME);
        }

        @Override
        public ResourceLocation getName() {
            return NAME;
        }
    }

}
