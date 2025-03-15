package net.az.the_singularity.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.az.the_singularity.Singularity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AstralExtractingRecipe implements Recipe<SimpleContainer> {
    private final Ingredient fuel;
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public AstralExtractingRecipe(Ingredient fuel,
                                  NonNullList<Ingredient> inputItems,
                                  ItemStack output,
                                  ResourceLocation id) {
        this.fuel = fuel;
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }


    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        return fuel.test(pContainer.getItem(0)) &&
               inputItems.get(0).test(pContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public Ingredient getFuel() {
        return fuel;
    }

    public static class Type implements RecipeType<AstralExtractingRecipe>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "extracting";
    }

    public static class Serializer implements RecipeSerializer<AstralExtractingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Singularity.MOD_ID, "astral_extracting");

        @Override
        public AstralExtractingRecipe fromJson(ResourceLocation pRecipeId,
                                               JsonObject pSerializedRecipe) {

            ItemStack output = ShapedRecipe.itemStackFromJson(
                    GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            Ingredient fuel = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "fuel"));


            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AstralExtractingRecipe(fuel, inputs, output, pRecipeId);
        }

        @Override
        public @Nullable AstralExtractingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            Ingredient fuel = Ingredient.fromNetwork(pBuffer);

            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();

            return new AstralExtractingRecipe(fuel, inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, AstralExtractingRecipe pRecipe) {
            pRecipe.fuel.toNetwork(pBuffer);

            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient: pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
