package dev.arcegy.fromspores;

import dev.arcegy.fromspores.core.misc.FromSporesSpawns;
import dev.arcegy.fromspores.core.registry.FromSporesEntityTypes;
import dev.arcegy.fromspores.core.registry.FromSporesItems;
import dev.arcegy.fromspores.core.registry.FromSporesMobEffects;
import dev.arcegy.fromspores.core.registry.FromSporesSoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FromSpores.MOD_ID)
public class FromSpores {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "fromspores";

    public FromSpores() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        FromSporesItems.ITEMS.register(modEventBus);
        FromSporesEntityTypes.ENTITY_TYPES.register(modEventBus);
        FromSporesSoundEvents.SOUND_EVENTS.register(modEventBus);
        FromSporesMobEffects.EFFECTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FromSporesSpawns.registerSpawns();
        });
    }
}
