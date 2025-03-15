package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.block.ModBlocks;
import net.az.the_singularity.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Singularity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.NEEDS_ASTRALITE_TOOL).add(
                ModBlocks.SINGULARITY_SHARD_ORE.get()
        );

        addOre(ModBlocks.SINGULARITY_SHARD_ORE.get(),
               ModBlocks.ASTRALITE_ORE.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.ASTRALITE_INFUSED_PURPUR_BLOCK.get(),
                ModBlocks.ASTRALITE_INFUSED_PURPUR_PILLAR.get()
        );
    }

    private void addOre(Block... blocks) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blocks);
        this.tag(Tags.Blocks.ORES).add(blocks);
    }
}
