package net.simplegrinder.client.gui.book;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.class_308;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.container.RecipeBookGui;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.DefaultedList;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class RecipeBookGrinderGui extends RecipeBookGui {
    private Iterator<Item> field_3153;
    private Set<Item> field_3149;
    private Slot field_3150;
    private Item field_3152;
    private float field_3151;

    protected boolean method_2589() {
        boolean var1 = !this.method_17061();
        this.method_17060(var1);
        return var1;
    }

    protected abstract boolean method_17061();

    protected abstract void method_17060(boolean var1);

    public boolean isOpen() {
        return this.method_17062();
    }

    protected abstract boolean method_17062();

    protected void method_2593(boolean var1) {
        this.method_17063(var1);
        if (!var1) {
            this.field_3086.method_2638();
        }

        this.method_2588();
    }

    protected abstract void method_17063(boolean var1);

    protected void method_2585() {
        this.field_3088.method_1962(152, 182, 28, 18, TEXTURE);
    }

    protected String method_2599() {
        return I18n.translate(this.field_3088.method_1965() ? this.method_17064() : "gui.recipebook.toggleRecipes.all");
    }

    protected abstract String method_17064();

    public void method_2600(Slot var1) {
        super.method_2600(var1);
        if (var1 != null && var1.id < this.field_3095.method_7658()) {
            this.field_3150 = null;
        }

    }

    public void method_2596(Recipe var1, List<Slot> var2) {
        ItemStack var3 = var1.getOutput();
        this.field_3092.method_2565(var1);
        this.field_3092.method_2569(Ingredient.ofStacks(var3), ((Slot)var2.get(2)).xPosition, ((Slot)var2.get(2)).yPosition);
        DefaultedList<Ingredient> var4 = var1.getPreviewInputs();
        this.field_3150 = (Slot)var2.get(1);
        if (this.field_3149 == null) {
            this.field_3149 = this.method_17065();
        }

        this.field_3153 = this.field_3149.iterator();
        this.field_3152 = null;
        Iterator<Ingredient> var5 = var4.iterator();

        for(int var6 = 0; var6 < 2; ++var6) {
            if (!var5.hasNext()) {
                return;
            }

            Ingredient var7 = (Ingredient)var5.next();
            if (!var7.method_8103()) {
                Slot var8 = (Slot)var2.get(var6);
                this.field_3092.method_2569(var7, var8.xPosition, var8.yPosition);
            }
        }

    }

    protected abstract Set<Item> method_17065();

    public void method_2581(int var1, int var2, boolean var3, float var4) {
        super.method_2581(var1, var2, var3, var4);
        if (this.field_3150 != null) {
            if (!Gui.isControlPressed()) {
                this.field_3151 += var4;
            }

            class_308.method_1453();
            GlStateManager.disableLighting();
            int var5 = this.field_3150.xPosition + var1;
            int var6 = this.field_3150.yPosition + var2;
            Drawable.drawRect(var5, var6, var5 + 16, var6 + 16, 822018048);
            this.client.getItemRenderer().renderItemInGui(this.client.player, this.method_2658().getDefaultStack(), var5, var6);
            GlStateManager.depthFunc(516);
            Drawable.drawRect(var5, var6, var5 + 16, var6 + 16, 822083583);
            GlStateManager.depthFunc(515);
            GlStateManager.enableLighting();
            class_308.method_1450();
        }
    }

    private Item method_2658() {
        if (this.field_3152 == null || this.field_3151 > 30.0F) {
            this.field_3151 = 0.0F;
            if (this.field_3153 == null || !this.field_3153.hasNext()) {
                if (this.field_3149 == null) {
                    this.field_3149 = this.method_17065();
                }

                this.field_3153 = this.field_3149.iterator();
            }

            this.field_3152 = (Item)this.field_3153.next();
        }

        return this.field_3152;
    }
}
