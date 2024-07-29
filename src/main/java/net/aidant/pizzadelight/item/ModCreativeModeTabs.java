package net.aidant.pizzadelight.item;

import net.aidant.pizzadelight.PizzaDelight;
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

    public static final RegistryObject<CreativeModeTab> PIZZADELIGHT_TAB = CREATIVE_MODE_TABS.register("pizzadelight_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.PIZZA.get()))
                    .title(Component.translatable("creativetab.pizzadelight_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PIZZA.get());
                        pOutput.accept(ModItems.ROLLING_PIN.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
