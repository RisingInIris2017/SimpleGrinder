package com.rumaruka.simplegrinder.Core;

import com.rumaruka.simplegrinder.Common.OreDict.OreDictMain;


import com.rumaruka.simplegrinder.Init.*;
import com.rumaruka.simplegrinder.Proxy.CommonProxy;
import com.rumaruka.simplegrinder.Reference.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.MODVERSIONS)
public class simplygrinderCore {


    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    @Mod.Instance(Reference.MODID)
    public static simplygrinderCore instance;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent e) {

        // Blocks
        BlocksCore.init();
        BlocksCore.InGameRegister();

        // TileEntity
        TileEntityCore.InGameRegist();
        // Items
        ItemsCore.init();
        ItemsCore.InGameRegister();
        // EventHandler
        EventRegister.RegisetHandlers();
        // Crafting
        proxy.preInit(e);
        CraftingCore.loadCraft();
        // Smelting
        FurnaceSmelting.Smelting();
        // Load ConfigHandler
        ConfigHandler.startConfig(e);
        // Load Config
        Configuration config = new Configuration(e.getSuggestedConfigurationFile());
        config.load();
        //Load Fuel
        GameRegistry.registerFuelHandler(new ModFuelsHandler());
        //Load Ore Dict
        OreDictMain.registerOre();

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent e) {
        proxy.Renders();

    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent e) {
            proxy.postInit(e);
    }

}
