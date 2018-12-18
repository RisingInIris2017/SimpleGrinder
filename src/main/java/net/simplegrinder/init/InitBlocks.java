package net.simplegrinder.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.simplegrinder.SimpleGrinderMod;
import net.simplegrinder.common.blocks.BlockMachineCore;
import net.simplegrinder.common.blocks.GrinderBlock;

public class InitBlocks {

    public static Block coal_grinder;
   // public static Block lit_grinder;
    //For crafting
    public static Block machine_core;



    public static void init(){

        machine_core=new BlockMachineCore();
        coal_grinder = new GrinderBlock();
    }
    public static void inGameRegistry(){


        Registry.register(Registry.BLOCK,new Identifier(SimpleGrinderMod.MODID,"machine_core"),machine_core);
        Registry.register(Registry.ITEM,new Identifier(SimpleGrinderMod.MODID,"machine_core"),new BlockItem(machine_core,new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK,new Identifier(SimpleGrinderMod.MODID,"coal_grinder"),coal_grinder);
        Registry.register(Registry.ITEM,new Identifier(SimpleGrinderMod.MODID,"coal_grinder"),new BlockItem(coal_grinder,new Item.Settings().itemGroup(ItemGroup.DECORATIONS)));

    }







}
