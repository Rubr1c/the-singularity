package net.az.the_singularity.datagen;

import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModBlocks;
import net.az.the_singularity.init.ModItems;
import net.az.the_singularity.init.ModTabs;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, Singularity.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {

        for (RegistryObject<Item> item: ModItems.ITEMS.getEntries()) {
            if (item.get() instanceof BlockItem) continue;
            addItem(item);
        }

        for (RegistryObject<Block> block: ModBlocks.BLOCKS.getEntries()) {
            addBlock(block);
        }

        for (RegistryObject<CreativeModeTab> tab: ModTabs.CREATIVE_MODE_TABS.getEntries()) {
            addTab(tab);
        }
    }

    private void addBlock(RegistryObject<Block> block) {
        add(block.get(), capitalize(block.getId().getPath().replace('_', ' ')));
    }

    private void addItem(RegistryObject<Item> item) {
        add(item.get(), capitalize(item.getId().getPath().replace('_', ' ')));
    }

    private void addTab(RegistryObject<CreativeModeTab> creativeModeTab) {
        add("creative_tab." + Singularity.MOD_ID + "." + creativeModeTab.getId().getPath(),
                capitalize(creativeModeTab.getId().getPath().replace('_', ' ')));
    }

    public  String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.split("\\s+");
        StringBuilder capitalizedSentence = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedSentence.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return capitalizedSentence.toString().trim();
    }
}
