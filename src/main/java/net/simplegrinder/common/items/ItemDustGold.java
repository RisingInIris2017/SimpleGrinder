package net.simplegrinder.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;


public class ItemDustGold extends Item {
    public ItemDustGold( ) {
        super(new Settings().itemGroup(ItemGroup.MATERIALS));


    }

    @Override
    public String getTranslationKey() {
        return "item.simplegrinder.dust_gold";
    }
}
