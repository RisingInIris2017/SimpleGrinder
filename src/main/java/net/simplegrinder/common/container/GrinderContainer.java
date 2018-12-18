package net.simplegrinder.common.container;



import net.minecraft.container.FurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.simplegrinder.common.tileentity.TileEntityCoalGrinder;
import net.simplegrinder.recipes.GrinderRecipes;

import java.util.Iterator;

public class GrinderContainer extends ContainerCoalGrinder {
    public GrinderContainer(PlayerInventory var1, Inventory var2) {
        super(var1, var2);
    }

    protected boolean method_7640(ItemStack var1) {
        Iterator var2 = this.field_7822.getRecipeManager().values().iterator();

        Recipe var3;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            var3 = (Recipe)var2.next();
        } while(!(var3 instanceof GrinderRecipes) || !((Ingredient)var3.getPreviewInputs().get(0)).matches(var1));

        return true;
    }

    public boolean method_16945(ItemStack var1) {
        return TileEntityCoalGrinder.canUseAsFuel(var1);
    }
}
