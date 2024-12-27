package com.aidant.pizzadelight.item;

import com.aidant.pizzadelight.PizzaDelight;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PizzaDelight.MOD_ID);

    // --- ITEMS ---

    public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin",
            () -> new Item(new Item.Properties().durability(120)));

    // -- Cheese --

    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEESE_SLICE = ITEMS.register("cheese_slice",
            () -> new ConsumableItem(
                    new Item.Properties().food(ModFoods.CHEESE_SLICE),
                    false,
                    false
            ));

    public static final RegistryObject<Item> CHEESE_BREAD = ITEMS.register("cheese_bread",
            () -> new ConsumableItem(
                    new Item.Properties().food(ModFoods.CHEESE_BREAD),
                    false,
                    false
            ));

    // -- Hotdog --
    public static final RegistryObject<Item> RAW_SAUSAGE = ITEMS.register("raw_sausage",
            () -> new ConsumableItem(
                    new Item.Properties().food(ModFoods.RAW_SAUSAGE),
                    false,
                    false
            ));

    public static final RegistryObject<Item> SAUSAGE = ITEMS.register("cooked_sausage",
            () -> new ConsumableItem(
                    new Item.Properties().food(ModFoods.SAUSAGE),
                    false,
                    false
            ));

    public static final RegistryObject<Item> HOTDOG = ITEMS.register("hotdog",
            () -> new ConsumableItem(
                    new Item.Properties().food(ModFoods.HOTDOG),
                    true,
                    false
            ));

    // -- Pizza --

    public static final RegistryObject<Item> FLAT_DOUGH = ITEMS.register("flat_dough",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<SequencedAssemblyItem> INCOMPLETE_PIZZA = ITEMS.register("incomplete_pizza",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    // public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza",
    //         () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIZZA_SLICE = ITEMS.register("pizza_slice",
            () -> new ConsumableItem(
                    new Item.Properties().food(ModFoods.PIZZA_SLICE),
                    true,
                    false
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
