package com.rumaruka.simplegrinder.Init;

import com.rumaruka.simplegrinder.Common.items.ItemDustGold;
import com.rumaruka.simplegrinder.Common.items.ItemDustIron;
import com.rumaruka.simplegrinder.Common.items.ItemFloar;
import com.rumaruka.simplegrinder.Common.items.ItemIFuel;
import com.rumaruka.simplegrinder.Common.items.ItemMashCarrot;
import com.rumaruka.simplegrinder.Common.items.ItemMashPotato;
import com.rumaruka.simplegrinder.Common.items.ItemWoodChips;
import com.rumaruka.simplegrinder.Reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ItemsCore {

	public static Item dust_iron;
	public static Item dust_gold;
	public static Item flour;
	public static ItemFood mash_carrot;
	public static ItemFood mash_potato;
	
	public static Item i_fuel;
	public static Item wood_chips;
	public static void init() {
		dust_iron = new ItemDustIron().setUnlocalizedName("dust_iron").setCreativeTab(CreativeTabs.tabMaterials);
		dust_gold = new ItemDustGold().setUnlocalizedName("dust_gold").setCreativeTab(CreativeTabs.tabMaterials);
		wood_chips = new ItemWoodChips().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("wood_chips");
		flour = new ItemFloar().setUnlocalizedName("flour").setCreativeTab(CreativeTabs.tabFood);
		mash_carrot = (ItemFood) new ItemMashCarrot(4, 2.9F, false).setUnlocalizedName("mash_carrot").setCreativeTab(CreativeTabs.tabFood);
		mash_potato = (ItemFood) new ItemMashPotato(3, 4.5F, false).setUnlocalizedName("mash_potato").setCreativeTab(CreativeTabs.tabFood);
		
		i_fuel = new ItemIFuel().setUnlocalizedName("i_fuel").setCreativeTab(CreativeTabs.tabMaterials);
		
		
	}
	
	public static void InGameRegister(){
		GameRegistry.registerItem(dust_iron, dust_iron.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dust_gold, dust_gold.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(flour, flour.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(mash_carrot, mash_carrot.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(mash_potato, mash_potato.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(i_fuel, i_fuel.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(wood_chips, wood_chips.getUnlocalizedName().substring(5));
		
		
	}
	
	
	
	
	public static void Render(){
		Renders(dust_iron);
		Renders(dust_gold);
		Renders(flour);
		Renders(mash_carrot);
		Renders(mash_potato);
		Renders(i_fuel);
		Renders(wood_chips);
	}
	public static void Renders(Item i){
		
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(
					Reference.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));
		
	}
}