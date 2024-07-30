package net.aidant.pizzadelight.item;

import net.aidant.pizzadelight.PizzaDelight;
import net.aidant.pizzadelight.item.advanced.RollingPinItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PizzaDelight.MOD_ID);

    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLAT_DOUGH = ITEMS.register("flat_dough",
            () -> new Item(new Item.Properties()));

    //FIXME Make the rolling pin a weapon!!!
    public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin",
            () -> new RollingPinItem(new Item.Properties()
                    .durability(131)
                    .stacksTo(1)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
