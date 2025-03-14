package net.az.the_singularity.items.tools;

import net.az.the_singularity.init.ModTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AstraliteHoe extends HoeItem {

    public AstraliteHoe() {
        super(ModTiers.ASTRALITE, 1, 1, new Properties());
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        ItemStack stack = pContext.getItemInHand();
        BlockState state = level.getBlockState(pos);
        Player player = pContext.getPlayer();

        if (player.isShiftKeyDown()) {
            if (state.getBlock() instanceof BonemealableBlock block) {
                if (block.isValidBonemealTarget(level, pos, state, level.isClientSide())) {
                    if (!level.isClientSide()) {
                        block.performBonemeal((ServerLevel) level, level.random, pos, state);

                        level.levelEvent(2005, pos, 0);

                        stack.hurtAndBreak(1, player,
                                p -> p.broadcastBreakEvent(pContext.getHand())
                        );
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        } else {
            if (state.getBlock() instanceof CropBlock cropBlock) {
                if (cropBlock.isMaxAge(state)) {
                    if (!level.isClientSide()) {
                        level.destroyBlock(pos, true);
                        level.setBlock(pos, cropBlock.getStateForAge(0), 3);
                        level.levelEvent(2005, pos, 0);
                        stack.hurtAndBreak(1, player,
                                p -> p.broadcastBreakEvent(pContext.getHand())
                        );
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }

        return super.useOn(pContext);
    }
}