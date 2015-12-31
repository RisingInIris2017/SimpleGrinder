package com.rumaruka.cg.common.blocks;

import java.util.Random;

import com.rumaruka.cg.cg;
import com.rumaruka.cg.client.registers.ModBlocks;
import com.rumaruka.cg.common.tileentity.TileEntityCoalGrinder;
import com.rumaruka.cg.reference.GUId;
import com.rumaruka.cg.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CoalGrinder extends BlockContainer {
	private final Random field_149933_a = new Random();
	private final boolean offon;
	private static boolean field_149934_M;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon front;

	
	
	public CoalGrinder(boolean b) {
		
		super(Material.rock);
		setBlockName("CoalGrinderI_tier");
		setStepSound(soundTypePiston);
		setHarvestLevel("pickaxe", 0);
		this.offon = b;
	}
	 @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	    {
	        return p_149691_1_ == 1 ? this.top : (p_149691_1_ == 0 ? this.top : (p_149691_1_ != p_149691_2_ ? this.blockIcon : this.front));
	    }

	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister IIcR)
	    {
	        this.blockIcon = IIcR.registerIcon(Reference.MOD_ID+":"+"grind_side");
	        this.front = IIcR.registerIcon(this.offon ? Reference.MOD_ID+":"+"grind_front_on" : Reference.MOD_ID+":"+"grind_front_off");
	        this.top = IIcR.registerIcon(Reference.MOD_ID+":"+"grind_top");
	    }
	@Override
	 public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	    {
	        return Item.getItemFromBlock(ModBlocks.CoalGrinder);
	    }
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileEntityCoalGrinder();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int faceHit, float par7,
			float par8, float par9) {
		if (player.isSneaking()) {
			return false;
		} else {
			if (!world.isRemote) {
				if (world.getTileEntity(x, y, z) instanceof TileEntityCoalGrinder) {
					player.openGui(cg.instance, GUId.CoalGrinder.ordinal(), world, x, y, z);
				}
			}

			return true;
		}
	}
	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_,
			EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
		}

		if (l == 1) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
		}

		if (l == 2) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
		}

		if (l == 3) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
		}

		if (p_149689_6_.hasDisplayName()) {
			((TileEntityCoalGrinder) p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_))
					.func_145951_a(p_149689_6_.getDisplayName());
		}
	}
	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_,
			int p_149749_6_) {
		if (!field_149934_M) {
			TileEntityCoalGrinder tileentityfurnace = (TileEntityCoalGrinder) p_149749_1_.getTileEntity(p_149749_2_,
					p_149749_3_, p_149749_4_);

			if (tileentityfurnace != null) {
				for (int i1 = 0; i1 < tileentityfurnace.getSizeInventory(); ++i1) {
					ItemStack itemstack = tileentityfurnace.getStackInSlot(i1);

					if (itemstack != null) {
						float f = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
						float f1 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
						float f2 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0) {
							int j1 = this.field_149933_a.nextInt(21) + 10;

							if (j1 > itemstack.stackSize) {
								j1 = itemstack.stackSize;
							}

							itemstack.stackSize -= j1;
							EntityItem entityitem = new EntityItem(p_149749_1_, (double) ((float) p_149749_2_ + f),
									(double) ((float) p_149749_3_ + f1), (double) ((float) p_149749_4_ + f2),
									new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								entityitem.getEntityItem()
										.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
							}

							float f3 = 0.05F;
							entityitem.motionX = (double) ((float) this.field_149933_a.nextGaussian() * f3);
							entityitem.motionY = (double) ((float) this.field_149933_a.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double) ((float) this.field_149933_a.nextGaussian() * f3);
							p_149749_1_.spawnEntityInWorld(entityitem);
						}
					}
				}

				p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
			}
		}

		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}
	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
		super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
		this.func_149930_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	}

	private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_) {
		if (!p_149930_1_.isRemote) {
			Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
			Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
			Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
			Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
			byte b0 = 3;

			if (block.func_149730_j() && !block1.func_149730_j()) {
				b0 = 3;
			}

			if (block1.func_149730_j() && !block.func_149730_j()) {
				b0 = 2;
			}

			if (block2.func_149730_j() && !block3.func_149730_j()) {
				b0 = 5;
			}

			if (block3.func_149730_j() && !block2.func_149730_j()) {
				b0 = 4;
			}

			p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
		}
	}
	 public static void updateGrindBlockState(boolean p_149931_0_, World p_149931_1_, int p_149931_2_, int p_149931_3_, int p_149931_4_)
	    {
	        int l = p_149931_1_.getBlockMetadata(p_149931_2_, p_149931_3_, p_149931_4_);
	        TileEntity tileentity = p_149931_1_.getTileEntity(p_149931_2_, p_149931_3_, p_149931_4_);
	        field_149934_M = true;

	        if (p_149931_0_)
	        {
	            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, ModBlocks.lit_grind);
	        }
	        else
	        {
	            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, ModBlocks.CoalGrinder);
	        }

	        field_149934_M = false;
	        p_149931_1_.setBlockMetadataWithNotify(p_149931_2_, p_149931_3_, p_149931_4_, l, 2);

	        if (tileentity != null)
	        {
	            tileentity.validate();
	            p_149931_1_.setTileEntity(p_149931_2_, p_149931_3_, p_149931_4_, tileentity);
	        }
	    }
	   public boolean hasComparatorInputOverride()
	    {
	        return true;
	    }
	   public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
	    {
	        return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
	    }
	   


}
