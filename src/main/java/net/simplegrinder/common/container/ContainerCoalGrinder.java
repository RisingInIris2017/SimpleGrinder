package net.simplegrinder.common.container;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1662;
import net.minecraft.class_1737;
import net.minecraft.container.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.world.World;
import net.simplegrinder.common.container.slots.GrinderFuelSlot;

import java.util.Iterator;

public abstract class ContainerCoalGrinder extends CraftingContainer {
    private final Inventory inventory;
    protected final World field_7822;
    private int cookTime;
    private int totalCookTime;
    private int burnTime;
    private int fuelTime;

    ContainerCoalGrinder(PlayerInventory var1, Inventory var2) {
        this.inventory = var2;
        this.field_7822 = var1.player.world;
        this.addSlot(new Slot(var2, 0, 56, 17));
        this.addSlot(new GrinderFuelSlot(this, var2, 1, 56, 53));
        this.addSlot(new FurnaceOutputSlot(var1.player, var2, 2, 116, 35));

        int var3;
        for(var3 = 0; var3 < 3; ++var3) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for(var3 = 0; var3 < 9; ++var3) {
            this.addSlot(new Slot(var1, var3, 8 + var3 * 18, 142));
        }

    }

    public void addListener(ContainerListener var1) {
        super.addListener(var1);
        var1.onContainerInvRegistered(this, this.inventory);
    }

    public void method_7654(class_1662 var1) {
        if (this.inventory instanceof class_1737) {
            ((class_1737)this.inventory).method_7683(var1);
        }

    }

    public void clearCraftingSlots() {
        this.inventory.clearInv();
    }

    public boolean matches(Recipe var1) {
        return var1.matches(this.inventory, this.field_7822);
    }

    public int getCraftingResultSlotIndex() {
        return 2;
    }

    public int getCraftingWidth() {
        return 1;
    }

    public int getCrafitngHeight() {
        return 1;
    }

    @Environment(EnvType.CLIENT)
    public int method_7658() {
        return 3;
    }

    public void setProperty(int var1, int var2) {
        this.inventory.setInvProperty(var1, var2);
    }

    public boolean canUse(PlayerEntity var1) {
        return this.inventory.canPlayerUseInv(var1);
    }

    public void sendContentUpdates() {
        super.sendContentUpdates();
        Iterator var1 = this.listeners.iterator();

        while(var1.hasNext()) {
            ContainerListener var2 = (ContainerListener)var1.next();
            if (this.cookTime != this.inventory.getInvProperty(2)) {
                var2.onContainerPropertyUpdate(this, 2, this.inventory.getInvProperty(2));
            }

            if (this.burnTime != this.inventory.getInvProperty(0)) {
                var2.onContainerPropertyUpdate(this, 0, this.inventory.getInvProperty(0));
            }

            if (this.fuelTime != this.inventory.getInvProperty(1)) {
                var2.onContainerPropertyUpdate(this, 1, this.inventory.getInvProperty(1));
            }

            if (this.totalCookTime != this.inventory.getInvProperty(3)) {
                var2.onContainerPropertyUpdate(this, 3, this.inventory.getInvProperty(3));
            }
        }

        this.cookTime = this.inventory.getInvProperty(2);
        this.burnTime = this.inventory.getInvProperty(0);
        this.fuelTime = this.inventory.getInvProperty(1);
        this.totalCookTime = this.inventory.getInvProperty(3);
    }

    public ItemStack transferSlot(PlayerEntity var1, int var2) {
        ItemStack var3 = ItemStack.EMPTY;
        Slot var4 = (Slot)this.slotList.get(var2);
        if (var4 != null && var4.hasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (var2 == 2) {
                if (!this.insertItem(var5, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                var4.onStackChanged(var5, var3);
            } else if (var2 != 1 && var2 != 0) {
                if (this.method_7640(var5)) {
                    if (!this.insertItem(var5, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.method_16945(var5)) {
                    if (!this.insertItem(var5, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (var2 >= 3 && var2 < 30) {
                    if (!this.insertItem(var5, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (var2 >= 30 && var2 < 39 && !this.insertItem(var5, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(var5, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (var5.isEmpty()) {
                var4.setStack(ItemStack.EMPTY);
            } else {
                var4.markDirty();
            }

            if (var5.getAmount() == var3.getAmount()) {
                return ItemStack.EMPTY;
            }

            var4.onTakeItem(var1, var5);
        }

        return var3;
    }

    protected abstract boolean method_7640(ItemStack var1);

    public abstract boolean method_16945(ItemStack var1);
}
