package com.rumaruka.simplegrinder.Init;
import com.rumaruka.simplegrinder.Crafting.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
public class CraftingCore {


    public static void loadCraft() {

        ItemStack outPut;

        outPut = new ItemStack(BlocksCore.machine_core);
        RecipeHelper.addShapedRecipe(outPut, new Object[]{
                "SSS",
                "RPR",
                "SSS", 'S', new ItemStack(Blocks.STONE,1,0), 'R', Items.REDSTONE, 'P', Blocks.PISTON
        });
        ItemStack outPUT;
        outPUT = new ItemStack(BlocksCore.coal_grinder);
        RecipeHelper.addShapedRecipe(outPUT, new Object[]{
                "SIS",
                "SMS",
                "IPI", 'S', new ItemStack(Blocks.STONE,1,0), 'I', Items.IRON_INGOT, 'P', Blocks.PISTON, 'M', BlocksCore.machine_core
        });

        ItemStack output;
        output = new ItemStack(ItemsCore.i_fuel);
        RecipeHelper.addShapedRecipe(output, new Object[]{
                "EIE",
                "CNC",
                "EIE", 'I', new ItemStack(Items.DIAMOND), 'N', Items.NETHER_STAR   ,  'C', new ItemStack(Blocks.COAL_BLOCK), 'E', ItemsCore.wood_chips
        });


    }

}
