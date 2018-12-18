package net.simplegrinder.common.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.class_3866;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.simplegrinder.common.tileentity.TileEntityCoalGrinder;

import java.util.Random;

public class GrinderBlock extends BlockCoalGrinder {



    public GrinderBlock() {
        super(Settings.of(Material.METAL));
    }

    @Override
    protected void method_17025(World var1, BlockPos var2, PlayerEntity var3) {
        BlockEntity var4 = var1.getBlockEntity(var2);
        if (var4 instanceof TileEntityCoalGrinder) {
            var3.openContainer((TileEntityCoalGrinder)var4);
            var3.method_7281(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new TileEntityCoalGrinder();
    }
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState var1, World var2, BlockPos var3, Random var4) {
        if ((Boolean)var1.get(field_11105)) {
            double var5 = (double)var3.getX() + 0.5D;
            double var7 = (double)var3.getY();
            double var9 = (double)var3.getZ() + 0.5D;
            if (var4.nextDouble() < 0.1D) {
                var2.playSound(var5, var7, var9, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCK, 1.0F, 1.0F, false);
            }

            Direction var11 = (Direction)var1.get(FACING);
            Direction.Axis var12 = var11.getAxis();
            double var13 = 0.52D;
            double var15 = var4.nextDouble() * 0.6D - 0.3D;
            double var17 = var12 == Direction.Axis.X ? (double)var11.getOffsetX() * 0.52D : var15;
            double var19 = var4.nextDouble() * 6.0D / 16.0D;
            double var21 = var12 == Direction.Axis.Z ? (double)var11.getOffsetZ() * 0.52D : var15;
            var2.method_8406(ParticleTypes.SMOKE, var5 + var17, var7 + var19, var9 + var21, 0.0D, 0.0D, 0.0D);
            var2.method_8406(ParticleTypes.FLAME, var5 + var17, var7 + var19, var9 + var21, 0.0D, 0.0D, 0.0D);
        }
    }
}
