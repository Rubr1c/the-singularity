package net.az.the_singularity.item.armor;

import net.az.the_singularity.init.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

public class AstraliteHelmet extends ModArmorItem {


    public AstraliteHelmet() {
        super(ModArmorMaterials.ASTRALITE,
                Type.HELMET,
                new Item.Properties(),
                new MobEffectInstance(MobEffects.NIGHT_VISION, 220,
                        1, false, false, true));
    }
}
