package net.az.the_singularity.init;

import net.az.the_singularity.Singularity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ASTRALITE_TOOL = tag("needs_astralite_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Singularity.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Singularity.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_ASTRALITE_TEMPLE = tag("has_astralite_temple");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME,
                    new ResourceLocation(Singularity.MOD_ID, name));
        }
    }
}
