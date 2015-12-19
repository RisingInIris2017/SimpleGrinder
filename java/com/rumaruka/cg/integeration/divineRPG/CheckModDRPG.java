package com.rumaruka.cg.integeration.divineRPG;

import org.apache.logging.log4j.LogManager;

import cpw.mods.fml.common.Loader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class CheckModDRPG {
	
	public static boolean DRPGDetect;
	public static final Logger logger = LogManager.getLogger();
	
	public static void runPreInitChecks()
	{
		if(Loader.isModLoaded("divineprg"))
		{
			DRPGDetect = true;
			logger.info("Divineprg detected, enabling compathability tweaks...");
		}
	}
	
	public static void postInit()
	{
		if(DRPGDetect)
		{
			try
			{
				logger.info("This is still to be implemented...");
				//Class<?> CompactorAPI = Class.forName("Reika.RotaryCraft.API.CompactorAPI");
				//Method addRecipe = CompactorAPI.getMethod("addCompactorRecipe", ItemStack.class,ItemStack.class,int.class,int.class);
				
			}catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
	}

}
