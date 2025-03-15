package net.az.the_singularity.recipe;

import net.az.the_singularity.Singularity;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Singularity.MOD_ID);

    public static final RegistryObject<RecipeSerializer<AstralExtractingRecipe>> ASTRAL_EXTRACTOR_SERIALIZER =
            SERIALIZERS.register("astral_extracting", () -> AstralExtractingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
    }
}
