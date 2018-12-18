package net.simplegrinder;

import net.fabricmc.api.ModInitializer;
import net.simplegrinder.init.InitBlocks;
import net.simplegrinder.init.InitItems;
import net.simplegrinder.init.InitTileEntity;

public class SimpleGrinderMod implements ModInitializer {
	public static final String MODID ="simplegrinder" ;

	@Override
	public void onInitialize() {
		InitBlocks.init();
		InitBlocks.inGameRegistry();

		InitTileEntity.load();

		InitItems.init();
		InitItems.inGameRegistry();
	}
}
