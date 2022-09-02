package dev.arcegy.fromspores.core.event;

import dev.arcegy.fromspores.FromSpores;
import dev.arcegy.fromspores.client.entity.model.CraverModel;
import dev.arcegy.fromspores.client.entity.renderer.entity.CraverRenderer;
import dev.arcegy.fromspores.core.registry.FromSporesEntityTypes;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FromSpores.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FromSporesEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FromSporesEntityTypes.CRAVER.get(), CraverRenderer::new);
        event.registerEntityRenderer(FromSporesEntityTypes.SPORE_BOMB.get(), ThrownItemRenderer::new);
    }
}
