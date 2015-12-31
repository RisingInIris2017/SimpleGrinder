package com.rumaruka.cg.client.gui;

import org.lwjgl.opengl.GL11;

import com.rumaruka.cg.common.container.ContainerCoalGrinderII;
import com.rumaruka.cg.common.container.ContainerCoalGrinderIII;
import com.rumaruka.cg.common.tileentity.TileEntityCoalGrinderII;
import com.rumaruka.cg.common.tileentity.TileEntityCoalGrinderIII;
import com.rumaruka.cg.reference.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCoalGrinderIII extends GuiContainer{

	private ResourceLocation grindGUI = new ResourceLocation(Reference.MOD_ID+":"+"textures/gui/container/grindGui.png");
			
	private TileEntityCoalGrinderIII teCGIII;

	private static final String __OBFID = "CL_00000758";
	
	public GuiCoalGrinderIII(InventoryPlayer ip, TileEntityCoalGrinderIII teCGIII ) {
		super(new ContainerCoalGrinderIII(ip, teCGIII));
		this.teCGIII = teCGIII;
	}
	



	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = this.teCGIII.hasCustomInventoryName() ? this.teCGIII.getInventoryName():I18n.format(this.teCGIII.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize /2 - this.fontRendererObj.getStringWidth(s)/2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(grindGUI);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

	        if (this.teCGIII.isBurning())
	        {
	            int i1 = this.teCGIII.getBurnTimeRemainingScaled(13);
	            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
	            i1 = this.teCGIII.getCookProgressScaled(24);
	            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	        }
		
	}
	


}



