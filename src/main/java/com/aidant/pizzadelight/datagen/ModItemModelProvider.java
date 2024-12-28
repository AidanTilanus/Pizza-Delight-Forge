package com.aidant.pizzadelight.datagen;

import com.aidant.pizzadelight.PizzaDelight;
import com.aidant.pizzadelight.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PizzaDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleWeapon(ModItems.ROLLING_PIN);

        simpleItem(ModItems.CHEESE);
        simpleItem(ModItems.CHEESE_SLICE);
        simpleItem(ModItems.CHEESE_BREAD);

        simpleItem(ModItems.RAW_SAUSAGE);
        simpleItem(ModItems.SAUSAGE);
        simpleItem(ModItems.HOTDOG);

        simpleItem(ModItems.FLAT_DOUGH);

        simpleItem(ModItems.INCOMPLETE_PIZZA);
        simpleItem(ModItems.PIZZA_SLICE);
    }

    private ItemModelBuilder simpleItem(@NotNull RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PizzaDelight.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleWeapon(@NotNull RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(PizzaDelight.MOD_ID, "item/" + item.getId().getPath()));
    }
}
