package net.az.the_singularity.datagen.loot;

import net.az.the_singularity.init.ModBlocks;
import net.az.the_singularity.init.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.SINGULARITY_SHARD_ORE.get(),
                block -> createNoFortuneOreDrop(ModBlocks.SINGULARITY_SHARD_ORE.get(),
                                                ModItems.SINGULARITY_SHARD.get()));

        this.add(ModBlocks.ASTRALITE_ORE.get(),
                block -> createOreDrop(ModBlocks.ASTRALITE_ORE.get(),
                                       ModItems.ASTRALITE_GEM.get()));

        dropSelf(ModBlocks.ASTRALITE_INFUSED_PURPUR_BLOCK.get());
        dropSelf(ModBlocks.ASTRALITE_INFUSED_PURPUR_PILLAR.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createNoFortuneOreDrop(Block p_250450_, Item p_249745_) {
        return createSilkTouchDispatchTable(p_250450_, this.applyExplosionDecay(p_250450_, LootItem.lootTableItem(p_249745_)));
    }

}
