package net.az.the_singularity.items.tools;

import net.az.the_singularity.init.ModTiers;
import net.az.the_singularity.world.WorldUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import java.util.List;

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
