package net.az.the_singularity.item.armor;

import net.az.the_singularity.item.armor.material.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class AstraliteBoots extends ModArmorItem {


    public AstraliteBoots() {
        super(ModArmorMaterials.ASTRALITE,
                Type.BOOTS,
                new Properties(),
                new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 220,
                        1, false, false, true));
    }
}
