package com.rumaruka.simplegrinder.init.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.RecipeSerializers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.RecipeType;

public class SGRecipe {

    public static class Types {

        public static final RecipeType<GrinderRecipe> CRUSHER = RecipeType.get(new ResourceLocation("grinder"), GrinderRecipe.class);

    }

    public static class Serializers{

        static IRecipeSerializer<GrinderRecipe> CRUSHING;

        public static void register(){
            CRUSHING = RecipeSerializers.register(new GrinderRecipe.Serializer());
        }

    }
}
