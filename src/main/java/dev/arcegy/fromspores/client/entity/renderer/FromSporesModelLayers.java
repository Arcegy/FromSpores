package dev.arcegy.fromspores.client.entity.renderer;

import dev.arcegy.fromspores.FromSpores;
import dev.arcegy.fromspores.client.entity.model.CraverModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = FromSpores.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FromSporesModelLayers {
    public static ModelLayerLocation CRAVER = new ModelLayerLocation(new ResourceLocation(FromSpores.MOD_ID, "craver"), "main");

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CRAVER, CraverModel::createBodyLayer);
    }
}
