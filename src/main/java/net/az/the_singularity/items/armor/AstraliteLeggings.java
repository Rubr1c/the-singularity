package net.az.the_singularity.items.armor;

import net.az.the_singularity.init.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class AstraliteLeggings extends ModArmorItem {


    public AstraliteLeggings() {
        super(ModArmorMaterials.ASTRALITE,
                Type.LEGGINGS,
                new Properties(),
                new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 220,
                        1, false, false, true));
    }
}
