package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
            ExistingFileHelper existingFileHelper) {
        super(output, provider, Singularity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Biomes.HAS_ASTRALITE_TEMPLE)
                .addTag(BiomeTags.IS_END);
    }
}