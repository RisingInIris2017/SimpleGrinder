package com.rumaruka.simplegrinder.Init;

import com.rumaruka.simplegrinder.Client.gui.creativetabs.SimpleGrinderCreativeTabs;
import com.rumaruka.simplegrinder.Common.blocks.BlockCoalGrinder;
import com.rumaruka.simplegrinder.Common.blocks.BlockMachineCore;
import com.rumaruka.simplegrinder.Common.items.ItemCoalGrinder;
import com.rumaruka.simplegrinder.Common.items.ItemMachineCore;
import com.rumaruka.simplegrinder.Reference.Reference;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.google.common.collect.ObjectArrays;
import java.lang.reflect.Constructor;
import org.apache.logging.log4j.Level;
import com.google.common.base.Strings;
public class BlocksCore {
	
	//Main 
	public static Block coal_grinder;
	public static Block lit_grinder;
	//For crafting
	public static Block machine_core;
	
	
	public static void init(){
		
		coal_grinder = new BlockCoalGrinder(false).setUnlocalizedName("coal_grinder").setCreativeTab(SimpleGrinderCreativeTabs.TABS_SIMPLE_GRINDER);
		lit_grinder = new BlockCoalGrinder(true).setUnlocalizedName("lit_grinder").setLightLevel(3.5F);
		machine_core = new BlockMachineCore().setUnlocalizedName("machine_core").setCreativeTab(SimpleGrinderCreativeTabs.TABS_SIMPLE_GRINDER);
	}
	public static void InGameRegister(){
		BlocksCore.registerBlock(coal_grinder, ItemCoalGrinder.class ,coal_grinder.getUnlocalizedName().substring(5));
		BlocksCore.registerBlock(lit_grinder,lit_grinder.getUnlocalizedName().substring(5));
		BlocksCore.registerBlock(machine_core, ItemMachineCore.class,machine_core.getUnlocalizedName().substring(5));
	}
	 @Deprecated
	    public static Block registerBlock(Block block)
	    {
		 GameRegistry.register(block);
		 GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	        return block;
	    }
	@Deprecated
    public static Block registerBlock(Block block, String name)
    {
        if (block.getRegistryName() == null && Strings.isNullOrEmpty(name))
            throw new IllegalArgumentException("Attempted to register a Block with no name: " + block);
        if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name))
            throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
        return registerBlock(block.getRegistryName() != null ? block : block.setRegistryName(name));
    }
	  @Deprecated
	    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, Object... itemCtorArgs)
	    {
	        if (Strings.isNullOrEmpty(name))
	        {
	            throw new IllegalArgumentException("Attempted to register a block with no name: " + block);
	        }
	        if (Loader.instance().isInState(LoaderState.CONSTRUCTING))
	        {
	            FMLLog.warning("The mod %s is attempting to register a block whilst it it being constructed. This is bad modding practice - please use a proper mod lifecycle event.", Loader.instance().activeModContainer());
	        }
	        try
	        {
	            assert block != null : "registerBlock: block cannot be null";
	            if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name))
	                throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
	            ItemBlock i = null;
	            if (itemclass != null)
	            {
	                Class<?>[] ctorArgClasses = new Class<?>[itemCtorArgs.length + 1];
	                ctorArgClasses[0] = Block.class;
	                for (int idx = 1; idx < ctorArgClasses.length; idx++)
	                {
	                    ctorArgClasses[idx] = itemCtorArgs[idx - 1].getClass();
	                }
	                Constructor<? extends ItemBlock> itemCtor = itemclass.getConstructor(ctorArgClasses);
	                i = itemCtor.newInstance(ObjectArrays.concat(block, itemCtorArgs));
	            }
	            // block registration has to happen first
	           GameRegistry.register(block.getRegistryName() == null ? block.setRegistryName(name) : block);
	            if (i != null)
	                GameRegistry.register(i.setRegistryName(name));
	            return block;
	        } catch (Exception e)
	        {
	            FMLLog.log(Level.ERROR, e, "Caught an exception during block registration");
	            throw new LoaderException(e);
	        }
	    }
	public static void Render(){
		registerRender(machine_core);
		registerRender(coal_grinder);
		registerRender(lit_grinder);
		
	}
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
    
}

	
	