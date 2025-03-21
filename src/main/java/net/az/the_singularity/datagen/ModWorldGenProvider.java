package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.worldgen.ModBiomeModifiers;
import net.az.the_singularity.worldgen.ModConfiguredFeatures;
import net.az.the_singularity.worldgen.ModPlacedFeatures;
import net.az.the_singularity.worldgen.structure.ModStructures;
import net.az.the_singularity.worldgen.structure.ModStructureSets;
import net.az.the_singularity.worldgen.structure.ModTemplatePools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            .add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap);

    public ModWorldGenProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Singularity.MOD_ID));
    }
}
