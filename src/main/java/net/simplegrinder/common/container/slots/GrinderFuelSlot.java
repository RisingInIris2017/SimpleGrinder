package net.simplegrinder.common.container.slots;

import net.minecraft.container.FurnaceContainer;
import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.simplegrinder.common.container.ContainerCoalGrinder;

public class GrinderFuelSlot extends Slot {
    final ContainerCoalGrinder field_17083;

    public GrinderFuelSlot(ContainerCoalGrinder var1, Inventory var2, int var3, int var4, int var5) {
        super(var2, var3, var4, var5);
        this.field_17083 = var1;
    }

    public boolean canInsert(ItemStack var1) {
        return this.field_17083.method_16945(var1) || isBucket(var1);
    }

    public int getMaxStackAmount(ItemStack var1) {
        return isBucket(var1) ? 1 : super.getMaxStackAmount(var1);
    }

    public static boolean isBucket(ItemStack var0) {
        return var0.getItem() == Items.BUCKET;
    }
}
