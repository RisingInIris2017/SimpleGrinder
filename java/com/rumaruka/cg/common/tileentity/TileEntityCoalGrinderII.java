package com.rumaruka.cg.common.tileentity;

import com.rumaruka.cg.client.recipes.GrindRecipes;
import com.rumaruka.cg.common.blocks.CoalGrinderII;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCoalGrinderII extends TileEntity implements ISidedInventory{
	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1 };

	private ItemStack[] grindItemStacks = new ItemStack[3];
	//Grind Time
	public int grindBurnTime;
	public int currentItemBurnTime;
	public int grindCookTime;
	
	private String field_145958_o;
	private static final String __OBFID = "CL_00000357";

	public int getSizeInventory() {
		return this.grindItemStacks.length;
	}

	public ItemStack getStackInSlot(int p_70301_1_) {
		return this.grindItemStacks[p_70301_1_];
	}

	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		if (this.grindItemStacks[p_70298_1_] != null) {
			ItemStack itemstack;

			if (this.grindItemStacks[p_70298_1_].stackSize <= p_70298_2_) {
				itemstack = this.grindItemStacks[p_70298_1_];
				this.grindItemStacks[p_70298_1_] = null;
				return itemstack;
			} else {
				itemstack = this.grindItemStacks[p_70298_1_].splitStack(p_70298_2_);

				if (this.grindItemStacks[p_70298_1_].stackSize == 0) {
					this.grindItemStacks[p_70298_1_] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		if (this.grindItemStacks[p_70304_1_] != null) {
			ItemStack itemstack = this.grindItemStacks[p_70304_1_];
			this.grindItemStacks[p_70304_1_] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		this.grindItemStacks[p_70299_1_] = p_70299_2_;

		if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit()) {
			p_70299_2_.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory
	 */
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.field_145958_o : "container.Coal GrinderII";
	}

	/**
	 * Returns if the inventory is named
	 */
	public boolean hasCustomInventoryName() {
		return this.field_145958_o != null && this.field_145958_o.length() > 0;
	}

	public void func_145951_a(String p_145951_1_) {
		this.field_145958_o = p_145951_1_;
	}

	public void readFromNBT(NBTTagCompound p_145839_1_) {
		super.readFromNBT(p_145839_1_);
		NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
		this.grindItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.grindItemStacks.length) {
				this.grindItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.grindBurnTime = p_145839_1_.getShort("BurnTime");
		this.grindCookTime = p_145839_1_.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.grindItemStacks[1]);

		if (p_145839_1_.hasKey("CustomName", 8)) {
			this.field_145958_o = p_145839_1_.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound p_145841_1_) {
		super.writeToNBT(p_145841_1_);
		p_145841_1_.setShort("BurnTime", (short) this.grindBurnTime);
		p_145841_1_.setShort("CookTime", (short) this.grindCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.grindItemStacks.length; ++i) {
			if (this.grindItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.grindItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		p_145841_1_.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()) {
			p_145841_1_.setString("CustomName", this.field_145958_o);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int p_145953_1_) {
		return this.grindCookTime * p_145953_1_ / 150;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int p_145955_1_) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = 150;
		}

		return this.grindBurnTime * p_145955_1_ / this.currentItemBurnTime;
	}

	public boolean isBurning() {
		return this.grindBurnTime > 0;
	}

	public void updateEntity() {
		boolean flag = this.grindBurnTime > 0;
		boolean flag1 = false;

		if (this.grindBurnTime > 0) {
			--this.grindBurnTime;
		}

		if (!this.worldObj.isRemote) {
			if (this.grindBurnTime != 0 || this.grindItemStacks[1] != null && this.grindItemStacks[0] != null) {
				if (this.grindBurnTime == 0 && this.canGrind()) {
					this.currentItemBurnTime = this.grindBurnTime = getItemBurnTime(this.grindItemStacks[1]);

					if (this.grindBurnTime > 0) {
						flag1 = true;

						if (this.grindItemStacks[1] != null) {
							--this.grindItemStacks[1].stackSize;

							if (this.grindItemStacks[1].stackSize == 0) {
								this.grindItemStacks[1] = grindItemStacks[1].getItem()
										.getContainerItem(grindItemStacks[1]);
							}
						}
					}
				}

				if (this.isBurning() && this.canGrind()) {
					++this.grindCookTime;

					if (this.grindCookTime == 150) {
						this.grindCookTime = 0;
						this.grindItem();
						flag1 = true;
					}
				} else {
					this.grindCookTime = 0;
				}
				if (flag != this.grindBurnTime > 0) {
					flag1 = true;
					CoalGrinderII.updateGrindBlockState(this.grindBurnTime > 0, this.worldObj, this.xCoord, this.yCoord,
							this.zCoord);
				}
			}

		}

		if (flag1) {
			this.markDirty();
		}
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	private boolean canGrind() {
		if (this.grindItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = GrindRecipes.grinding().getSmeltingResult(this.grindItemStacks[0]);
			if (itemstack == null)
				return false;
			if (this.grindItemStacks[2] == null)
				return true;
			if (!this.grindItemStacks[2].isItemEqual(itemstack))
				return false;
			int result = grindItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.grindItemStacks[2].getMaxStackSize();
		}
	}

	public void grindItem() {
		if (this.canGrind()) {
			ItemStack itemstack = GrindRecipes.grinding().getSmeltingResult(this.grindItemStacks[0]);

			if (this.grindItemStacks[2] == null) {
				this.grindItemStacks[2] = itemstack.copy();
			} else if (this.grindItemStacks[2].getItem() == itemstack.getItem()) {
				this.grindItemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.grindItemStacks[0].stackSize;

			if (this.grindItemStacks[0].stackSize <= 0) {
				this.grindItemStacks[0] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack p_145952_0_) {
		if (p_145952_0_ == null) {
			return 0;
		} else {
			Item item = p_145952_0_.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.wooden_slab) {
					return 150;
				}

				if (block.getMaterial() == Material.wood) {
					return 300;
				}

				if (block == Blocks.coal_block) {
					return 16000;
				}
			}

			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
				return 150;
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
				return 150;
			if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
				return 150;
			if (item == Items.stick)
				return 50;
			if (item == Items.coal)
				return 1550;
			if (item == Items.lava_bucket)
				return 15000;
			if (item == Item.getItemFromBlock(Blocks.sapling))
				return 50;
			if (item == Items.blaze_rod)
				return 2200;
			return GameRegistry.getFuelValue(p_145952_0_);
		}
	}

	public static boolean isItemFuel(ItemStack p_145954_0_) {
		/**
		 * Returns the number of ticks that the supplied fuel item will keep the
		 * furnace burning, or 0 if the item isn't fuel
		 */
		return getItemBurnTime(p_145954_0_) > 0;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: p_70300_1_.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
						(double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return p_94041_1_ == 2 ? false : (p_94041_1_ == 1 ? isItemFuel(p_94041_2_) : true);
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed
	 * by automation on the given side of this block.
	 */
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return p_94128_1_ == 0 ? slotsBottom : (p_94128_1_ == 1 ? slotsTop : slotsSides);
	}

	/**
	 * Returns true if automation can insert the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return p_102008_3_ != 0 || p_102008_1_ != 1 || p_102008_2_.getItem() == Items.bucket;
	}
}
