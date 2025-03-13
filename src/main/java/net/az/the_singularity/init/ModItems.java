package net.az.the_singularity.init;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.items.DarkRepository;
import net.az.the_singularity.items.ModTiers;
import net.az.the_singularity.items.tools.AstralitePickaxe;
import net.az.the_singularity.items.tools.AstraliteSword;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    public static final RegistryObject<Item> ASTRALITE_INFUSED_PURPUR_BLOCK = create(
            "astralite_infused_purpur_block",
            () -> new BlockItem(ModBlocks.ASTRALITE_INFUSED_PURPUR_BLOCK.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> ASTRALITE_INFUSED_PURPUR_PILLAR = create(
            "astralite_infused_purpur_pillar",
            () -> new BlockItem(ModBlocks.ASTRALITE_INFUSED_PURPUR_PILLAR.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> ASTRALITE_PICKAXE = create(
            "astralite_pickaxe",
            () -> new AstralitePickaxe()
    );

    public static final RegistryObject<Item> ASTRALITE_SWORD = create(
            "astralite_sword",
            () -> new AstraliteSword()
    );

    public static final RegistryObject<Item> ASTRALITE_UPGRADE_SMITHING_TEMPLATE = create(
            "astralite_upgrade_smithing_template",
            () -> new Item(new Item.Properties())
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
