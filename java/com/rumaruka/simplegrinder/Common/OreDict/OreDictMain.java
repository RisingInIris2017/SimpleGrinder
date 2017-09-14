package com.rumaruka.simplegrinder.Common.OreDict;


import com.rumaruka.simplegrinder.Init.ItemsCore;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictMain {
	
	
	

	public static void registerOre(){

		//Dust
	 OreDictionary.registerOre("dustIron", new ItemStack(ItemsCore.dust_iron));
	 OreDictionary.registerOre("dustGold",new ItemStack(ItemsCore.dust_gold));
	 OreDictionary.registerOre("logChips", new ItemStack(ItemsCore.wood_chips));
	 OreDictionary.registerOre("flour", new ItemStack(ItemsCore.flour));
	 OreDictionary.registerOre("mashCarrot", new ItemStack(ItemsCore.mash_carrot));
	 OreDictionary.registerOre("mashPotato", new ItemStack(ItemsCore.mash_potato));
	 OreDictionary.registerOre("omlete",new ItemStack(ItemsCore.omlete));
	}
	
	


}