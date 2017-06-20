package com.rumaruka.simplegrinder.Init;

import com.rumaruka.simplegrinder.Helpers.SGCraftingHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Rumaruka on 16.06.2017.
 */
public class CraftingCore {


    public static void loadCraft() {

        ItemStack outPut;

        outPut = new ItemStack(BlocksCore.machine_core);
        SGCraftingHelper.addShapedRecipe(outPut, new Object[]{
                "SSS",
                "RPR",
                "SSS", 'S', new ItemStack(Blocks.STONE,1,0), 'R', Items.REDSTONE, 'P', Blocks.PISTON
        });
        ItemStack outPUT;
        outPUT = new ItemStack(BlocksCore.coal_grinder);
        SGCraftingHelper.addShapedRecipe(outPUT, new Object[]{
                "SIS",
                "SMS",
                "IPI", 'S', new ItemStack(Blocks.STONE,1,0), 'I', Items.IRON_INGOT, 'P', Blocks.PISTON, 'M', BlocksCore.machine_core
        });

        ItemStack output;
        output = new ItemStack(ItemsCore.i_fuel);
        SGCraftingHelper.addShapedRecipe(output, new Object[]{
                "EIE",
                "GCG",
                "EIE", 'I', new ItemStack(Items.DIAMOND), 'G', Items.GOLD_INGOT,  'C', new ItemStack(Blocks.COAL_BLOCK), 'E', ItemsCore.wood_chips
        });


    }

}


