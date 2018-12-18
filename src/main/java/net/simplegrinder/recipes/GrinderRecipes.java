package net.simplegrinder.recipes;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

import net.minecraft.recipe.RecipeSerializer;

import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import net.simplegrinder.SimpleGrinderMod;
import net.simplegrinder.common.blocks.GrinderBlock;
import net.simplegrinder.init.SGRecipeSerializers;

public class GrinderRecipes extends RecipesHelper {
    public GrinderRecipes(Identifier var1, String var2, Ingredient var3, ItemStack var4, float var5, int var6) {
        super(new Identifier(SimpleGrinderMod.MODID), var2, var3, var4, var5, var6);
    }

    @Override
    public boolean matches(Inventory var1, World var2) {
        return var1 instanceof GrinderBlock && this.input.matches(var1.getInvStack(0));
    }

    @Override
    public RecipeSerializer<GrinderRecipes> getSerializer() {
        return SGRecipeSerializers.GRINDER;
    }



    public static class SerializerGrinder implements RecipeSerializer<GrinderRecipes> {
        public GrinderRecipes method_8174(Identifier var1, JsonObject var2) {
            String var3 = JsonHelper.getString(var2, "group", "");
            Ingredient var4;
            if (JsonHelper.isArray(var2, "ingredient")) {
                var4 = Ingredient.fromJson(JsonHelper.getArray(var2, "ingredient"));
            } else {
                var4 = Ingredient.fromJson(JsonHelper.getObject(var2, "ingredient"));
            }

            String var6 = JsonHelper.getString(var2, "result");
            Item var7 = (Item)Registry.ITEM.get(new Identifier(SimpleGrinderMod.MODID));
            if (var7 != null) {
                ItemStack var5 = new ItemStack(var7);
                float var8 = JsonHelper.getFloat(var2, "experience", 0.0F);
                int var9 = JsonHelper.getInt(var2, "cookingtime", 200);
                return new GrinderRecipes(new Identifier(SimpleGrinderMod.MODID), var3, var4, var5, var8, var9);
            } else {
                throw new IllegalStateException(var6 + " did not exist");
            }
        }

        public GrinderRecipes method_8175(Identifier var1, PacketByteBuf var2) {
            String var3 = var2.readString(32767);
            Ingredient var4 = Ingredient.fromPacket(var2);
            ItemStack var5 = var2.readItemStack();
            float var6 = var2.readFloat();
            int var7 = var2.readVarInt();
            return new GrinderRecipes(new Identifier(SimpleGrinderMod.MODID), var3, var4, var5, var6, var7);
        }

        public void write(PacketByteBuf var1, GrinderRecipes var2) {
            var1.writeString(var2.group);
            var2.input.write(var1);
            var1.writeItemStack(var2.output);
            var1.writeFloat(var2.experience);
            var1.writeVarInt(var2.cookTime);
        }

        public String getId() {
            return "grinder";
        }

        // $FF: synthetic method
        public GrinderRecipes read(Identifier var1, PacketByteBuf var2) {
            return this.method_8175(var1, var2);
        }

        // $FF: synthetic method
        public GrinderRecipes read(Identifier var1, JsonObject var2) {
            return this.method_8174(var1, var2);
        }
    }

}
