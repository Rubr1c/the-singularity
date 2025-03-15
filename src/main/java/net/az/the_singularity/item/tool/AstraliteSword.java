package net.az.the_singularity.item.tool;

import net.az.the_singularity.init.ModTags;
import net.az.the_singularity.init.ModTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class AstraliteSword extends SwordItem {
    public AstraliteSword() {
        super(ModTiers.ASTRALITE, 4, -2.0f, new Item.Properties());
    }

    @Override
    public void inventoryTick(ItemStack stack,
                              Level level,
                              Entity entity,
                              int slot,
                              boolean isSelected) {
        if (!(entity instanceof Player pPlayer)) return;
        if (level.isClientSide()) return;

        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getBoolean("pearlCooldownActive")) {
            int cooldown = tag.getInt("pearlCooldown");

            if (cooldown > 0) {
                cooldown--;
                tag.putInt("pearlCooldown", cooldown);

                pPlayer.displayClientMessage(
                        Component.literal("Pearl Cooldown: " + String.format("%.2f", cooldown / 20.0) + "s")
                                .withStyle(ChatFormatting.RED),
                        true
                );
            } else {
                tag.putBoolean("pearlCooldownActive", false);
                tag.remove("pearlCooldown");
            }
        }
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel,
                                                  Player pPlayer,
                                                  InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            if (pPlayer.isShiftKeyDown()) {
                CompoundTag tag = stack.getOrCreateTag();
                if (!tag.getBoolean("pearlCooldownActive")) {
                    ThrownEnderpearl enderpearl = new ThrownEnderpearl(pLevel, pPlayer);

                    enderpearl.shootFromRotation(pPlayer,
                            pPlayer.getXRot(), pPlayer.getYRot(),
                            0.0f, 1.5f, 1.0f);
                    pLevel.addFreshEntity(enderpearl);
                    tag.putInt("pearlCooldown", 30);
                    tag.putBoolean("pearlCooldownActive", true);
                    return InteractionResultHolder.pass(stack);
                }
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack,
                             LivingEntity pTarget,
                             LivingEntity pAttacker) {

        if (!pAttacker.level().isClientSide()) {
            Holder<EntityType<?>> entityTypeHolder = pTarget.getType().builtInRegistryHolder();
            if (entityTypeHolder.is(ModTags.Entities.END_MOBS) ||
                    pAttacker.level().dimension() == Level.END) {
                float bonusDamage = 5.0f;

                if (pAttacker instanceof Player) {
                    bonusDamage = this.getDamage() * 0.5f;
                }
                
                pTarget.hurt(pAttacker.level().damageSources().mobAttack(pAttacker), bonusDamage);
                
                if (pAttacker.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                            pTarget.getX(), pTarget.getY() + 0.5, pTarget.getZ(),
                            20, 0.2, 0.2, 0.2, 0.05);
                }
            }
        }

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

}
