package net.az.the_singularity.block;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.block.base.AstralExtractor;
import net.az.the_singularity.block.ore.AstraliteOre;
import net.az.the_singularity.block.ore.SingularityShardOre;
import net.az.the_singularity.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Singularity.MOD_ID);

    public static final RegistryObject<Block> SINGULARITY_SHARD_ORE = registerBlock(
            "singularity_shard_ore",
            () -> new SingularityShardOre()
    );

    public static final RegistryObject<Block> ASTRAL_EXTRACTOR = registerBlock(
            "astral_extractor",
            () -> new AstralExtractor(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion())
    );

    public static final RegistryObject<Block> ASTRALITE_ORE = registerBlock(
            "astralite_ore",
            () -> new AstraliteOre()
    );

    public static final RegistryObject<Block> ASTRALITE_INFUSED_PURPUR_BLOCK = registerBlock(
        "astralite_infused_purpur_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK))
    );

    public static final RegistryObject<Block> ASTRALITE_INFUSED_PURPUR_PILLAR = registerBlock(
            "astralite_infused_purpur_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_PILLAR))
    );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.create(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
