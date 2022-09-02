package dev.arcegy.fromspores.core.registry;

import dev.arcegy.fromspores.FromSpores;
import dev.arcegy.fromspores.common.effect.SporingEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FromSporesMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, FromSpores.MOD_ID);

    public static final RegistryObject<MobEffect> SPORING = EFFECTS.register("sporing", () ->
            new SporingEffect(MobEffectCategory.HARMFUL, 16753768));
}
