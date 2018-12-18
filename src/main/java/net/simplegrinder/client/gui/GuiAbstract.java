package net.simplegrinder.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.class_344;
import net.minecraft.client.gui.ContainerGui;
import net.minecraft.client.gui.container.RecipeBookGui;
import net.minecraft.client.gui.ingame.RecipeBookProvider;
import net.minecraft.container.ActionTypeSlot;
import net.minecraft.container.CraftingContainer;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Identifier;
import net.simplegrinder.client.gui.book.RecipeBookGrinderGui;
import net.simplegrinder.common.container.GrinderContainer;
import net.simplegrinder.common.tileentity.TileEntityCoalGrinder;

public abstract class GuiAbstract extends ContainerGui implements RecipeBookProvider {
    private static final Identifier RECIPE_BUTTON_TEXTURE = new Identifier("textures/gui/recipe_button.png");
    private final PlayerInventory playerInv;
    private final Inventory inventory;
    public final RecipeBookGrinderGui recipeBook;
    private boolean field_2925;

    public GuiAbstract(GrinderContainer var1, RecipeBookGrinderGui var2, PlayerInventory var3, Inventory var4) {
        super(var1);
        this.recipeBook = var2;
        this.playerInv = var3;
        this.inventory = var4;
    }

    public void onInitialized() {
        super.onInitialized();
        this.field_2925 = this.width < 379;
        this.recipeBook.method_2597(this.width, this.height, this.client, this.field_2925, (CraftingContainer)this.container);
        this.left = this.recipeBook.method_2595(this.field_2925, this.width, this.containerWidth);
        this.addButton(new class_344(10, this.left + 20, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_TEXTURE) {
            public void onPressed(double var1, double var3) {
                GuiAbstract.this.recipeBook.method_2579(GuiAbstract.this.field_2925);
                GuiAbstract.this.recipeBook.method_2591();
                GuiAbstract.this.left = GuiAbstract.this.recipeBook.method_2595(GuiAbstract.this.field_2925, GuiAbstract.this.width, GuiAbstract.this.containerWidth);
                this.getPos(GuiAbstract.this.left + 20, GuiAbstract.this.height / 2 - 49);
            }
        });
    }

    protected abstract Identifier method_17045();

    public void update() {
        super.update();
        this.recipeBook.update();
    }

    public void draw(int var1, int var2, float var3) {
        this.drawBackground();
        if (this.recipeBook.isOpen() && this.field_2925) {
            this.drawBackground(var3, var1, var2);
            this.recipeBook.method_2578(var1, var2, var3);
        } else {
            this.recipeBook.method_2578(var1, var2, var3);
            super.draw(var1, var2, var3);
            this.recipeBook.method_2581(this.left, this.top, true, var3);
        }

        this.drawMousoverTooltip(var1, var2);
        this.recipeBook.method_2601(this.left, this.top, var1, var2);
    }

    protected void drawForeground(int var1, int var2) {
        String var3 = this.inventory.getDisplayName().getFormattedText();
        this.fontRenderer.draw(var3, (float)(this.containerWidth / 2 - this.fontRenderer.getStringWidth(var3) / 2), 6.0F, 4210752);
        this.fontRenderer.draw(this.playerInv.getDisplayName().getFormattedText(), 8.0F, (float)(this.containerHeight - 96 + 2), 4210752);
    }

    protected void drawBackground(float var1, int var2, int var3) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(this.method_17045());
        int var4 = this.left;
        int var5 = this.top;
        this.drawTexturedRect(var4, var5, 0, 0, this.containerWidth, this.containerHeight);
        int var6;
        if (TileEntityCoalGrinder.method_11199(this.inventory)) {
            var6 = this.method_2482();
            this.drawTexturedRect(var4 + 56, var5 + 36 + 12 - var6, 176, 12 - var6, 14, var6 + 1);
        }

        var6 = this.method_2484();
        this.drawTexturedRect(var4 + 79, var5 + 34, 176, 14, var6 + 1, 16);
    }

    public boolean mouseClicked(double var1, double var3, int var5) {
        if (this.recipeBook.mouseClicked(var1, var3, var5)) {
            return true;
        } else {
            return this.field_2925 && this.recipeBook.isOpen() ? true : super.mouseClicked(var1, var3, var5);
        }
    }

    protected void onMouseClick(Slot var1, int var2, int var3, ActionTypeSlot var4) {
        super.onMouseClick(var1, var2, var3, var4);
        this.recipeBook.method_2600(var1);
    }

    public boolean keyPressed(int var1, int var2, int var3) {
        return this.recipeBook.keyPressed(var1, var2, var3) ? false : super.keyPressed(var1, var2, var3);
    }

    protected boolean isClickInContainerBounds(double var1, double var3, int var5, int var6, int var7) {
        boolean var8 = var1 < (double)var5 || var3 < (double)var6 || var1 >= (double)(var5 + this.containerWidth) || var3 >= (double)(var6 + this.containerHeight);
        return this.recipeBook.method_2598(var1, var3, this.left, this.top, this.containerWidth, this.containerHeight, var7) && var8;
    }

    public boolean charTyped(char var1, int var2) {
        return this.recipeBook.charTyped(var1, var2) ? true : super.charTyped(var1, var2);
    }

    public void method_16891() {
        this.recipeBook.method_2592();
    }

    public RecipeBookGui getRecipeBookGui() {
        return this.recipeBook;
    }

    public void onClosed() {
        this.recipeBook.close();
        super.onClosed();
    }

    private int method_2484() {
        int var1 = this.inventory.getInvProperty(2);
        int var2 = this.inventory.getInvProperty(3);
        return var2 != 0 && var1 != 0 ? var1 * 24 / var2 : 0;
    }

    private int method_2482() {
        int var1 = this.inventory.getInvProperty(1);
        if (var1 == 0) {
            var1 = 200;
        }

        return this.inventory.getInvProperty(0) * 13 / var1;
    }
}
