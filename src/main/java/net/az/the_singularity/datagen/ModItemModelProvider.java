package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Singularity.MOD_ID, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        simpleItem(ModItems.SINGULARITY_SHARD);
        simpleItem(ModItems.DARK_REPOSITORY);

        itemWithState(ModItems.DARK_REPOSITORY,
                      List.of("vstored"),
                      List.of(
                              List.of(5.0f, 10.0f, 15.0f, 20.0f, 25.0f,
                                      30.0f, 35.0f, 40.0f, 45.0f, 50.0f, 55.0f, 60.0f)
                      ));

        simpleItem(ModItems.ASTRALITE_GEM);
        handheldItem(ModItems.ASTRALITE_PICKAXE);
        handheldItem(ModItems.ASTRALITE_SWORD);
        handheldItem(ModItems.ASTRALITE_AXE);
        handheldItem(ModItems.ASTRALITE_SHOVEL);
        handheldItem(ModItems.ASTRALITE_HOE);
        simpleItem(ModItems.ASTRALITE_UPGRADE_SMITHING_TEMPLATE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Singularity.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Singularity.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder itemWithState(RegistryObject<Item> item, List<String> predicates, List<List<Float>> variantValues) {
        ItemModelBuilder base = getBuilder(item.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("item/generated")))
                .texture("layer0", new ResourceLocation(Singularity.MOD_ID, "item/" + item.getId().getPath()));

        int variantIndex = 1;

        for (int i = 0; i < predicates.size(); i++) {
            String predicateKey = predicates.get(i);
            for (Float threshold : variantValues.get(i)) {
                base.override()
                        .predicate(new ResourceLocation(Singularity.MOD_ID, predicateKey), threshold)
                        .model(new ModelFile.UncheckedModelFile(new ResourceLocation(Singularity.MOD_ID, "item/" + item.getId().getPath() + "_variant" + variantIndex)))
                        .end();

                getBuilder(item.getId().getPath() + "_variant" + variantIndex)
                        .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("item/generated")))
                        .texture("layer0", new ResourceLocation(Singularity.MOD_ID, "item/" + item.getId().getPath() + "_variant" + variantIndex));
                variantIndex++;
            }
        }
        return base;
    }
}
