package com.rumaruka.cg.reference;

import com.rumaruka.cg.common.handler.ResourceLocationHelper;

import net.minecraft.util.ResourceLocation;

public class Textures {

	public static final String RESOURCE_PREFIX = Reference.MOD_ID + ":";

	public static final class GUINei {
		protected static final String GUI_TEXTURE_LOCATION = RESOURCE_PREFIX + "textures/gui/container";
		public static final ResourceLocation grindGUI = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "grindGui.png");

	}

}
