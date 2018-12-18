package net.simplegrinder.client.gui.book;

import net.minecraft.item.Item;
import net.simplegrinder.common.tileentity.TileEntityCoalGrinder;

import java.util.Set;

public class GrinderBookGui extends RecipeBookGrinderGui {
    protected boolean method_17061() {
        return this.recipeBook.method_17319();
    }

    protected void method_17060(boolean var1) {
        this.recipeBook.method_17320(var1);
    }

    protected boolean method_17062() {
        return this.recipeBook.method_17317();
    }

    protected void method_17063(boolean var1) {
        this.recipeBook.method_17318(var1);
    }

    protected String method_17064() {
        return "gui.recipebook.toggleRecipes.grinder";
    }

    protected Set<Item> method_17065() {
        return TileEntityCoalGrinder.method_11196().keySet();
    }
}
