package net.simplegrinder.common.blocks;



import net.minecraft.block.*;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.container.FurnaceGui;
import net.minecraft.container.Container;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sortme.ItemScatterer;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.simplegrinder.common.tileentity.TileEntityCoalGrinder;

public abstract class BlockCoalGrinder extends BlockWithEntity {

    public static final DirectionProperty FACING;
    public static final BooleanProperty field_11105;

    protected BlockCoalGrinder(Block.Settings var1) {
        super(var1);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(FACING, Direction.NORTH)).with(field_11105, false));
    }

    public int getLuminance(BlockState var1) {
        return (Boolean)var1.get(field_11105) ? super.getLuminance(var1) : 0;
    }

    public boolean activate(BlockState var1, World var2, BlockPos var3, PlayerEntity var4, Hand var5, Direction var6, float var7, float var8, float var9) {
        if (!var2.isRemote) {
            this.method_17025(var2, var3, var4);
        }

        return true;
    }

    protected abstract void method_17025(World var1, BlockPos var2, PlayerEntity var3);

    public BlockState getPlacementState(ItemPlacementContext var1) {
        return (BlockState)this.getDefaultState().with(FACING, var1.getPlayerHorizontalFacing().getOpposite());
    }

    public void onPlaced(World var1, BlockPos var2, BlockState var3, LivingEntity var4, ItemStack var5) {
        if (var5.hasDisplayName()) {
            BlockEntity var6 = var1.getBlockEntity(var2);
            if (var6 instanceof TileEntityCoalGrinder) {
                ((TileEntityCoalGrinder)var6).setCustomName(var5.getDisplayName());
            }
        }

    }

    public void onBlockRemoved(BlockState var1, World var2, BlockPos var3, BlockState var4, boolean var5) {
        if (var1.getBlock() != var4.getBlock()) {
            BlockEntity var6 = var2.getBlockEntity(var3);
            if (var6 instanceof TileEntityCoalGrinder) {
                ItemScatterer.spawn(var2, (BlockPos)var3, (TileEntityCoalGrinder)var6);
                var2.updateHorizontalAdjacent(var3, this);
            }

            super.onBlockRemoved(var1, var2, var3, var4, var5);
        }
    }

    public boolean hasComparatorOutput(BlockState var1) {
        return true;
    }

    public int getComparatorOutput(BlockState var1, World var2, BlockPos var3) {
        return Container.calculateComparatorOutput(var2.getBlockEntity(var3));
    }

    public RenderTypeBlock getRenderType(BlockState var1) {
        return RenderTypeBlock.MODEL;
    }

    public BlockState applyRotation(BlockState var1, Rotation var2) {
        return (BlockState)var1.with(FACING, var2.method_10503((Direction)var1.get(FACING)));
    }

    public BlockState applyMirror(BlockState var1, Mirror var2) {
        return var1.applyRotation(var2.getRotation((Direction)var1.get(FACING)));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> var1) {
        var1.with(FACING, field_11105);
    }

    static {
        FACING = HorizontalFacingBlock.field_11177;
        field_11105 = RedstoneTorchBlock.field_11446;
    }
}
