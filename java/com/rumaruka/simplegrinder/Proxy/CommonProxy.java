package com.rumaruka.simplegrinder.Proxy;



import com.rumaruka.simplegrinder.Core.ModConfig;
import com.rumaruka.simplegrinder.Init.GrinderRecipes;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Logger;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy {

	private static final Logger logger = FMLLog.getLogger();

	public void Renders(){
		
	}
	
	public void info(String s){
		logger.info(s);
	}


	public void error(String s){
		logger.error(s);
	}

	public void preInit(FMLPreInitializationEvent e) {
		ModConfig.refreshConfig(e.getSuggestedConfigurationFile());

	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {
		for (GrinderRecipes r : ModConfig.recipes)
			r.register();
		GrinderRecipes.registerDefaultOreRecipes();
	}
}
