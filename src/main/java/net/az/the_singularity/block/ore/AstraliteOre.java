package net.az.the_singularity.block.ore;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class AstraliteOre extends DropExperienceBlock {

    public AstraliteOre() {
        super(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(25.0f, 100f)
                .lightLevel((level) -> 5)
                .sound(SoundType.STONE)
                .mapColor(MapColor.COLOR_YELLOW),
                UniformInt.of(10, 15));
    }
}
