package net.az.the_singularity.init;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.items.DarkRepository;
import net.az.the_singularity.items.armor.AstraliteBoots;
import net.az.the_singularity.items.armor.AstraliteChestplate;
import net.az.the_singularity.items.armor.AstraliteHelmet;
import net.az.the_singularity.items.armor.AstraliteLeggings;
import net.az.the_singularity.items.tools.*;
import net.minecraft.world.item.ArmorItem;
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
            "singularity_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SINGULARITY_SHARD_ORE = create(
            "singularity_shard_ore",
            () -> new BlockItem(ModBlocks.SINGULARITY_SHARD_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Item> DARK_REPOSITORY = create(
            "dark_repository",
            () -> new DarkRepository(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ASTRALITE_GEM = create(
            "astralite_gem",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTRALITE_ORE = create(
            "astralite_ore",
            () -> new BlockItem(ModBlocks.ASTRALITE_ORE.get(), new Item.Properties()));


    public static final RegistryObject<Item> ASTRALITE_UPGRADE_SMITHING_TEMPLATE = create(
            "astralite_upgrade_smithing_template",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> ASTRALITE_PICKAXE = create(
            "astralite_pickaxe",
            () -> new AstralitePickaxe());
    public static final RegistryObject<Item> ASTRALITE_SWORD = create(
            "astralite_sword",
            () -> new AstraliteSword());
    public static final RegistryObject<Item> ASTRALITE_AXE = create(
            "astralite_axe",
            () -> new AstraliteAxe());
    public static final RegistryObject<Item> ASTRALITE_SHOVEL = create(
            "astralite_shovel",
            () -> new AstraliteShovel());
    public static final RegistryObject<Item> ASTRALITE_HOE = create(
            "astralite_hoe",
            () -> new AstraliteHoe());

    public static final RegistryObject<Item> ASTRALITE_HELMET = create(
            "astralite_helmet",
            () -> new AstraliteHelmet());
    public static final RegistryObject<Item> ASTRALITE_CHESTPLATE = create(
            "astralite_chestplate",
            () -> new AstraliteChestplate());
    public static final RegistryObject<Item> ASTRALITE_LEGGINGS = create(
            "astralite_leggings",
            () -> new AstraliteLeggings());
    public static final RegistryObject<Item> ASTRALITE_BOOTS = create(
            "astralite_boots",
            () -> new AstraliteBoots());

    public static final RegistryObject<Item> ASTRALITE_INFUSED_PURPUR_BLOCK = create(
            "astralite_infused_purpur_block",
            () -> new BlockItem(ModBlocks.ASTRALITE_INFUSED_PURPUR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ASTRALITE_INFUSED_PURPUR_PILLAR = create(
            "astralite_infused_purpur_pillar",
            () -> new BlockItem(ModBlocks.ASTRALITE_INFUSED_PURPUR_PILLAR.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static RegistryObject<Item> create(String name, Supplier<Item> item) {
        return ModTabs.addToMainTab(
                ITEMS.register(name, item)
        );
    }
}
