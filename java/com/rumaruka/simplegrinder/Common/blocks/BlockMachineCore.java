package com.rumaruka.simplegrinder.Common.blocks;

import java.util.Random;

import com.rumaruka.simplegrinder.Init.BlocksCore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockMachineCore extends Block {

	public BlockMachineCore( ) {
		super(Material.ROCK);
		setHarvestLevel("pickaxe", 0);
		setHardness(2.3F);
	}
	 public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return Item.getItemFromBlock(BlocksCore.machine_core);
	    }

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
}
