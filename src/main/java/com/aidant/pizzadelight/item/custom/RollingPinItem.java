package com.aidant.pizzadelight.item.custom;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class RollingPinItem extends AxeItem {
    int burnTime = 200;

    public RollingPinItem(Properties pProperties) {
        super(Tiers.WOOD, 14f,-3.8f ,pProperties);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }
}

