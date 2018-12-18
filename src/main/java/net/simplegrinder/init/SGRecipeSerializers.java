package net.simplegrinder.init;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeSerializers;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import net.simplegrinder.SimpleGrinderMod;
import net.simplegrinder.recipes.GrinderRecipes;

import java.util.Map;
import java.util.function.Function;

public class SGRecipeSerializers {


        private static final Map<String, RecipeSerializer<?>> serializers = Maps.newHashMap();
        public static final RecipeSerializer<GrinderRecipes> GRINDER = register(new GrinderRecipes.SerializerGrinder());

        public static <S extends RecipeSerializer<T>, T extends Recipe> S register(S var0) {
                if (serializers.containsKey(var0.getId())) {
                        throw new IllegalArgumentException("Duplicate recipe serializer " + var0.getId());
                } else {
                        serializers.put(var0.getId(), var0);
                        return var0;
                }
        }

        public static Recipe fromJson(Identifier var0, JsonObject var1) {
                String var2 = JsonHelper.getString(var1, "type");
                RecipeSerializer<?> var3 = (RecipeSerializer)serializers.get(var2);
                if (var3 == null) {
                        throw new JsonSyntaxException("Invalid or unsupported recipe type '" + var2 + "'");
                } else {
                        return var3.read(new Identifier(SimpleGrinderMod.MODID), var1);
                }
        }

        public static Recipe fromPacket(PacketByteBuf var0) {
                Identifier var1 = new Identifier(SimpleGrinderMod.MODID);
                String var2 = var0.readString(32767);
                RecipeSerializer<?> var3 = (RecipeSerializer)serializers.get(var2);
                if (var3 == null) {
                        throw new IllegalArgumentException("Unknown recipe serializer " + var2);
                } else {
                        return var3.read(var1, var0);
                }
        }

        public static <T extends Recipe> void toPacket(GrinderRecipes var0, PacketByteBuf var1) {
                var1.writeIdentifier(var0.getId());
                var1.writeString(var0.getSerializer().getId());
                RecipeSerializer<T> var2 = (RecipeSerializer<T>) var0.getSerializer();
                var2.write(var1, (T) var0);
        }

        public static final class Dummy<T extends Recipe> implements RecipeSerializer<T> {
                private final String id;
                private final Function<Identifier, T> supplier;

                public Dummy(String var1, Function<Identifier, T> var2) {
                        this.id = var1;
                        this.supplier = var2;
                }


                @Override
                public T read(Identifier var1, JsonObject var2) {
                        return this.supplier.apply(new Identifier(SimpleGrinderMod.MODID));
                }

                @Override
                public T read(Identifier var1, PacketByteBuf var2) {
                        return this.supplier.apply(new Identifier(SimpleGrinderMod.MODID));
                }

                public void write(PacketByteBuf var1, T var2) {
                }

                public String getId() {
                        return this.id;
                }
        }


}
