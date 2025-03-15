package net.az.the_singularity.creative;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, Singularity.MOD_ID
    );

    public static final List<Supplier<? extends ItemLike>> SINGULARITY_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> SINGULARITY_TAB = CREATIVE_MODE_TABS.register(
            "singularity",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.SINGULARITY_SHARD.get()))
                    .title(Component.translatable("creative_tab.the_singularity.singularity"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        SINGULARITY_TAB_ITEMS.forEach(item -> output.accept(item.get()));
                    }))
                    .build()

    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static <T extends Item> RegistryObject<T> addToMainTab(RegistryObject<T> item) {
        SINGULARITY_TAB_ITEMS.add(item);
        return item;
    }
}
