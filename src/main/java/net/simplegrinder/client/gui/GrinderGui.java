package net.simplegrinder.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.container.FurnaceGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Identifier;
import net.simplegrinder.client.gui.book.GrinderBookGui;
import net.simplegrinder.common.container.GrinderContainer;
@Environment(EnvType.CLIENT)
public class GrinderGui extends GuiAbstract {
    private static final Identifier grinderGui = new Identifier("simplegrinder:textures/gui/container/grindGui.png");

    public GrinderGui(PlayerInventory var1, Inventory var2) {
        super(new GrinderContainer(var1, var2), new GrinderBookGui(), var1, var2);
    }

    protected Identifier method_17045() {
        return grinderGui;
    }

}
