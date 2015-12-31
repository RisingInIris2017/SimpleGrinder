package com.rumaruka.cg.common.items;

import com.rumaruka.cg.client.gui.creativetabs.CreativeTabsSG;
import com.rumaruka.cg.reference.Reference;

import net.minecraft.item.Item;

public class obsidianDust extends Item {

	public obsidianDust() {
		super();
		setCreativeTab(CreativeTabsSG.SG_TAB);
		setUnlocalizedName("obsdianDust");
		setTextureName(Reference.MOD_ID+":"+"obsdianDust");
	}
}
