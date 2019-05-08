package com.rumaruka.simplegrinder.common.tile;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.blocks.BlockCoalGrinder;
import com.rumaruka.simplegrinder.common.inventory.ContainerCoalGrinder;
import com.rumaruka.simplegrinder.init.SGBlocks;
import com.rumaruka.simplegrinder.init.recipes.GrinderRecipe;
import com.rumaruka.simplegrinder.init.recipes.SGRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TileEntityCoalGrinder extends TileEntityInventoryBase implements ISidedInventory, ITickable {


    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2,1};
    private static final int[] SLOTS_SIDES = new int[]{1};
    /** The number of ticks that the crusher will keep burning */
    private int burnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the grinder burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    public TileEntityCoalGrinder() {
        super(SGBlocks.TYPE_COAL_GRINDER,3 );
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if(side==EnumFacing.DOWN){
            return SLOTS_BOTTOM;
        }
     else {
         return side == EnumFacing.UP?SLOTS_TOP:SLOTS_SIDES;
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable EnumFacing direction) {
        return this.isItemValidForSlot(index,itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if(direction == EnumFacing.DOWN && index == 1){
            Item item = stack.getItem();
            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }
        return true;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.itemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.itemStacks.set(index,stack);
        if(stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }
        if(index == 0 && !flag){
            this.totalCookTime = this.getCookTime();
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        } else {
            ItemStack itemstack = this.itemStacks.get(1);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }

    @Override
    public int getField(int id) {
        switch(id) {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }
    @Override
    public void setField(int id, int value) {
        switch(id) {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }

    }
    @Override
    public int getFieldCount() {
        return 4;
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }
    @OnlyIn(Dist.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.getField(0) > 0;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerCoalGrinder(playerInventory,this);
    }

    @Override
    public String getGuiID() {
        return SimpleGrinder.MODID+":coal_grinder";
    }

    @Override
    public void tick() {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.itemStacks.get(1);
            if (this.isBurning() || !itemstack.isEmpty() && !this.itemStacks.get(0).isEmpty()) {
                IRecipe irecipe = this.world.getRecipeManager().getRecipe(this, this.world, SGRecipe.Types.CRUSHER);
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    this.burnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (itemstack.hasContainerItem()) {
                            this.itemStacks.set(1, itemstack.getContainerItem());
                        }
                        else
                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                Item item1 = item.getContainerItem();
                                this.itemStacks.set(1, item1 == null ? ItemStack.EMPTY : new ItemStack(item1));
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(irecipe)) {
                    ++this.cookTime;
                    if (this.cookTime >= this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime();
                        this.smeltItem(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(BlockCoalGrinder.LIT, this.isBurning()), 3);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }
    private static int getItemBurnTime(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            Item item = stack.getItem();
            int ret = stack.getBurnTime();
            return net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack, ret == -1 ? TileEntityFurnace.getBurnTimes().getOrDefault(item, 0) : ret);
        }
    }
    private int getCookTime() {
        GrinderRecipe grinderRecipe = this.world.getRecipeManager().getRecipe(this, this.world, SGRecipe.Types.CRUSHER);
        return grinderRecipe != null ? grinderRecipe.getCookingTime() : 200;
    }

    private void smeltItem(@Nullable IRecipe recipe) {
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemInput = this.itemStacks.get(0);
            ItemStack itemOutput = recipe.getRecipeOutput();
            ItemStack itemOutput1 = this.itemStacks.get(2);
            if (itemOutput1.isEmpty()) {
                this.itemStacks.set(2, itemOutput.copy());
            } else if (itemOutput1.getItem() == itemOutput.getItem()) {
                itemOutput1.grow(itemOutput.getCount());
            }

            if (itemInput.getItem() == Blocks.WET_SPONGE.asItem() && !this.itemStacks.get(1).isEmpty() && this.itemStacks.get(1).getItem() == Items.BUCKET) {
                this.itemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemInput.shrink(1);
        }
    }

    private boolean canSmelt(@Nullable IRecipe recipe) {
        if (!this.itemStacks.get(0).isEmpty() && recipe != null) {
            ItemStack itemstack = recipe.getRecipeOutput();
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.itemStacks.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() < itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public void read(NBTTagCompound compound) {
        super.read(compound);
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.totalCookTime = compound.getInt("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.itemStacks.get(1));
    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound) {
        super.write(compound);
        compound.setInt("BurnTime", this.burnTime);
        compound.setInt("CookTime", this.cookTime);
        compound.setInt("CookTimeTotal", this.totalCookTime);
        return compound;
    }
}
