package com.aidant.pizzadelight.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects; // Import Farmer's Delight effect

public class ModFoods {

    public static final FoodProperties CHEESE_SLICE = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.3F).build();

    public static final FoodProperties CHEESE_BREAD = new FoodProperties.Builder()
            .nutrition(7).saturationMod(0.7F).build();

    public static final FoodProperties RAW_SAUSAGE = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.3F).meat().build();

    public static final FoodProperties SAUSAGE = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.6F).meat().build();

    public static final FoodProperties HOTDOG = new FoodProperties.Builder()
            .nutrition(12).saturationMod(1.0F)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 600, 0), 1.0F).build(); // #TODO add a tool tip.
}
