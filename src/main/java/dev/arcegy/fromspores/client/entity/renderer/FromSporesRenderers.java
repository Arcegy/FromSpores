package dev.arcegy.fromspores.client.entity.renderer;

import dev.arcegy.fromspores.FromSpores;
import dev.arcegy.fromspores.client.entity.renderer.entity.CraverRenderer;
import dev.arcegy.fromspores.core.registry.FromSporesEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = FromSpores.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FromSporesRenderers {
    @SubscribeEvent
    public static void rendererSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FromSporesEntityTypes.CRAVER.get(), CraverRenderer::new);
    }
}
