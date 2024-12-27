package com.aidant.pizzadelight.block;

import com.aidant.pizzadelight.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PizzaBlock extends Block {

    private static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    private static final VoxelShape PIZZA_SHAPE = Shapes.box(0, 0, 0, 1, 0.25, 1);

    public PizzaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return PIZZA_SHAPE;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        // Ensure the block renders using the model
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide) {
            ItemStack heldItem = player.getItemInHand(hand);
            int bites = state.getValue(BITES);

            // Check if the held item is in the "forge:tools/knives" tag
            if (heldItem.is(ItemTags.create(new ResourceLocation("forge", "tools/knives")))) {
                // Drop two pizza slices
                ItemStack dropStack = new ItemStack(ModItems.PIZZA_SLICE.get(), 2);
                popResource(level, pos, dropStack);

                if (bites < 3) {
                    // Update the block's state
                    level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
                } else {
                    // Remove the block when fully eaten
                    level.removeBlock(pos, false);
                }

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        BlockPos belowPos = pos.below();

        // Ensure there is any block below (not air)
        return !level.isEmptyBlock(belowPos);
    }

    @Override
    public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        if (!level.isClientSide()) {
            if (!canSurvive(state, level, pos)) {
                level.destroyBlock(pos, true); // Break the block immediately if unsupported
            }
        }
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos neighborPos, boolean isMoving) {
        if (!level.isClientSide()) {
            if (!canSurvive(state, level, pos)) {
                level.destroyBlock(pos, true); // Break the block if the neighbor (support) is removed
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        // Add the BITES property to the block state
        builder.add(BITES);
    }
}