package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModBlocks;
import net.az.the_singularity.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
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

        oreCook(pWriter,
                List.of(ModBlocks.ASTRALITE_ORE.get()),
                RecipeCategory.MISC,
                ModItems.ASTRALITE_GEM.get(),
                1f,
                200,
                "astralite"
        );

        smithingSet(ModItems.ASTRALITE_UPGRADE_SMITHING_TEMPLATE.get(),
                    ModItems.ASTRALITE_GEM.get(),
                    Map.of(Items.NETHERITE_SWORD, ModItems.ASTRALITE_SWORD.get(),
                           Items.NETHERITE_PICKAXE, ModItems.ASTRALITE_PICKAXE.get(),
                           Items.NETHERITE_SHOVEL, ModItems.ASTRALITE_SHOVEL.get(),
                           Items.NETHERITE_AXE, ModItems.ASTRALITE_AXE.get(),
                           Items.NETHERITE_HOE, ModItems.ASTRALITE_HOE.get(),
                           Items.NETHERITE_HELMET, ModItems.ASTRALITE_HELMET.get(),
                           Items.NETHERITE_CHESTPLATE, ModItems.ASTRALITE_CHESTPLATE.get(),
                           Items.NETHERITE_LEGGINGS, ModItems.ASTRALITE_LEGGINGS.get(),
                           Items.NETHERITE_BOOTS, ModItems.ASTRALITE_BOOTS.get()
                    ),
                    List.of(RecipeCategory.COMBAT,
                            RecipeCategory.TOOLS,
                            RecipeCategory.TOOLS,
                            RecipeCategory.TOOLS,
                            RecipeCategory.TOOLS,
                            RecipeCategory.COMBAT,
                            RecipeCategory.COMBAT,
                            RecipeCategory.COMBAT,
                            RecipeCategory.COMBAT
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

    private void oreCook(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                         List<ItemLike> pIngredients,
                         RecipeCategory pCategory,
                         ItemLike pResult,
                         float pExperience,
                         int pCookingTime,
                         String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                      List<ItemLike> pIngredients,
                                      RecipeCategory pCategory,
                                      ItemLike pResult,
                                      float pExperience,
                                      int pCookingTime,
                                      String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                      List<ItemLike> pIngredients,
                                      RecipeCategory pCategory,
                                      ItemLike pResult,
                                      float pExperience,
                                      int pCookingTime,
                                      String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                     RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
                                     List<ItemLike> pIngredients,
                                     RecipeCategory pCategory,
                                     ItemLike pResult,
                                     float pExperience,
                                     int pCookingTime,
                                     String pGroup,
                                     String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  Singularity.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
