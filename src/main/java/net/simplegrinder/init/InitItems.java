package net.simplegrinder.init;

import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.simplegrinder.SimpleGrinderMod;
import net.simplegrinder.common.items.*;

public class InitItems {
    public static Item dust_iron;
    public static Item dust_gold;
    public static Item flour;

    public static FoodItem mash_carrot;
    public static FoodItem mash_potato;
    public static FoodItem omlete;

    //Fuel
    public static Item i_fuel;
    public static Item wood_chips;

public  static void init(){

    dust_gold = new ItemDustGold();
    dust_iron = new ItemDustIron();
    wood_chips=new ItemWoodChips();
    flour=new ItemFlour();
    mash_carrot=new ItemMashCarrot();
    mash_potato=new ItemMashPotato();
    omlete=new ItemOmlete();
    i_fuel=new ItemIFuel();
}

public static void inGameRegistry(){


    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "dust_gold"), dust_gold);
    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "wood_chips"), wood_chips);
    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "dust_iron"), dust_iron);
    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "flour"), flour);
    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "mash_carrot"), mash_carrot);
    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "mash_potato"), mash_potato);
    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "omlete"), omlete);
    Registry.register(Registry.ITEM, new Identifier(SimpleGrinderMod.MODID, "i_fuel"), i_fuel);

}




}
