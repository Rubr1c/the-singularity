package net.az.the_singularity.worldgen.structure;

import com.mojang.datafixers.util.Pair;
import net.az.the_singularity.Singularity;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public class ModTemplatePools {
    public static final ResourceKey<StructureTemplatePool> ASTRALITE_TEMPLE = createKey("astralite_temple");

    public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureProcessorList> processorLists = context.lookup(Registries.PROCESSOR_LIST);

        // Get the empty processor list
        var emptyProcessorList = processorLists.getOrThrow(
                ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation("minecraft:empty")));

        // Create the structure pool element with RIGID projection
        var poolElement = StructurePoolElement.single(
                Singularity.MOD_ID + ":astralite_temple", emptyProcessorList)
                .apply(StructureTemplatePool.Projection.RIGID);

        register(
                context,
                ASTRALITE_TEMPLE,
                ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation("minecraft:empty")),
                List.of(Pair.of(poolElement, 1)));
    }

    private static void register(BootstapContext<StructureTemplatePool> context,
            ResourceKey<StructureTemplatePool> key,
            ResourceKey<StructureTemplatePool> fallback,
            List<Pair<StructurePoolElement, Integer>> elements) {
        context.register(key, new StructureTemplatePool(
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(fallback),
                elements));
    }

    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL,
                new ResourceLocation(Singularity.MOD_ID, name));
    }
}