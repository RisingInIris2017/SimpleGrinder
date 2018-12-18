package net.simplegrinder.common.items;

import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;


public class ItemMashCarrot extends FoodItem {
    public ItemMashCarrot( ) {
        super(3,2.3f,false,new Settings().itemGroup(ItemGroup.MATERIALS));


    }

    @Override
    public String getTranslationKey() {
        return "item.simplegrinder.dust_gold";
    }
}
