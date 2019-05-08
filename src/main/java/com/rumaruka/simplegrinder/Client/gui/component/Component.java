package com.rumaruka.simplegrinder.client.gui.component;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.client.gui.GuiCommonScreen;
import com.rumaruka.simplegrinder.client.gui.MouseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public abstract class Component extends Gui {

    protected static final ResourceLocation COMPONENT_TEXTURES = new ResourceLocation(SimpleGrinder.MODID,"textures/gui/container/gui_components.png");

    /** Component width in pixels */
    protected int width;
    /** Component height in pixels */
    protected int height;
    /** The x position of this component. */
    protected int x;
    /** The y position of this component. */
    protected int y;

    protected final int id;
    protected List<String> toolTipText;

    /** True if this control is enabled, false to disable. */
    public boolean enabled = true;
    /** Hides the button completely if false. */
    public boolean visible = true;
    protected boolean hovered;

    public Component(int id, int x, int y, int width, int height){
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns 0 if the component is disabled, 1 if the mouse is NOT hovering over this component and 2 if it IS hovering over
     * this component.
     */
    protected int getHoverState(boolean mouseOver) {
        int i = 1;
        if (!this.enabled) {
            i = 0;
        } else if (mouseOver) {
            i = 2;
        }
        return i;
    }

    public abstract void render(float partialTicks, int mouseX, int mouseY);

    public boolean mouseClicked(double x, double y, MouseButton mouseButton) {
        return true;
    }

    public boolean mouseReleased(double x, double y, MouseButton mouseButton) {
        return true;
    }

    public void renderToolTipToolTip(GuiCommonScreen screen, int mouseX, int mouseY){
        if(isMouseOver()){
            if( toolTipText != null && !toolTipText.isEmpty()){
                screen.drawHoveringText(getToolTipText(), mouseX, mouseY);
            }
        }
    }

    public Component setPos(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public void setToolTipText(List<String> toolTipText) {
        this.toolTipText = toolTipText;
    }

    public List<String> getToolTipText() {
        return toolTipText;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() {
        return this.hovered;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    protected void bindTexture(ResourceLocation resource){
        Minecraft.getInstance().getTextureManager().bindTexture(resource);
    }

}
