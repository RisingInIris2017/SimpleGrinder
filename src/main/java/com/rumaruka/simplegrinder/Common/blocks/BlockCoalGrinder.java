package com.rumaruka.simplegrinder.common.blocks;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.tile.TileEntityCoalGrinder;
import com.rumaruka.simplegrinder.util.SGUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCoalGrinder extends BlockContainer implements ITileEntityProvider{
    public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockRedstoneTorch.LIT;



    public static final ResourceLocation COAL_GRINDER = new ResourceLocation(SimpleGrinder.MODID, "coal_grinder");
    public BlockCoalGrinder( ) {
        super(Properties.from(Blocks.IRON_BLOCK));
         this.setDefaultState(getStateContainer().getBaseState().with(FACING,EnumFacing.NORTH).with(LIT,false));

        setRegistryName(COAL_GRINDER);
    }


    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileEntityFurnace) {
                ((TileEntityFurnace)tileentity).setCustomName(stack.getDisplayName());
            }
        }

    }

    public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileEntityFurnace) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnace)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    /**
     * @deprecated call via {@link IBlockState#hasComparatorInputOverride()} whenever possible. Implementing/overriding
     * is fine.
     */
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    /**
     * @deprecated call via {@link IBlockState#getComparatorInputOverride(World,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }


    @OnlyIn(Dist.CLIENT)
    public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LIT)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            EnumFacing enumfacing = stateIn.get(FACING);
            EnumFacing.Axis enumfacing$axis = enumfacing.getAxis();
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = enumfacing$axis == EnumFacing.Axis.X ? (double)enumfacing.getXOffset() * 0.52D : d4;
            double d6 = rand.nextDouble() * 6.0D / 16.0D;
            double d7 = enumfacing$axis == EnumFacing.Axis.Z ? (double)enumfacing.getZOffset() * 0.52D : d4;
            worldIn.spawnParticle(RedstoneParticleData.REDSTONE_DUST, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(RedstoneParticleData.REDSTONE_DUST, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }


    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }


    public IBlockState rotate(IBlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }


    public IBlockState mirror(IBlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
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



    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(FACING).add(LIT);
    }
}
