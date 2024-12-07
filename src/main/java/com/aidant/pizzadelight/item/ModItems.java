package com.aidant.pizzadelight.item;

import com.aidant.pizzadelight.PizzaDelight;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PizzaDelight.MOD_ID);

    // --- ITEMS ---

    public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin",
            () -> new Item(new Item.Properties().durability(120).stacksTo(1)));

    // -- Cheese --

    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEESE_SLICE = ITEMS.register("cheese_slice",
            () -> new Item(new Item.Properties().food(ModFoods.CHEESE_SLICE)));

    public static final RegistryObject<Item> CHEESE_BREAD = ITEMS.register("cheese_bread",
            () -> new Item(new Item.Properties().food(ModFoods.CHEESE_BREAD)));

    // -- Pizza --

    public static final RegistryObject<Item> FLAT_DOUGH = ITEMS.register("flat_dough",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
