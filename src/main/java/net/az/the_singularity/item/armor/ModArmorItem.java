package net.az.the_singularity.item.armor;

import com.google.common.collect.ImmutableMap;
import net.az.the_singularity.item.armor.material.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.ASTRALITE, new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 2,
                            false, false, true))
                    .build();

    private final MobEffectInstance individualEffect;

    public ModArmorItem(ArmorMaterial pMaterial,
                        Type pType,
                        Properties pProperties,
                        MobEffectInstance individualEffect) {
        super(pMaterial, pType, pProperties);
        this.individualEffect = individualEffect;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (!world.isClientSide()) {
            if (individualEffect != null && !player.hasEffect(individualEffect.getEffect())) {
                player.addEffect(new MobEffectInstance(individualEffect));
            }
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial material = entry.getKey();
            MobEffectInstance effectInstance = entry.getValue();
            if (hasCorrectArmorOn(material, player)) {
                if (effectInstance.getEffect() == MobEffects.HEALTH_BOOST) {
                    applyCustomHealthBoost(player, material, effectInstance);
                    player.removeEffect(MobEffects.HEALTH_BOOST);
                } else {
                    if (!player.hasEffect(effectInstance.getEffect())) {
                        player.addEffect(new MobEffectInstance(effectInstance));
                    }
                }
            } else {
                if (effectInstance.getEffect() == MobEffects.HEALTH_BOOST) {
                    removeCustomHealthBoost(player, material);
                } else {
                    player.removeEffect(effectInstance.getEffect());
                }
            }
        }
    }

    /**
     * Applies a custom health boost via an attribute modifier.
     * In this example, each amplifier level adds 4 extra health points.
     */
    private void applyCustomHealthBoost(Player player, ArmorMaterial material, MobEffectInstance effectInstance) {
        if (player.getAttribute(Attributes.MAX_HEALTH) != null) {
            UUID modifierUUID = UUID.nameUUIDFromBytes(("health_boost_" + material.toString()).getBytes(StandardCharsets.UTF_8));
            double extraHealth = 2.0 * (effectInstance.getAmplifier() + 1);
            if (player.getAttribute(Attributes.MAX_HEALTH).getModifier(modifierUUID) == null) {
                AttributeModifier modifier = new AttributeModifier(
                        modifierUUID,
                        "Full suit health boost for " + material.toString(),
                        extraHealth,
                        AttributeModifier.Operation.ADDITION
                );
                player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(modifier);
            }
        }
    }

    /**
     * Removes the custom health boost attribute modifier for the given armor material.
     * Also adjusts the player's current health proportionally to prevent health-related issues.
     */
    private void removeCustomHealthBoost(Player player, ArmorMaterial material) {
        if (player.getAttribute(Attributes.MAX_HEALTH) != null) {
            UUID modifierUUID = UUID.nameUUIDFromBytes(("health_boost_" + material.toString()).getBytes(StandardCharsets.UTF_8));
            AttributeModifier modifier = player.getAttribute(Attributes.MAX_HEALTH).getModifier(modifierUUID);
            
            if (modifier != null) {
                double currentHealth = player.getHealth();
                double currentMaxHealth = player.getMaxHealth();
                double extraHealth = modifier.getAmount();
                
                player.getAttribute(Attributes.MAX_HEALTH).removeModifier(modifierUUID);
                
                double newMaxHealth = currentMaxHealth - extraHealth;
                
                if (currentHealth > newMaxHealth) {
                    player.setHealth((float) Math.min(currentHealth, newMaxHealth));
                }
            }
        }
    }

    /**
     * Checks that every armor slot is filled with an ArmorItem of the specified material.
     */
    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
        ArmorItem breastplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
