package com.aidant.pizzadelight.block;

import com.aidant.pizzadelight.PizzaDelight;
import com.aidant.pizzadelight.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PizzaDelight.MOD_ID);

    public static final RegistryObject<Block> CHEESE_BLOCK = registerBlock("cheese_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BANJO).strength(1.5F).sound(SoundType.WOOD).pushReaction(PushReaction.NORMAL)));

    public static final RegistryObject<Block> CHEESE_BRICKS = registerBlock("cheese_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.CHEESE_BLOCK.get())));

    public static final RegistryObject<Block> POLISHED_CHEESE = registerBlock("polished_cheese",
            () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.CHEESE_BLOCK.get())));

    // -- Pizza --

    public static final RegistryObject<Block> PIZZA = registerBlock("pizza",
            () -> new PizzaBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).mapColor(MapColor.COLOR_ORANGE).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void  register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
