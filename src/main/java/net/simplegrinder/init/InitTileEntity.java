package net.simplegrinder.init;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.simplegrinder.SimpleGrinderMod;
import net.simplegrinder.common.tileentity.TileEntityCoalGrinder;

public class InitTileEntity {

    public static final BlockEntityType<TileEntityCoalGrinder> TILE_ENTITY_COAL_GRINDER_BLOCK_ENTITY_TYPE = BlockEntityType.Builder.create(TileEntityCoalGrinder::new).method_11034(null);


    public static void load(){
        Registry.register(Registry.BLOCK_ENTITY, new Identifier(SimpleGrinderMod.MODID, "coal_grinder"), TILE_ENTITY_COAL_GRINDER_BLOCK_ENTITY_TYPE);

    }
}
