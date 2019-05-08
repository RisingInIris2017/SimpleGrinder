package com.rumaruka.simplegrinder.common.items;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemIronDust extends Item {

    public static final ResourceLocation DUST_IRON = new ResourceLocation(SimpleGrinder.MODID,"dust_iron");
    public ItemIronDust( ) {
        super(new Properties().group(SimpleGrinder.simple_grinder_itemgroup));

        setRegistryName(DUST_IRON);
    }
}
