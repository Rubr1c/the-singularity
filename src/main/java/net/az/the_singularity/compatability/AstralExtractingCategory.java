package net.az.the_singularity.compatability;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.az.the_singularity.Singularity;
import net.az.the_singularity.block.ModBlocks;
import net.az.the_singularity.recipe.AstralExtractingRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class AstralExtractingCategory implements IRecipeCategory<AstralExtractingRecipe> {
    public static final ResourceLocation UID =
            new ResourceLocation(Singularity.MOD_ID, "astral_extracting");
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(Singularity.MOD_ID, "textures/gui/astral_extractor_gui.png");

    public static final RecipeType<AstralExtractingRecipe> ASTRAL_EXTRACTING_TYPE =
            new RecipeType<>(UID, AstralExtractingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AstralExtractingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
        this.icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.ASTRAL_EXTRACTOR.get())
        );
    }


    @Override
    public RecipeType<AstralExtractingRecipe> getRecipeType() {
        return ASTRAL_EXTRACTING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.the_singularity.astral_extractor");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AstralExtractingRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 35).addIngredients(recipe.getFuel());
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 35).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(recipe.getResultItem(null));
    }
}
