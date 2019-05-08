package com.rumaruka.simplegrinder.common.tile;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;

public abstract class TileEntityAPI extends TileEntity implements INameable, IInteractionObject {

    protected ITextComponent customName;

    public TileEntityAPI(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }


    @Override
    public ITextComponent getName() {
        return this.customName!=null?this.customName : new TextComponentTranslation("container."+super.getType().getRegistryName().getPath());
    }

    @Override
    public boolean hasCustomName() {
        return this.customName!=null;
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return this.customName;
    }
    @Override
    public void read(NBTTagCompound compound) {
        super.read(compound);

        if (compound.contains("CustomName", 8)) {
            this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
        }

    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound) {
        super.write(compound);

        if (this.customName != null) {
            compound.setString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        return compound;
    }
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.setPos(pkt.getPos());
        this.read(pkt.getNbtCompound());
    }
    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.write(new NBTTagCompound()));
    }
}
