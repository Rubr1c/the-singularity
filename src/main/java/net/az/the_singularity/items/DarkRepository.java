package net.az.the_singularity.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DarkRepository extends Item {


    public DarkRepository(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public void appendHoverText(ItemStack pStack,
                                @Nullable Level pLevel,
                                List<Component> pTooltipComponents,
                                TooltipFlag pIsAdvanced) {
        int stored = pStack.getOrCreateTag().getInt("vStored");
        pTooltipComponents.add(Component.literal("Stored: " + stored));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide() && pPlayer.getY() <= -128) {
            CompoundTag tag = stack.getOrCreateTag();
            int current = tag.getInt("vStored");
            tag.putInt("vStored", current + 1);
        }
        return InteractionResultHolder.success(stack);
    }
}
