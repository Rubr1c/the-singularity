package net.az.the_singularity;

import net.az.the_singularity.block.entity.ModBlockEntities;
import net.az.the_singularity.block.ModBlocks;
import net.az.the_singularity.item.ModItems;
import net.az.the_singularity.creative.ModTabs;
import net.az.the_singularity.recipe.ModRecipes;
import net.az.the_singularity.gui.screen.AstralExtractorScreen;
import net.az.the_singularity.gui.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Singularity.MOD_ID)
public class Singularity {

    public static final String MOD_ID = "the_singularity";


    public Singularity(FMLJavaModLoadingContext context) {
        final IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

}
