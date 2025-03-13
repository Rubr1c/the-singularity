package net.az.the_singularity.items.tools;

import net.az.the_singularity.items.ModTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class AstraliteSword extends SwordItem {
    public AstraliteSword() {
        super(ModTiers.ASTRALITE, 4, -2.0f, new Item.Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel,
                                                  Player pPlayer,
                                                  InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.getBoolean("push_mode")) {
                tag.putBoolean("push_mode", false);
                pPlayer.displayClientMessage(
                        Component.literal("Push Mode Off").withStyle(ChatFormatting.RED),
                        true);
            } else {
                tag.putBoolean("push_mode", true);
                pPlayer.displayClientMessage(
                        Component.literal("Push Mode On").withStyle(ChatFormatting.GREEN),
                        true);
            }
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack,
                             LivingEntity pTarget,
                             LivingEntity pAttacker) {

        if (!pAttacker.level().isClientSide()) {
            CompoundTag tag = pStack.getOrCreateTag();

            if (tag.getBoolean("push_mode")) {
                double strength = 15.0;
                double xRatio = pTarget.getX() - pAttacker.getX();
                double zRatio = pTarget.getZ() - pAttacker.getZ();
                double distance = Math.sqrt(xRatio * xRatio + zRatio * zRatio);

                if (distance != 0.0) {
                    xRatio /= distance;
                    zRatio /= distance;
                    pTarget.setDeltaMovement(xRatio * strength, 0.5, zRatio * strength);
                }
            }
        }


        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
