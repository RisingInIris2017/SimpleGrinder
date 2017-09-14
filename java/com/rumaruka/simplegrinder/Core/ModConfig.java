package com.rumaruka.simplegrinder.Core;
import java.io.File;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


import com.rumaruka.simplegrinder.Init.GrinderRecipes;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ModConfig {

    public static Configuration config;

    public static List<GrinderRecipes> recipes;
    public static String[] blacklistDusts;
    public static boolean dusts;

    public static double  effiFuelMultiplier;

    @SuppressWarnings("serial")
    public static void refreshConfig(File file) {
        config = new Configuration(file);
        config.load();
        Gson gson = new Gson();
        Property prop = config.get("recipe", "grinder_recipes", GrinderRecipes.getDefaultRecipes().stream().map(r -> gson.toJson(r)).toArray(String[]::new));
        prop.setLanguageKey("grinder_recipes");
        prop.setComment("Grinder Recipes" + //
                Configuration.NEW_LINE + "item: modID:itemNname OR oreDictName" + //
                Configuration.NEW_LINE + "amount: #number" + //
                Configuration.NEW_LINE + "metadata: /number");
        recipes = Arrays.stream(prop.getStringList()).map(s -> (GrinderRecipes) gson.fromJson(s, new TypeToken<GrinderRecipes>() {
        }.getType())).collect(Collectors.toList());
        blacklistDusts = config.getStringList("blacklistDusts", Configuration.CATEGORY_GENERAL, new String[] { "dustCoal" }, "Blacklist for dusts which should not be craftable in pulvus.");
        dusts = config.getBoolean("dusts", "recipe", true, "Enable built in dusts");

         effiFuelMultiplier = config.getFloat("effiFuelMultiplier", "multiplier", .3f, .05f, 5f, "Multiplier of Fuel Consumption of Efficiency Upgrade");
        if (config.hasChanged()) {
            config.save();
        }

    }

}