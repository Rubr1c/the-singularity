package net.az.the_singularity.items.tools;

import net.az.the_singularity.init.ModTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AstraliteAxe extends AxeItem {


    public AstraliteAxe() {
        super(ModTiers.ASTRALITE, 5, -3.0f, new Properties());
    }

    @Override
    public boolean mineBlock(ItemStack pStack,
                             Level pLevel,
                             BlockState pState,
                             BlockPos pPos,
                             LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide()) {
            if (pState.is(BlockTags.LOGS)) {
               mineX(BlockTags.LOGS,
                       pStack,
                       pLevel,
                       pEntityLiving,
                       List.of(pPos),
                       new AtomicInteger(64),
                       true);
            } else if (pState.is(BlockTags.LEAVES)) {
                mineX(BlockTags.LEAVES,
                        pStack,
                        pLevel,
                        pEntityLiving,
                        List.of(pPos),
                        new AtomicInteger(128),
                        true);
            }
        }

        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    private List<BlockPos> getNeighbouringBlockPos(BlockPos pos) {
        return List.of(
                pos,
                pos.above(),
                pos.below(),
                pos.west(),
                pos.east(),
                pos.north(),
                pos.south()
        );
    }
    private void mineX(TagKey<Block> tag,
                       ItemStack stack,
                       Level level,
                       LivingEntity entity,
                       List<BlockPos> pos,
                       AtomicInteger limit,
                       boolean shouldDmg) {
        if (limit.get() <= 0) return;

        if (shouldDmg) {
            stack.hurtAndBreak(1, entity, livingEntity -> {
                livingEntity.broadcastBreakEvent(livingEntity.getUsedItemHand());
            });
        }

        for (BlockPos p : pos) {
            BlockState state = level.getBlockState(p);
            if (state.is(tag)) {
                if (limit.get() > 0) {
                    level.destroyBlock(p, true, entity);
                    limit.decrementAndGet();

                    mineX(tag, stack, level, entity, getNeighbouringBlockPos(p), limit, !shouldDmg);
                }
            }
        }
    }
}