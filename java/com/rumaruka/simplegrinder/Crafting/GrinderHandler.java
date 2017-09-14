package com.rumaruka.simplegrinder.Crafting;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GrinderHandler {

    private static final GrinderHandler base = new GrinderHandler();
    public Map<ItemStack, ItemStack> crushingList = Maps.newHashMap();
    private Map<ItemStack, Float> experienceList = Maps.newHashMap();

    public static GrinderHandler instance() {
        return base;
    }

    private GrinderHandler() {

    }

    public ItemStack getResult(ItemStack stack) {
        for (Entry<ItemStack, ItemStack> entry : crushingList.entrySet()) {
            ItemStack in = entry.getKey().copy();
            if (in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
                return entry.getValue().copy();
        }
        return ItemStack.EMPTY;
    }

    public float getExperience(ItemStack stack) {
        for (Entry<ItemStack, Float> entry : experienceList.entrySet()) {
            ItemStack in = entry.getKey().copy();
            if (in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
                return entry.getValue();
        }
        return 0f;
    }
    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.crushingList;
    }

    public void addItemStack(ItemStack in, ItemStack out, float exp) {
        if (in.isEmpty() || out.isEmpty())
            return;
        for (ItemStack stack : crushingList.keySet())
            if (in.isItemEqual(stack))
                return;
        this.crushingList.put(in.copy(), out);
        this.experienceList.put(out.copy(), exp);
    }
}
