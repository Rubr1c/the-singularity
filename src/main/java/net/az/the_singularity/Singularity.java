package net.az.the_singularity;

import com.mojang.logging.LogUtils;
import net.az.the_singularity.init.ModBlocks;
import net.az.the_singularity.init.ModItems;
import net.az.the_singularity.init.ModTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Singularity.MOD_ID)
public class Singularity {

    public static final String MOD_ID = "the_singularity";
    private static final Logger LOGGER = LogUtils.getLogger();


    public Singularity(FMLJavaModLoadingContext context) {
        final IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

}
