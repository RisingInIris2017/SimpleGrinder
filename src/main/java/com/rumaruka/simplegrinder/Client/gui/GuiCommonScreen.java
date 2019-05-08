package com.rumaruka.simplegrinder.client.gui;

import com.rumaruka.simplegrinder.client.gui.component.Component;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;

@OnlyIn(Dist.CLIENT)
public class GuiCommonScreen extends GuiContainer {
    private List<Component> components = new ArrayList<>();

    /** The player inventory bound to this GUI. */
    protected final InventoryPlayer playerInventory;
    protected final IInventory tileInventory;

    public GuiCommonScreen(Container container, InventoryPlayer playerInv, IInventory tileInventory) {
        super(container);
        this.playerInventory = playerInv;
        this.tileInventory = tileInventory;
    }

    @Override
    protected void initGui() {
        super.initGui();
        components.clear();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        super.render(mouseX, mouseY, partialTicks);

        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the item)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileInventory.getDisplayName().getFormattedText();
        this.fontRenderer.drawString(s, (float)(this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2), 6.0F, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    }

    /**
     * Draws the background layer of this container (behind the item).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        components.forEach(component -> {
            component.render(partialTicks,mouseX,mouseY);
        });
    }

    @Override
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
        super.renderHoveredToolTip(mouseX, mouseY);
        components.forEach(component -> {
            component.renderToolTipToolTip(this, mouseX, mouseY);
        });
    }

    @Override
    public boolean mouseClicked(double x, double y, int code) {
        components.forEach(component -> {
            MouseButton mouseButton = MouseButton.byCode(code);
            component.mouseClicked(x, y, mouseButton);
        });
        return super.mouseClicked(x, y, code);
    }

    @Override
    public boolean mouseReleased(double x, double y, int code) {
        components.forEach(component -> {
            MouseButton mouseButton = MouseButton.byCode(code);
            component.mouseReleased(x, y, mouseButton);
        });
        return super.mouseReleased(x, y, code);
    }

    protected void addComponent(Component component){
        components.add(component);
    }

    protected Component getComponent(int id){
        for (Component component : components) {
            if(component.getId() == id)return component;
        }
        return null;
    }

    public static boolean isMouseWithinRegion(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    public static boolean isMouseWithinRegion(Component component, int mouseX, int mouseY) {
        return isMouseWithinRegion(component.getX(),component.getY(),component.getWidth(),component.getHeight(),mouseX,mouseY);
    }
}
