package com.rumaruka.simplegrinder.Client.gui;
import com.rumaruka.simplegrinder.Common.containers.ContainerCoaGrinder;
import com.rumaruka.simplegrinder.Common.tileentity.TileEntityCoalGrinder;
import com.rumaruka.simplegrinder.Reference.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiCoalGrinder extends GuiContainer
{
	
		private static final ResourceLocation grindGuiTextures = new ResourceLocation(Reference.MODID,"textures/gui/container/grind.png");
    	private final InventoryPlayer playerInventory;
	    private final IInventory tileGrind;

	    public GuiCoalGrinder(InventoryPlayer playerInv, IInventory grindInv)
	    {
	        super(new ContainerCoaGrinder(playerInv, grindInv));
	        this.playerInventory = playerInv;
	        this.tileGrind = grindInv;
	    }

	    /**
	     * Draw the foreground layer for the GuiContainer (everything in front of the items)
	     */
	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        String s = this.tileGrind.getDisplayName().getUnformattedText();
	        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
	        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	    }

	    /**
	     * Draws the background layer of this container (behind the items).
	     */
	    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	    {
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(grindGuiTextures);
	        int i = (this.width - this.xSize) / 2;
	        int j = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

	        if (TileEntityCoalGrinder.isBurning(this.tileGrind))
	        {
	            int k = this.getBurnLeftScaled(13);
	            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
	        }

	        int l = this.getCookProgressScaled(24);
	        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
	    }

	    private int getCookProgressScaled(int pixels)
	    {
	        int i = this.tileGrind.getField(2);
	        int j = this.tileGrind.getField(3);
	        return j != 0 && i != 0 ? i * pixels / j : 0;
	    }

	    private int getBurnLeftScaled(int pixels)
	    {
	        int i = this.tileGrind.getField(1);

	        if (i == 0)
	        {
	            i = 200;
	        }

	        return this.tileGrind.getField(0) * pixels / i;
	    }
	}