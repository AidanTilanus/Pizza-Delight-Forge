package com.aidant.pizzadelight.datagen;

import com.aidant.pizzadelight.PizzaDelight;
import com.aidant.pizzadelight.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PizzaDelight.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.CHEESE_BLOCK);
        blockWithItem(ModBlocks.CHEESE_BRICKS);
        blockWithItem(ModBlocks.POLISHED_CHEESE);
    }

    private void blockWithItem(@NotNull RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
