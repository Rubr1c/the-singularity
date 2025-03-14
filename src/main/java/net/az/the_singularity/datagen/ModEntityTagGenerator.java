package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagGenerator extends EntityTypeTagsProvider {
    public ModEntityTagGenerator(PackOutput p_256095_,
                                 CompletableFuture<HolderLookup.Provider> p_256572_,
                                 @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256095_, p_256572_, Singularity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Entities.END_MOBS).add(
                EntityType.ENDERMAN,
                EntityType.SHULKER,
                EntityType.ENDER_DRAGON,
                EntityType.ENDERMITE
        );
    }
}
