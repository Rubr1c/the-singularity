package net.az.the_singularity.item.tool.tier;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.tags.ModTags;
import net.az.the_singularity.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModTiers {
    public static final Tier ASTRALITE = TierSortingRegistry.registerTier(
            new ForgeTier(5,
                    2500,
                    15.0f,
                    5.0f,
                    25,
                    ModTags.Blocks.NEEDS_ASTRALITE_TOOL,
                    () -> Ingredient.of(ModItems.ASTRALITE_PICKAXE.get())),
            new ResourceLocation(Singularity.MOD_ID, "astralite"),
            List.of(Tiers.NETHERITE), List.of()
    );
}
