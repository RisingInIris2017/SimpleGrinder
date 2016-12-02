package com.rumaruka.simplegrinder.Common.containers;

import javax.annotation.Nullable;

import com.rumaruka.simplegrinder.Common.tileentity.TileEntityCoalGrinder;
import com.rumaruka.simplegrinder.Init.GrinderRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCoaGrinder extends Container
{
	private final IInventory tileGrind;
    private int cookTime;
    private int totalCookTime;
    private int grindBurnTime;
    private int currentItemBurnTime;

    public ContainerCoaGrinder(InventoryPlayer playerInventory, IInventory grindInv)
    {
        this.tileGrind = grindInv;
        this.addSlotToContainer(new Slot(grindInv, 0, 56, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(grindInv, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, grindInv, 2, 116, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileGrind);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

            if (this.cookTime != this.tileGrind.getField(2))
            {
                icontainerlistener.sendProgressBarUpdate(this, 2, this.tileGrind.getField(2));
            }

            if (this.grindBurnTime != this.tileGrind.getField(0))
            {
                icontainerlistener.sendProgressBarUpdate(this, 0, this.tileGrind.getField(0));
            }

            if (this.currentItemBurnTime != this.tileGrind.getField(1))
            {
                icontainerlistener.sendProgressBarUpdate(this, 1, this.tileGrind.getField(1));
            }

            if (this.totalCookTime != this.tileGrind.getField(3))
            {
                icontainerlistener.sendProgressBarUpdate(this, 3, this.tileGrind.getField(3));
            }
        }

        this.cookTime = this.tileGrind.getField(2);
        this.grindBurnTime = this.tileGrind.getField(0);
        this.currentItemBurnTime = this.tileGrind.getField(1);
        this.totalCookTime = this.tileGrind.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileGrind.setField(id, data);
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileGrind.isUseableByPlayer(playerIn);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.field_190927_a;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!GrinderRecipes.instance().getSmeltingResult(itemstack1).func_190926_b())
                {
                    if (!this.mergeItemStack(itemstack1, 0+1, 1, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (TileEntityCoalGrinder.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return ItemStack.field_190927_a;
            }

            if (itemstack1.func_190926_b())
            {
                slot.putStack(ItemStack.field_190927_a);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.func_190916_E() == itemstack.func_190916_E())
            {
                return ItemStack.field_190927_a;
            }

            slot.func_190901_a(playerIn, itemstack1);
        }

        return itemstack;
    }
}