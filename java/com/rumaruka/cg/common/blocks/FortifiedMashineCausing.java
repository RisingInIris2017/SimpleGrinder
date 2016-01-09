package com.rumaruka.cg.common.blocks;

import com.rumaruka.cg.client.gui.creativetabs.CreativeTabsSG;
import com.rumaruka.cg.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FortifiedMashineCausing extends Block {

	public FortifiedMashineCausing( ) {
		super(Material.rock);
		setCreativeTab(CreativeTabsSG.SG_TAB);
		setBlockName("FortifiedMashineCausing");
		setBlockTextureName(Reference.MOD_ID+":"+"FMC");
		setHarvestLevel("pickaxe", 3);
		setHardness(50.0F);
	}

}
