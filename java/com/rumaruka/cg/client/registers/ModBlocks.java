package com.rumaruka.cg.client.registers;

import com.rumaruka.cg.common.blocks.CoalGrinder;
import com.rumaruka.cg.common.blocks.CoalGrinderII;
import com.rumaruka.cg.common.blocks.MashineCausing;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {
	
	public static Block MashineCausing;
	public static Block CoalGrinder;
	public static Block CoalGrinderII;
	public static Block lit_grind ;
	public static Block lit_grindII;
	
	public static void reg(){
		
		lit_grind = new CoalGrinder(true).setHardness(3.5F).setLightLevel(0.875F);
		CoalGrinder = new CoalGrinder(false).setHardness(3.5F).setCreativeTab(CreativeTabs.tabDecorations);
		lit_grindII = new CoalGrinderII(true).setHardness(3.5F).setLightLevel(0.875F);;
		CoalGrinderII = new CoalGrinderII(false).setHardness(3.5F).setCreativeTab(CreativeTabs.tabDecorations);
		MashineCausing = new MashineCausing();
		GameRegistry.registerBlock(MashineCausing, "MashineCausing");
		GameRegistry.registerBlock(CoalGrinder, "CoalGrinder");
		GameRegistry.registerBlock(lit_grind, "lit_grind");
		GameRegistry.registerBlock(lit_grindII, "lit_grindII");
		GameRegistry.registerBlock(CoalGrinderII, "CoalGrinderII");
		
	}

}
