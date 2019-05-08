package com.rumaruka.simplegrinder.init;
import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.blocks.BlockCoalGrinder;
import com.rumaruka.simplegrinder.common.blocks.BlockMachineCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.registries.IForgeRegistry;
public class SGItems {

    public static Item dust_iron;
    public static Item dust_gold;
    public static Item flour;
    public static Item i_fuel;
    public static Item wood_chips;
    public static Item machine_core;
    public static ItemFood mash_carrot;
    public static ItemFood mash_potato;
    public static ItemFood omlete;

    public static void register(IForgeRegistry<Item> registry) {
        registry.register(new ItemBlock(SGBlocks.blockMachineCore, new Item.Properties().group(SimpleGrinder.simple_grinder_itemgroup)).setRegistryName(BlockMachineCore.MACHINE_CORE));
        registry.register(new ItemBlock(SGBlocks.blockCoalGrinder, new Item.Properties().group(SimpleGrinder.simple_grinder_itemgroup)).setRegistryName(BlockCoalGrinder.COAL_GRINDER));
      //  registry.register(new ItemFancyIngot());
       // registry.register(new ItemWand());
    }
}
