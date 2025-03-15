package net.az.the_singularity.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.az.the_singularity.Singularity;
import net.az.the_singularity.recipe.AstralExtractingRecipe;
import net.az.the_singularity.screen.AstralExtractorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEISingularityModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Singularity.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AstralExtractingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<AstralExtractingRecipe> astralExtractingRecipes =
                recipeManager.getAllRecipesFor(AstralExtractingRecipe.Type.INSTANCE);

        registration.addRecipes(AstralExtractingCategory.ASTRAL_EXTRACTING_TYPE, astralExtractingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AstralExtractorScreen.class, 79, 35, 24, 17,
                AstralExtractingCategory.ASTRAL_EXTRACTING_TYPE);
    }
}
