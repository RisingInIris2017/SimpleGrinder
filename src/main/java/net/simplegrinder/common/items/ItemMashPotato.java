package net.simplegrinder.common.items;

import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemGroup;


public class ItemMashPotato extends FoodItem {
    public ItemMashPotato( ) {
        super(3,2.3f,false,new Settings().itemGroup(ItemGroup.MATERIALS));


    }

    @Override
    public String getTranslationKey() {
        return "item.simplegrinder.dust_gold";
    }
}
