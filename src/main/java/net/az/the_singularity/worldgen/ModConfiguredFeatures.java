package net.az.the_singularity.worldgen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SINGULARITY_SHARD_ORE =
            registerKey("singularity_shard_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ASTRALITE_ORE =
            registerKey("astralite_ore");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endstoneReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldSingularityOre = List.of(
                OreConfiguration.target(deepslateReplaceables,
                        ModBlocks.SINGULARITY_SHARD_ORE.get().defaultBlockState())
        );



        register(context, OVERWORLD_SINGULARITY_SHARD_ORE, Feature.ORE, new OreConfiguration(overworldSingularityOre, 1));
        register(context, END_ASTRALITE_ORE, Feature.ORE, new OreConfiguration(
                endstoneReplaceables, ModBlocks.ASTRALITE_ORE.get().defaultBlockState(), 3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Singularity.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
