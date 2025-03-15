package net.az.the_singularity.worldgen.structure;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.tags.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Map;
import java.util.Optional;

public class ModStructures {
    public static final ResourceKey<Structure> ASTRALITE_TEMPLE = registerKey("astralite_temple");

    public static void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(ASTRALITE_TEMPLE, new JigsawStructure(
                structure(context),
                templatePools.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL,
                        new ResourceLocation(Singularity.MOD_ID, "astralite_temple"))),
                Optional.empty(),
                1,
                UniformHeight.of(VerticalAnchor.absolute(128), VerticalAnchor.absolute(200)),
                true,
                Optional.empty(),
                80));
    }

    private static Structure.StructureSettings structure(BootstapContext<Structure> context) {
        return new Structure.StructureSettings(
                context.lookup(Registries.BIOME).getOrThrow(ModTags.Biomes.HAS_ASTRALITE_TEMPLE),
                Map.of(),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                TerrainAdjustment.BEARD_THIN);
    }

    private static ResourceKey<Structure> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(Singularity.MOD_ID, name));
    }
}