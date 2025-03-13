package net.az.the_singularity.datagen;

import net.az.the_singularity.init.ModBlocks;
import net.az.the_singularity.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.Map;
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

        smithingSet(ModItems.ASTRALITE_UPGRADE_SMITHING_TEMPLATE.get(),
                    ModItems.ASTRALITE_GEM.get(),
                    Map.of(Items.NETHERITE_SWORD, ModItems.ASTRALITE_SWORD.get(),
                           Items.NETHERITE_PICKAXE, ModItems.ASTRALITE_PICKAXE.get()
                    ),
                    List.of(RecipeCategory.COMBAT,
                            RecipeCategory.TOOLS
                    ),
                    pWriter);
    }

    private void smithing(Item template,
                          Item ingot,
                          Item pIngredientItem,
                          Item pResultItem,
                          RecipeCategory pCategory,
                          Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(pIngredientItem),
                Ingredient.of(ingot), pCategory, pResultItem)
                .unlocks(getHasName(pResultItem), has(ingot))
                .save(pFinishedRecipeConsumer, getItemName(pResultItem) + "_smithing");
    }

    private void smithingSet(Item template,
                             Item ingot,
                             Map<Item, Item> pItems,
                             List<RecipeCategory> pCategories,
                             Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        int i = 0;
        for (Item item: pItems.keySet()) {
            System.out.println(item);
            this.smithing(
                    template,
                    ingot,
                    item,
                    pItems.get(item),
                    pCategories.get(i++),
                    pFinishedRecipeConsumer
            );
        }
    }
}
