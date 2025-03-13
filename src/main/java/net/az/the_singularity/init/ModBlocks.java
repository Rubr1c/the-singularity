package net.az.the_singularity.init;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.blocks.AstraliteOre;
import net.az.the_singularity.blocks.SingularityShardOre;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Singularity.MOD_ID);

    public static final RegistryObject<Block> SINGULARITY_SHARD_ORE = BLOCKS.register(
            "singularity_shard_ore",
            () -> new SingularityShardOre()
    );

    public static final RegistryObject<Block> ASTRALITE_ORE = BLOCKS.register(
            "astralite_ore",
            () -> new AstraliteOre()
    );

    public static final RegistryObject<Block> ASTRALITE_INFUSED_PURPUR_BLOCK = BLOCKS.register(
        "astralite_infused_purpur_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK))
    );

    public static final RegistryObject<Block> ASTRALITE_INFUSED_PURPUR_PILLAR = BLOCKS.register(
            "astralite_infused_purpur_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_PILLAR))
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
