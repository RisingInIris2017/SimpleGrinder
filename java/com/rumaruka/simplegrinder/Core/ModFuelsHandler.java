package com.rumaruka.simplegrinder.Core;

import com.rumaruka.simplegrinder.Init.ItemsCore;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class ModFuelsHandler implements IFuelHandler{

	public ModFuelsHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem()== ItemsCore.i_fuel)
			return 30000;
		if(fuel.getItem()== ItemsCore.wood_chips)
			return 5;
		return 0;
		
	}

}
