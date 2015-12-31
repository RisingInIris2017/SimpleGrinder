package com.rumaruka.cg.common.items;

import com.rumaruka.cg.client.gui.creativetabs.CreativeTabsSG;
import com.rumaruka.cg.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class flour extends Item {
	
	public flour()
	{
		super();
		setCreativeTab(CreativeTabsSG.SG_TAB);
		setTextureName(Reference.MOD_ID+":"+"Flour");
		setUnlocalizedName("Flour");
	}

}
