package com.rumaruka.simplegrinder.common.blocks;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.tile.TileEntityCoalGrinder;
import com.rumaruka.simplegrinder.util.SGUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCoalGrinder extends BlockContainer implements ITileEntityProvider{
    public static final DirectionProperty FACING = DirectionProperty.create("facing",EnumFacing.values());
    public static final BooleanProperty LIT = BooleanProperty.create("lit");



    public static final ResourceLocation COAL_GRINDER = new ResourceLocation(SimpleGrinder.MODID, "coal_grinder");
    public BlockCoalGrinder( ) {
        super(Properties.from(Blocks.IRON_BLOCK));
         this.setDefaultState(getStateContainer().getBaseState().with(FACING,EnumFacing.NORTH).with(LIT,false));

        setRegistryName(COAL_GRINDER);
    }



    @Override
    public int getLightValue(IBlockState state, IWorldReader world, BlockPos pos) {
        return state.get(LIT)?super.getLightValue(state):0;
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileEntityCoalGrinder){
            TileEntityCoalGrinder tileEntityCoalGrinder = (TileEntityCoalGrinder) tileEntity;
            if(tileEntityCoalGrinder.getGuiID() !=null && player instanceof EntityPlayerMP){
                SGUtils.openGui((EntityPlayerMP) player,pos,tileEntityCoalGrinder);
                player.addStat(StatList.INTERACT_WITH_FURNACE);
                return true;
            }
        }
        return false;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityCoalGrinder();
    }


    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        return this.getDefaultState().with(FACING,EnumFacing.getFacingFromVector(pos.getX(),pos.getY(),pos.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(FACING).add(LIT);
    }
}
