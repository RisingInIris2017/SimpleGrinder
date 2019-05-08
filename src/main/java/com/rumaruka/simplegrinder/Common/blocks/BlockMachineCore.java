package com.rumaruka.simplegrinder.common.blocks;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class BlockMachineCore extends Block {

    public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;


    public static final ResourceLocation MACHINE_CORE = new ResourceLocation(SimpleGrinder.MODID, "machine_core");
    public BlockMachineCore( ) {
        super(Properties.create(Material.IRON));
        setRegistryName(MACHINE_CORE);
    }
}
