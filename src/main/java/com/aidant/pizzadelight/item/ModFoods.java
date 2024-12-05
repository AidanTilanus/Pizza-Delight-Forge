package com.aidant.pizzadelight.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties CHEESE_SLICE = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.3F).build();

    public static final FoodProperties CHEESE_BREAD = new FoodProperties.Builder()
            .nutrition(7).saturationMod(0.7F).build();
}
