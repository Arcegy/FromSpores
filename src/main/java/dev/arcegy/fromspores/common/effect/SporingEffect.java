package dev.arcegy.fromspores.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class SporingEffect extends MobEffect {
    Random random = new Random();
    public SporingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int potionAmplifier) {
        if (livingEntity.getHealth() > 0.1F) {
            float chance = random.nextFloat();
            if (potionAmplifier > 0) {
                if (chance <= 0.1 * (potionAmplifier * 1.2)) {
                    if (!(livingEntity.getRemainingFireTicks() == 0)) {
                        livingEntity.setRemainingFireTicks(40);
                    }
                }
            }
            else {
                if (chance <= 0.01) {
                    if (!(livingEntity.getRemainingFireTicks() == 0)) {
                        livingEntity.setRemainingFireTicks(40);
                    }
                }
            }
        }
        super.applyEffectTick(livingEntity, potionAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int potionDuration, int potionAmplifier) {
        return true;
    }
}
