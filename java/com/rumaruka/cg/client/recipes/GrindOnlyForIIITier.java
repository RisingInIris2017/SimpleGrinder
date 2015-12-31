package com.rumaruka.cg.client.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.rumaruka.cg.client.registers.ModItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GrindOnlyForIIITier {

	private static final GrindOnlyForIIITier grindBase = new GrindOnlyForIIITier();

	private Map grindList = new HashMap();
	private Map expaList = new HashMap();
	private static final String __OBFID = "CL_00000085";

	public static GrindOnlyForIIITier grinding() {
		return grindBase;
	}

	
	private GrindOnlyForIIITier() {
		this.grindMaterial(Blocks.cobblestone, new ItemStack(Blocks.sand,2), 3F);
		this.grindMaterial(Blocks.sandstone, new ItemStack(Blocks.sand,4), 2F);
		this.grindMaterial(Blocks.coal_ore, new ItemStack(Items.coal,16), 2F);
		this.grindMaterial(Blocks.iron_ore, new ItemStack(ModItems.dustIron, 2), 5F);
		this.grindMaterial(Blocks.gold_ore, new ItemStack(ModItems.dustGold, 2), 3F);
		this.grindMaterial(Blocks.diamond_ore, new ItemStack(Items.diamond, 4), 4F);
		this.grindMaterial(Blocks.emerald_ore, new ItemStack(Items.emerald, 4), 10F);
		this.grindMaterial(Blocks.quartz_ore, new ItemStack(Items.quartz, 15), 5F);
		this.grindMaterial(Blocks.gravel, new ItemStack(Items.flint, 4), 5F);
		this.grindMaterial(Blocks.redstone_ore, new ItemStack(Items.redstone, 20), 5F);
		this.grindMaterial(Blocks.wool, new ItemStack(Items.string, 4), 5F);
		this.grindMaterial(Blocks.lapis_ore, new ItemStack(Items.dye, 10, 4), 5F);
		this.grindMaterial(new ItemStack(Items.wheat), new ItemStack(ModItems.Flour,2), 4.8F);
		this.grindMaterial(new ItemStack(Items.bone), new ItemStack(Items.dye,10,15), 4.8F);
		this.grindMaterial(new ItemStack(Items.potato), new ItemStack(ModItems.potMash,2), 4.8F);
		this.grindMaterial(new ItemStack(Items.carrot), new ItemStack(ModItems.carrotMash,2), 4.8F);
		this.grindMaterial(new ItemStack(Blocks.obsidian), new ItemStack(ModItems.obsidianDust, 2), 4.8F);
		
		
	}

	private void grindMaterial(Block b, ItemStack is, float exp) {
		this.grindMaterial(Item.getItemFromBlock(b), is, exp);

	}

	private void grindMaterial(Item I, ItemStack is, float exp) {
		this.grindMaterial(new ItemStack(I), is, exp);

	}

	private void grindMaterial(ItemStack is, ItemStack is1, float exp) {
		this.grindList.put(is, is1);
		this.expaList.put(is1, Float.valueOf(exp));

	}

	public ItemStack getSmeltingResult(ItemStack p_151395_1_) {
		Iterator iterator = this.grindList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return null;
			}

			entry = (Entry) iterator.next();
		} while (!this.func_151397_a(p_151395_1_, (ItemStack) entry.getKey()));

		return (ItemStack) entry.getValue();
	}

	private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_) {
		return p_151397_2_.getItem() == p_151397_1_.getItem()
				&& (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
	}

	public Map getSmeltingList() {
		return this.grindList;
	}

	public float func_151398_b(ItemStack p_151398_1_) {
		float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
		if (ret != -1)
			return ret;

		Iterator iterator = this.expaList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return 0.0F;
			}

			entry = (Entry) iterator.next();
		} while (!this.func_151397_a(p_151398_1_, (ItemStack) entry.getKey()));

		return ((Float) entry.getValue()).floatValue();
	}

}

