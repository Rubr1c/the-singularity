package net.az.the_singularity.init;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.items.DarkRepository;
import net.az.the_singularity.items.ModTiers;
import net.az.the_singularity.items.tools.AstralitePickaxe;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
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

    public static final RegistryObject<Item> SINGULARITY_SHARD_ORE = create(
            "singularity_shard_ore",
            () -> new BlockItem(ModBlocks.SINGULARITY_SHARD_ORE.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> DARK_REPOSITORY = create(
            "dark_repository",
            () -> new DarkRepository(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> ASTRALITE_GEM = create(
            "astralite_gem",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> ASTRALITE_ORE = create(
            "astralite_ore",
            () -> new BlockItem(ModBlocks.ASTRALITE_ORE.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> ASTRALITE_PICKAXE = create(
            "astralite_pickaxe",
            () -> new AstralitePickaxe()
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
