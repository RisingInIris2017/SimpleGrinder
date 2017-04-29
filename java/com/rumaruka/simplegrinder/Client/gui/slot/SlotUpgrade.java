package com.rumaruka.simplegrinder.Client.gui.slot;

import com.rumaruka.simplegrinder.Common.tileentity.TileEntityCoalGrinder;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;

public class SlotUpgrade extends Slot{

	public SlotUpgrade(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		
	}
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
		// TODO Auto-generated method stub
		//return TileEntityCoalGrinder.isItemUpgraded(stack);
	}
	

}
