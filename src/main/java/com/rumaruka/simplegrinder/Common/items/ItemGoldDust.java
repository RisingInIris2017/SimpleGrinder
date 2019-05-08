package com.rumaruka.simplegrinder.common.items;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemGoldDust extends Item {

    public static final ResourceLocation DUST_GOLD = new ResourceLocation(SimpleGrinder.MODID,"dust_gold");
    public ItemGoldDust( ) {
        super(new Properties().group(SimpleGrinder.simple_grinder_itemgroup));

        setRegistryName(DUST_GOLD);
    }
}
