package net.az.the_singularity.worldgen.structure;

import net.az.the_singularity.Singularity;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class ModStructureSets {
    public static final ResourceKey<StructureSet> ASTRALITE_TEMPLE = registerKey("astralite_temple");

    public static void bootstrap(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        // Register the astralite temple structure set
        context.register(ASTRALITE_TEMPLE, new StructureSet(
                structures.getOrThrow(ModStructures.ASTRALITE_TEMPLE),
                new RandomSpreadStructurePlacement(
                        150, // spacing
                        100, // separation
                        RandomSpreadType.LINEAR,
                        1961465172 // salt
                )));
    }

    private static ResourceKey<StructureSet> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(Singularity.MOD_ID, name));
    }
}