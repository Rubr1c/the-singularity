package net.az.the_singularity.item.armor;

import net.az.the_singularity.item.armor.material.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

public class AstraliteChestplate extends ModArmorItem {


    public AstraliteChestplate() {
        super(ModArmorMaterials.ASTRALITE,
                Type.CHESTPLATE,
                new Item.Properties(),
                new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 220,
                        1, false, false, true));
    }
}
