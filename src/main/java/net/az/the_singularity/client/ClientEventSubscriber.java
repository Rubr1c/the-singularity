package net.az.the_singularity.client;


import net.az.the_singularity.Singularity;
import net.az.the_singularity.init.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Singularity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.DARK_REPOSITORY.get(),
                    new ResourceLocation(Singularity.MOD_ID, "vstored"),
                    (stack, world, entity, seed) -> {
                CompoundTag tag = stack.getTag();
                return tag != null ? (float) tag.getInt("vStored") : 0.0F;
            });
        });
    }
}
