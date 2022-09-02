package dev.arcegy.fromspores.core.registry;

import dev.arcegy.fromspores.FromSpores;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FromSporesSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FromSpores.MOD_ID);
    public static final RegistryObject<SoundEvent> CRAVER_AMBIENT = register("entity.craver.ambient");
    public static final RegistryObject<SoundEvent> CRAVER_HURT = register("entity.craver.hurt");
    public static final RegistryObject<SoundEvent> CRAVER_DEATH = register("entity.craver.death");
    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent((new ResourceLocation(FromSpores.MOD_ID, name))));
    }
}
