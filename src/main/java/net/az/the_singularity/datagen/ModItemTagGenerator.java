package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Singularity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.TRIMMABLE_ARMOR).add(
                ModItems.ASTRALITE_HELMET.get(),
                ModItems.ASTRALITE_CHESTPLATE.get(),
                ModItems.ASTRALITE_LEGGINGS.get(),
                ModItems.ASTRALITE_BOOTS.get()
        );
    }
}
