package com.rumaruka.cg.client.registers;

import com.rumaruka.cg.client.gui.creativetabs.CreativeTabsSG;
import com.rumaruka.cg.common.blocks.CoalGrinder;
import com.rumaruka.cg.common.blocks.CoalGrinderII;
import com.rumaruka.cg.common.blocks.CoalGrinderIII;
import com.rumaruka.cg.common.blocks.FortifiedMashineCausing;
import com.rumaruka.cg.common.blocks.MashineCausing;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {
	
	public static Block MashineCausing;
	public static Block FortifiedMashineCausing;
	public static Block CoalGrinder;
	public static Block CoalGrinderII;
	public static Block CoalGrinderIII;
	public static Block CoalGrinderIV;
	public static Block lit_grind ;
	public static Block lit_grindII;
	public static Block lit_grindIII;
	public static Block lit_grindIV;
	
	
	public static void reg(){
		
		lit_grind = new CoalGrinder(true).setHardness(3.5F).setLightLevel(0.875F);
		CoalGrinder = new CoalGrinder(false).setHardness(3.5F).setCreativeTab(CreativeTabsSG.SG_TAB);
		
		lit_grindII = new CoalGrinderII(true).setHardness(3.5F).setLightLevel(0.875F);;
		CoalGrinderII = new CoalGrinderII(false).setHardness(3.5F).setCreativeTab(CreativeTabsSG.SG_TAB);
		
		lit_grindIII = new CoalGrinderIII(true);
		CoalGrinderIII = new CoalGrinderIII(false).setHardness(3.5F).setCreativeTab(CreativeTabsSG.SG_TAB);
		
		/*lit_grindIV = new CoalGrinderIV(true);
		CoalGrinderIV = new CoalGrinderIV(false).setHardness(3.5F).setCreativeTab(CreativeTabsSG.SG_TAB);
		*/
		MashineCausing = new MashineCausing();
		FortifiedMashineCausing = new FortifiedMashineCausing();
		
		GameRegistry.registerBlock(MashineCausing, "MashineCausing");
		GameRegistry.registerBlock(CoalGrinder, "CoalGrinder");
		GameRegistry.registerBlock(lit_grind, "lit_grind");
		GameRegistry.registerBlock(lit_grindII, "lit_grindII");
		GameRegistry.registerBlock(CoalGrinderII, "CoalGrinderII");
		GameRegistry.registerBlock(lit_grindIII, "lit_grindIII");
		GameRegistry.registerBlock(CoalGrinderIII, "CoalGrinderIII");
		GameRegistry.registerBlock(FortifiedMashineCausing, "FortifiedMashineCausing");
		
	}

}
