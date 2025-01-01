package com.aidant.pizzadelight.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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

import java.util.function.Supplier;

public class PizzaBlock extends Block {

    private static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    private static final VoxelShape PIZZA_SHAPE = Shapes.box(0, 0, 0, 1, 0.25, 1);

    private final Supplier<Item> sliceItem;

    public PizzaBlock(Properties properties, Supplier<Item> sliceItem) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
        this.sliceItem = sliceItem;


    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return PIZZA_SHAPE;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState state, Level level, BlockPos pos) {
        int bites = state.getValue(BITES);
        return (4 - bites) * 15 / 4;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide) {
            ItemStack heldItem = player.getItemInHand(hand);
            int bites = state.getValue(BITES);

            if (heldItem.is(ItemTags.create(new ResourceLocation("forge", "tools/knives")))) {
                ItemStack dropStack = new ItemStack(this.sliceItem.get(), 2); // Use Supplier to get the item
                popResource(level, pos, dropStack);

                // damage the knife
                heldItem.hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(hand); // Notify other players of the tool breaking
                });

                if (bites < 3) {
                    level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
                } else {
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
        return !level.isEmptyBlock(belowPos);
    }

    @Override
    public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        if (!level.isClientSide()) {
            if (!canSurvive(state, level, pos)) {
                level.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos neighborPos, boolean isMoving) {
        if (!level.isClientSide()) {
            if (!canSurvive(state, level, pos)) {
                level.destroyBlock(pos, true);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }
}
