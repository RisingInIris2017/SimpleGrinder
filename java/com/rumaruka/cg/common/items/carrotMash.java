package com.rumaruka.cg.common.items;

import com.rumaruka.cg.client.gui.creativetabs.CreativeTabsSG;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class carrotMash extends ItemFood {
	/** Number of ticks to run while 'EnumAction'ing until result. */
	public final int itemUseDuration;
	/** The amount this food item heals the player. */
	private final int healAmount;
	private final float saturationModifier;
	/** Whether wolves like this food (true for raw and cooked porkchop). */
	private final boolean isWolfsFavoriteMeat;
	/**
	 * If this field is true, the food can be consumed even if the player don't
	 * need to eat.
	 */
	private boolean alwaysEdible;
	/**
	 * represents the potion effect that will occurr upon eating this food. Set
	 * by setPotionEffect
	 */
	private int potionId;
	/** set by setPotionEffect */
	private int potionDuration;
	/** set by setPotionEffect */
	private int potionAmplifier;
	/** probably of the set potion effect occurring */
	private float potionEffectProbability;
	private static final String __OBFID = "CL_00000036";

	public carrotMash(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
		super(p_i45339_1_, p_i45339_2_, p_i45339_3_);
		this.itemUseDuration = 32;
		this.healAmount = p_i45339_1_;
		this.isWolfsFavoriteMeat = p_i45339_3_;
		this.saturationModifier = p_i45339_2_;
		this.setCreativeTab(CreativeTabsSG.SG_TAB);
	}
	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
		--p_77654_1_.stackSize;
		p_77654_3_.getFoodStats().func_151686_a(this, p_77654_1_);
		p_77654_2_.playSoundAtEntity(p_77654_3_, "random.burp", 0.3F, p_77654_2_.rand.nextFloat() * 0.1F + 0.5F);
		this.onFoodEaten(p_77654_1_, p_77654_2_, p_77654_3_);
		return p_77654_1_;
	}

	protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
		if (!p_77849_2_.isRemote) {
			
			p_77849_3_.addPotionEffect(new PotionEffect(Potion.nightVision.id, 500, 0));

		}
	}

}
