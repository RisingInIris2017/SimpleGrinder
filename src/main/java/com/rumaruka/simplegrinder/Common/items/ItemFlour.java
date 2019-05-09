package com.rumaruka.simplegrinder.common.items;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemFlour extends Item {

    public static final ResourceLocation FLOUR = new ResourceLocation(SimpleGrinder.MODID,"flour");
    public ItemFlour( ) {
        super(new Properties().group(SimpleGrinder.simple_grinder_itemgroup));

        setRegistryName(FLOUR);
    }
}
