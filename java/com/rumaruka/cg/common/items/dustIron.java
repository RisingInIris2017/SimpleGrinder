package com.rumaruka.cg.common.items;

import com.rumaruka.cg.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class dustIron extends Item {
	
	public dustIron()
	{
		super();
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("dustIron");
		setTextureName(Reference.MOD_ID+":"+"dustIron");
	}

}
