package com.rumaruka.simplegrinder.client.gui;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.client.gui.component.BurnComponent;
import com.rumaruka.simplegrinder.common.inventory.ContainerCoalGrinder;
import com.rumaruka.simplegrinder.common.tile.TileEntityCoalGrinder;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class GuiCoalGrinder extends GuiCommonScreen {

    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(SimpleGrinder.MODID,"textures/gui/container/machines/grind.png");
    public GuiCoalGrinder(InventoryPlayer inventoryPlayer, IInventory inventory) {
        super(new ContainerCoalGrinder(inventoryPlayer,inventory),inventoryPlayer,inventory);
    }

    @Override
    protected void initGui() {
        super.initGui();
        addComponent(new BurnComponent(0,guiLeft + 56, guiTop + 36 + 12));
    }

    @Override
    public void tick() {
        super.tick();
    }
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        //GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int cookProgressScaled = this.getCookProgressScaled(17);
        this.drawTexturedModalRect(i + 79, j + 34+1, 176, 0, 24, cookProgressScaled);

        boolean isBurning = TileEntityCoalGrinder.isBurning(this.tileInventory);
        int burnTime = tileInventory.getField(0);
        int currentItemBurnTime = tileInventory.getField(1);

        ((BurnComponent)getComponent(0)).update(isBurning,burnTime,currentItemBurnTime);

        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
    }

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    private int getCookProgressScaled(int pixels) {
        int i = this.tileInventory.getField(2);
        int j = this.tileInventory.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

}
