package net.az.the_singularity.block.entity;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, Singularity.MOD_ID
    );

    public static final RegistryObject<BlockEntityType<AstralExtractorBlockEntity>> ASTRAL_EXTRACTOR_BE =
            BLOCK_ENTITIES.register("astral_extractor_be",
                    () -> BlockEntityType.Builder.of(AstralExtractorBlockEntity::new,
                            ModBlocks.ASTRAL_EXTRACTOR.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
