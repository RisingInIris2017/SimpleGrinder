package com.rumaruka.simplegrinder.client.gui;

import com.rumaruka.simplegrinder.common.tile.TileEntityCoalGrinder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.function.Function;
import java.util.function.Supplier;

public class SGGuiFactory implements Supplier<Function<FMLPlayMessages.OpenContainer, GuiScreen>> {
    @Override
    public Function<FMLPlayMessages.OpenContainer, GuiScreen> get() {
        return openContainer -> {
            ResourceLocation location = openContainer.getId();
            EntityPlayerSP singlPlayer = Minecraft.getInstance().player;
            BlockPos pos = openContainer.getAdditionalData().readBlockPos();
            TileEntity tileEntity = singlPlayer.world.getTileEntity(pos);

            if(tileEntity instanceof TileEntityCoalGrinder){
                return new GuiCoalGrinder(singlPlayer.inventory, (TileEntityCoalGrinder) tileEntity);
            }
            return null;
        };
    }
}
