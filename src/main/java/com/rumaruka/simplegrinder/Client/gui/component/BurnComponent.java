package com.rumaruka.simplegrinder.client.gui.component;


import net.minecraft.client.Minecraft;

public class BurnComponent extends com.rumaruka.simplegrinder.client.gui.component.Component {

    private int furnaceBurnTime = 0;
    private int currentItemBurnTime = 0;
    private boolean isBurning = false;

    public BurnComponent(int id, int x, int y) {
        super(id, x, y, 0, 0);
    }

    public BurnComponent setBurnTimes(int furnaceBurnTime, int currentItemBurnTime){
        this.furnaceBurnTime = furnaceBurnTime;
        this.currentItemBurnTime = currentItemBurnTime;
        return this;
    }

    public BurnComponent setBurning(boolean burning) {
        isBurning = burning;
        return this;
    }

    public BurnComponent update(boolean burning, int furnaceBurnTime, int currentItemBurnTime){
        this.isBurning = burning;
        this.furnaceBurnTime = furnaceBurnTime;
        this.currentItemBurnTime = currentItemBurnTime;
        return this;
    }

    @Override
    public void render(float partialTicks, int mouseX, int mouseY) {
        if (isBurning && visible) {
            Minecraft.getInstance().getTextureManager().bindTexture(COMPONENT_TEXTURES);
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(x, y-k, 0, 93 - k, 14, k + 1);
        }
    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.currentItemBurnTime;
        if (i == 0)i = 200;
        return this.furnaceBurnTime * pixels / i;
    }
}
