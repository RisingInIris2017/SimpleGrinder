package com.rumaruka.simplegrinder.util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
public class SGUtils {

    public static void openGui(EntityPlayerMP player, BlockPos pos, IInteractionObject interactionObject){
        NetworkHooks.openGui(player, interactionObject, (buffer) -> buffer.writeBlockPos(pos));
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't fuel
     */
    public static int getItemBurnTime(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            Item item = stack.getItem();
            int ret = stack.getBurnTime();
            return net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack, ret == -1 ? TileEntityFurnace.getBurnTimes().getOrDefault(item, 0) : ret);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return SGUtils.getItemBurnTime(stack) > 0;
    }

    public static Item getItemFromResource(String resourceName){
        return getItemFromResource(new ResourceLocation(resourceName));
    }

    public static Item getItemFromResource(ResourceLocation resourceLocation){
        return IRegistry.field_212630_s.func_212608_b(resourceLocation);
    }

    public static Block getBlockFromResource(String resourceName){
        return getBlockFromResource(new ResourceLocation(resourceName));
    }

    public static Block getBlockFromResource(ResourceLocation resourceLocation){
        return IRegistry.field_212618_g.func_212608_b(resourceLocation);
    }

    public static List<TileEntity> getTilesAroundTile(TileEntity tileEntity){
        List<TileEntity> tileEntities = new ArrayList<>();
        List<TileEntity> tileEntitiesRemove = new ArrayList<>();
        tileEntities.add(tileEntity.getWorld().getTileEntity(tileEntity.getPos().north()));
        tileEntities.add(tileEntity.getWorld().getTileEntity(tileEntity.getPos().east()));
        tileEntities.add(tileEntity.getWorld().getTileEntity(tileEntity.getPos().south()));
        tileEntities.add(tileEntity.getWorld().getTileEntity(tileEntity.getPos().west()));
        tileEntities.add(tileEntity.getWorld().getTileEntity(tileEntity.getPos().up()));
        tileEntities.add(tileEntity.getWorld().getTileEntity(tileEntity.getPos().down()));
        for (TileEntity entity : tileEntities) {
            if(entity == null)tileEntitiesRemove.add(entity);
        }
        tileEntities.removeAll(tileEntitiesRemove);
        return tileEntities;
    }

    @Nullable
    public static TileEntity getTileFromFacing(TileEntity tileEntity, EnumFacing enumFacing){
        switch (enumFacing){
            case NORTH:
                return tileEntity.getWorld().getTileEntity(tileEntity.getPos().north());
            case EAST:
                return tileEntity.getWorld().getTileEntity(tileEntity.getPos().east());
            case SOUTH:
                return tileEntity.getWorld().getTileEntity(tileEntity.getPos().south());
            case WEST:
                return tileEntity.getWorld().getTileEntity(tileEntity.getPos().west());
            case UP:
                return tileEntity.getWorld().getTileEntity(tileEntity.getPos().up());
            case DOWN:
                return tileEntity.getWorld().getTileEntity(tileEntity.getPos().down());
        }
        return null;
    }

    public static List<Long> blockPosListToLongList(List<BlockPos> blockPosList){
        List<Long> longList = new ArrayList<>();
        blockPosList.forEach(blockPos -> {
            longList.add(blockPos.toLong());
        });
        return longList;
    }

    public static List<BlockPos> longListToBlockPosList(List<Long> longList){
        List<BlockPos> blockPosList = new ArrayList<>();
        longList.forEach(aLong -> {
            blockPosList.add(BlockPos.fromLong(aLong));
        });
        return blockPosList;
    }

    // Function to remove duplicates from an ArrayList
    public static <T> List<T> removeDuplicates(List<T> list) {
        // Create a new ArrayList
        List<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) newList.add(element);
        }
        // return the new list
        return newList;
    }

}
