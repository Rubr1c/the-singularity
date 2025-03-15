package net.az.the_singularity.block.ore;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SingularityShardOre extends DropExperienceBlock {
    public SingularityShardOre() {
        super(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), UniformInt.of(15, 20));
    }
}
