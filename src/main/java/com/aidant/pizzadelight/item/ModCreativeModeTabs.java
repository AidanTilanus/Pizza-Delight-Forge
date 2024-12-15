package com.aidant.pizzadelight.item;

import com.aidant.pizzadelight.PizzaDelight;
import com.aidant.pizzadelight.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PizzaDelight.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PIZZA_DELIGHT = CREATIVE_MODE_TABS.register("pizzadelight_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PIZZA.get()))
                    .title(Component.translatable("creativetab.pizzadelight_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ROLLING_PIN.get());

                        output.accept(ModItems.CHEESE.get());
                        output.accept(ModItems.CHEESE_SLICE.get());
                        output.accept(ModBlocks.CHEESE_BLOCK.get());
                        output.accept(ModBlocks.CHEESE_BRICKS.get());
                        output.accept(ModBlocks.POLISHED_CHEESE.get());
                        output.accept(ModItems.CHEESE_BREAD.get());

                        output.accept(ModItems.RAW_SAUSAGE.get());
                        output.accept(ModItems.SAUSAGE.get());
                        output.accept(ModItems.HOTDOG.get());

                        output.accept(ModItems.FLAT_DOUGH.get());
                        output.accept(ModItems.PIZZA.get());
                    })
                    .build());

    public static void register (IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
