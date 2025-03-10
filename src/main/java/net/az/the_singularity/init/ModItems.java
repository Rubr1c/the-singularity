package net.az.the_singularity.init;

import net.az.the_singularity.Singularity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Singularity.MOD_ID);

    public static final RegistryObject<Item> SINGULARITY_SHARD = create(
            "singularity_shard", () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> DEEPSLATE_SINGULARITY_SHARD_ORE = create(
            "singularity_shard_ore",
            () -> new BlockItem(ModBlocks.SINGULARITY_SHARD_ORE.get(), new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static RegistryObject<Item> create(String name, Supplier<Item> item) {
        return ModTabs.addToMainTab(
                ITEMS.register(name, item)
        );
    }
}
