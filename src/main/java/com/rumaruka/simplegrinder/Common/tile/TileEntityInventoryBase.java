package com.rumaruka.simplegrinder.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

public abstract class TileEntityInventoryBase extends TileEntityAPI implements IInventory {
    protected NonNullList<ItemStack> itemStacks;
    public TileEntityInventoryBase(TileEntityType<?> tileEntityTypeIn,int size) {
        super(tileEntityTypeIn);
        itemStacks = NonNullList.withSize(size,ItemStack.EMPTY);
    }

    @Override
    public int getSizeInventory() {
        return itemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.itemStacks) {
            if (!itemstack.isEmpty())return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.itemStacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.itemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.itemStacks,index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.itemStacks.set(index,stack);
        if(stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this) return false;
        else return !(player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) > 64.0D);
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.itemStacks.clear();
    }

    @Override
    public void read(NBTTagCompound compound) {
        super.read(compound);
        this.itemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacks);
    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);

        return compound;
    }
}
