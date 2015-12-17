package com.rumaruka.cg.common.items;

import com.rumaruka.cg.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class dustGold extends Item {
	
	public dustGold()
	{
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("dustGold");
		setTextureName(Reference.MOD_ID+":"+"dustGold");
	}

}
