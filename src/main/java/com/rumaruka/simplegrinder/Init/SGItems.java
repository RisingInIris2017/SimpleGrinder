package com.rumaruka.simplegrinder.init;
import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.blocks.BlockCoalGrinder;
import com.rumaruka.simplegrinder.common.blocks.BlockMachineCore;
import com.rumaruka.simplegrinder.common.items.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class SGItems {


    @ObjectHolder("simplegrinder:dust_iron")
    public static ItemIronDust dust_iron;

    @ObjectHolder("simplegrinder:dust_gold")
    public static ItemGoldDust dust_gold;

    @ObjectHolder("simplegrinder:flour")
    public static ItemFlour flour;

    @ObjectHolder("simplegrinder:mash_carrot")
    public static ItemMashCarrot mash_carrot;
    @ObjectHolder("simplegrinder:mash_potato")
    public static ItemMashPotato mash_potato;
    @ObjectHolder("simplegrinder:omlete")
    public static ItemOmlete omlete;

    public static void register(IForgeRegistry<Item> registry) {
        registry.register(new ItemBlock(SGBlocks.blockMachineCore, new Item.Properties().group(SimpleGrinder.simple_grinder_itemgroup)).setRegistryName(BlockMachineCore.MACHINE_CORE));
        registry.register(new ItemBlock(SGBlocks.blockCoalGrinder, new Item.Properties().group(SimpleGrinder.simple_grinder_itemgroup)).setRegistryName(BlockCoalGrinder.COAL_GRINDER));
        registry.register(new ItemIronDust());
        registry.register(new ItemGoldDust());
        registry.register(new ItemFlour());
        registry.register(new ItemMashCarrot(4,2.9F,false));
        registry.register(new ItemMashPotato(3,4.5F,false));
        registry.register(new ItemOmlete(2,2.6F,false));
    }
}
