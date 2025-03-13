package net.az.the_singularity.datagen;

import net.az.the_singularity.init.ModBlocks;
import net.az.the_singularity.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                ModBlocks.ASTRALITE_INFUSED_PURPUR_BLOCK.get(), 8)
                .pattern("BBB")
                .pattern("BGB")
                .pattern("BBB")
                .define('B', Blocks.PURPUR_BLOCK)
                .define('G', ModItems.ASTRALITE_GEM.get())
                .unlockedBy(getHasName(ModBlocks.ASTRALITE_INFUSED_PURPUR_BLOCK.get()),
                            has(ModItems.ASTRALITE_GEM.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModBlocks.ASTRALITE_INFUSED_PURPUR_PILLAR.get(), 8)
                .pattern("BBB")
                .pattern("BGB")
                .pattern("BBB")
                .define('B', Blocks.PURPUR_PILLAR)
                .define('G', ModItems.ASTRALITE_GEM.get())
                .unlockedBy(getHasName(ModBlocks.ASTRALITE_INFUSED_PURPUR_PILLAR.get()),
                        has(ModItems.ASTRALITE_GEM.get()))
                .save(pWriter);
    }
}
