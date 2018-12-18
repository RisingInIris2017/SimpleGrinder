package net.simplegrinder.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;


public class ItemDustIron extends Item {
    public ItemDustIron( ) {
        super(new Settings().itemGroup(ItemGroup.MATERIALS));


    }

    @Override
    public String getTranslationKey() {
        return "item.simplegrinder.dust_gold";
    }
}
