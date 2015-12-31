package com.rumaruka.cg.common.blocks;

import com.rumaruka.cg.client.gui.creativetabs.CreativeTabsSG;
import com.rumaruka.cg.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class MashineCausing extends Block {

	public MashineCausing( ) {
		super(Material.rock);
		setCreativeTab(CreativeTabsSG.SG_TAB);
		setBlockTextureName(Reference.MOD_ID+":"+"MashineCausing");
		setBlockName("MashineCausing");
		setHardness(3.5F);
		setLightLevel(1F);
		setHarvestLevel("pickaxe", 0);
	}
	 
}
