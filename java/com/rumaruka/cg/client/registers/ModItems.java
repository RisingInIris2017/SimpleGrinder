package com.rumaruka.cg.client.registers;

import com.rumaruka.cg.common.items.carrotMash;
import com.rumaruka.cg.common.items.dustGold;
import com.rumaruka.cg.common.items.dustIron;
import com.rumaruka.cg.common.items.dustRealmit;
import com.rumaruka.cg.common.items.flour;
import com.rumaruka.cg.common.items.obsidianDust;
import com.rumaruka.cg.common.items.potMash;
import com.rumaruka.cg.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ModItems {

	public static Item dustIron;
	public static Item dustGold;
	public static Item obsidianDust;
	public static Item Flour;
	
	public static ItemFood potMash;
	public static ItemFood carrotMash;
		public static void reg() {
		dustIron = new dustIron();
		dustGold = new dustGold();
		obsidianDust = new obsidianDust();
		Flour = new flour();
		potMash = (ItemFood) new potMash(3, 2.5F, false).setUnlocalizedName("potMash").setTextureName(Reference.MOD_ID+":"+"mashP");
		carrotMash = (ItemFood) new carrotMash(4, 2.9F, false).setUnlocalizedName("carrotMash").setTextureName(Reference.MOD_ID+":"+"mashC");
		GameRegistry.registerItem(dustIron, "dustIron");
		GameRegistry.registerItem(dustGold, "dustGold");
		GameRegistry.registerItem(Flour, "Flour");
		GameRegistry.registerItem(potMash, "potMash");
		GameRegistry.registerItem(carrotMash, "carrotMash");
		GameRegistry.registerItem(obsidianDust, "obsidianDust");
		
		
		
		
		

	}

}
