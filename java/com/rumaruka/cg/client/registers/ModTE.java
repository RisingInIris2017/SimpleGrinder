package com.rumaruka.cg.client.registers;

import com.rumaruka.cg.common.tileentity.TileEntityCoalGrinder;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTE {

	
	
public static void reg(){
	GameRegistry.registerTileEntity(TileEntityCoalGrinder.class, "TileEntityCoalGrinder");
		}
}
