package com.rumaruka.cg.common.handler;

import com.rumaruka.cg.client.gui.GuiCoalGrinder;
import com.rumaruka.cg.common.container.ContainerCoalGrinder;
import com.rumaruka.cg.common.tileentity.TileEntityCoalGrinder;
import com.rumaruka.cg.reference.GUId;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		 if (ID == GUId.CoalGrinder.ordinal())
	        {
	            TileEntityCoalGrinder teCG = (TileEntityCoalGrinder) world.getTileEntity(x, y, z);
	            return new ContainerCoalGrinder(player.inventory, teCG);
	        }
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUId.CoalGrinder.ordinal())
        {
            TileEntityCoalGrinder teCG = (TileEntityCoalGrinder) world.getTileEntity(x, y, z);
            return new GuiCoalGrinder(player.inventory, teCG);
        }
		return null;
	}

}
