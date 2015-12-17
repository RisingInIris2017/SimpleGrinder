package com.rumaruka.cg;

import com.rumaruka.cg.client.recipes.FurnaceSmeltingRecipe;
import com.rumaruka.cg.client.recipes.VanillaRecipes;
import com.rumaruka.cg.client.registers.ModBlocks;
import com.rumaruka.cg.client.registers.ModItems;
import com.rumaruka.cg.client.registers.ModTE;
import com.rumaruka.cg.common.handler.GuiHandler;
import com.rumaruka.cg.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME_MOD, version = Reference.VERSION_MOD)

public class cg {

	@Mod.Instance
	public static cg instance;

	@EventHandler
	public void init(FMLInitializationEvent e) {
		ModTE.reg();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		ModItems.reg();
		ModBlocks.reg();
		VanillaRecipes.loadingInitRecipes();
		FurnaceSmeltingRecipe.smeltingDust();

	}

	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {

	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {

	}

}
