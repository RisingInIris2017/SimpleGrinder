package com.rumaruka.simplegrinder;

import com.rumaruka.simplegrinder.client.gui.SGGuiFactory;
import com.rumaruka.simplegrinder.init.SGBlocks;
import com.rumaruka.simplegrinder.init.SGItems;
import com.rumaruka.simplegrinder.init.recipes.SGRecipe;
import com.rumaruka.simplegrinder.proxy.ClientProxy;
import com.rumaruka.simplegrinder.proxy.IProxy;
import com.rumaruka.simplegrinder.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SimpleGrinder.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SimpleGrinder {
    public static SimpleGrinder instance;
    public static final String MODID = "simplegrinder";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static ItemGroup simple_grinder_itemgroup = new ItemGroup("simplegrinder") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SGBlocks.blockCoalGrinder);
        }
    };

    public SimpleGrinder(){
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,new SGGuiFactory());
        SGRecipe.Serializers.register();

    }
    private void setup(final FMLCommonSetupEvent event) {



        proxy.setup(event);
    }
    @SubscribeEvent
    public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
        SGBlocks.registerTiles(event.getRegistry());
    }
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        SGBlocks.register(event.getRegistry());
    }




    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        SGItems.register(event.getRegistry());
    }


}
