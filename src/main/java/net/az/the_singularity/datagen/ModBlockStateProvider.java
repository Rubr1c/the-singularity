package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Singularity.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SINGULARITY_SHARD_ORE);
        blockWithItem(ModBlocks.ASTRALITE_ORE);
        blockWithItem(ModBlocks.ASTRALITE_INFUSED_PURPUR_BLOCK);
        pillarBlock(ModBlocks.ASTRALITE_INFUSED_PURPUR_PILLAR);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void pillarBlock(RegistryObject<Block> blockRegistryObject) {
        if (blockRegistryObject.get() instanceof RotatedPillarBlock block) {
            axisBlock(block,
                    new ResourceLocation(Singularity.MOD_ID, "block/" + blockRegistryObject.getId().getPath()),
                    new ResourceLocation(Singularity.MOD_ID, "block/" + blockRegistryObject.getId().getPath() + "_top")
            );
            simpleBlockItem(block, models().getExistingFile(modLoc("block/" + blockRegistryObject.getId().getPath())));
        }

    }
}
