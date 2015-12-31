package com.rumaruka.cg.client.gui.creativetabs;

import com.rumaruka.cg.client.registers.ModBlocks;
import com.rumaruka.cg.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsSG {
	
	public static final CreativeTabs SG_TAB = new CreativeTabs(Reference.NAME_MOD)
			{
		  @Override
	        public Item getTabIconItem()
	        {
	            return Item.getItemFromBlock(ModBlocks.MashineCausing);
	        }
	    };
}
