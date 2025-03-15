package net.az.the_singularity.item.tool;

import net.az.the_singularity.item.tool.tier.ModTiers;
import net.az.the_singularity.world.WorldUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AstraliteShovel extends ShovelItem {

    public AstraliteShovel() {
        super(ModTiers.ASTRALITE, 0, 0, new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel,
                                                  Player pPlayer,
                                                  InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            if (pPlayer.isShiftKeyDown()) {
                CompoundTag tag = stack.getOrCreateTag();
                if (tag.getInt("mode") == 0) {
                    tag.putInt("mode", 1);
                    pPlayer.displayClientMessage(
                            Component.literal("Flatten Mode").withStyle(ChatFormatting.AQUA),
                            true);
                } else if (tag.getInt("mode") == 1) {
                    tag.putInt("mode", 2);
                    pPlayer.displayClientMessage(
                            Component.literal("Tunnel Mode").withStyle(ChatFormatting.GRAY),
                            true);
                } else if (tag.getInt("mode") == 2) {
                    tag.putInt("mode", 0);
                    pPlayer.displayClientMessage(
                            Component.literal("Normal Mode"), true);
                }
            } else {
                return InteractionResultHolder.pass(stack);
            }
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean mineBlock(ItemStack pStack,
                             Level pLevel,
                             BlockState pState,
                             BlockPos pPos,
                             LivingEntity pEntityLiving) {

        if (!pLevel.isClientSide()) {
            CompoundTag tag = pStack.getOrCreateTag();
            WorldUtil.breakWithMode(tag, pStack, pLevel, pState, pPos, pEntityLiving, null);
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

}
