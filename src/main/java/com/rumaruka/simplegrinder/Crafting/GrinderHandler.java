package com.rumaruka.simplegrinder.Crafting;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
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
            ItemStack in = entry.getKey();
            if (in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
                return entry.getValue();
        }
        return ItemStack.EMPTY;
    }
    public float getExperience(ItemStack stack) {
        for (Entry<ItemStack, Float> entry : experienceList.entrySet()) {
            ItemStack in = entry.getKey();
            if (in.isItemEqual(stack) || (in.getItem() == stack.getItem() && in.getItemDamage() == OreDictionary.WILDCARD_VALUE))
                return entry.getValue();
        }
        return 0f;
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
    public ItemStack getSmeltingList(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.crushingList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }
    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.crushingList;
    }
}
