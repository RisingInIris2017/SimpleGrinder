package com.rumaruka.simplegrinder.Init;

import com.rumaruka.simplegrinder.Core.GuiCore;
import com.rumaruka.simplegrinder.Core.simplygrinderCore;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class EventRegister {
	
	
	public static void RegisetHandlers(){
		NetworkRegistry.INSTANCE.registerGuiHandler(simplygrinderCore.instance, new GuiCore());
		
		
	}
}
