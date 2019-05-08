package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.blocks.BlockCoalGrinder;
import com.rumaruka.simplegrinder.common.blocks.BlockMachineCore;
import com.rumaruka.simplegrinder.common.tile.TileEntityCoalGrinder;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class SGBlocks {

    //TilEntity
    public static TileEntityType<?> TYPE_COAL_GRINDER;
    @ObjectHolder("simplegrinder:machine_core")
    public static BlockMachineCore blockMachineCore;

    @ObjectHolder("simplegrinder:coal_grinder")
    public static BlockCoalGrinder blockCoalGrinder;






    public static void registerTiles(IForgeRegistry<TileEntityType<?>> registry) {
        registry.register(TYPE_COAL_GRINDER = TileEntityType.Builder.create(TileEntityCoalGrinder::new).build(null).setRegistryName(new ResourceLocation(SimpleGrinder.MODID, "coal_grinder")));

    }


    public static void register(IForgeRegistry<Block> registry) {
        registry.register(new BlockCoalGrinder());
        registry.register(new BlockMachineCore());



    }

}
