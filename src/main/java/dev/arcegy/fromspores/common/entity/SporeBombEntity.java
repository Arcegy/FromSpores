package dev.arcegy.fromspores.common.entity;

import dev.arcegy.fromspores.core.registry.FromSporesEntityTypes;
import dev.arcegy.fromspores.core.registry.FromSporesItems;
import dev.arcegy.fromspores.core.registry.FromSporesMobEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = ItemSupplier.class
)

public class SporeBombEntity extends ThrowableItemProjectile {
    public SporeBombEntity(EntityType<? extends SporeBombEntity> type, Level world) {
        super(type, world);
    }

    public SporeBombEntity(Level world, LivingEntity livingEntity) {
        super(FromSporesEntityTypes.SPORE_BOMB.get(), livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return FromSporesItems.SPORE_BOMB.get();
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHit(HitResult result) {
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0F, 1.0F);
            this.spawnExplosionCloud();
            this.remove(RemovalReason.DISCARDED);

            this.initialEffect(result);
            this.spawnLingeringCloud();
        }

        super.onHit(result);
    }

    private void spawnExplosionCloud() {
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(this.level, this.getX(), this.getY() + 0.6, this.getZ());

        areaEffectCloud.setRadius(0.1F);
        areaEffectCloud.setWaitTime(0);
        areaEffectCloud.setDuration(10);
        areaEffectCloud.setRadiusPerTick(0);
        areaEffectCloud.setParticle(ParticleTypes.EXPLOSION);

        this.level.addFreshEntity(areaEffectCloud);
    }


    private void spawnLingeringCloud() {
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(this.level, this.getX(), this.getY() + 0.6, this.getZ());

        areaEffectCloud.setRadius(3.0F);
        areaEffectCloud.setRadiusOnUse(-0.5F);
        areaEffectCloud.setWaitTime(10);
        areaEffectCloud.setDuration(areaEffectCloud.getDuration() / 8);
        areaEffectCloud.setRadiusPerTick(-areaEffectCloud.getRadius() / (float) areaEffectCloud.getDuration());
        areaEffectCloud.addEffect(new MobEffectInstance(FromSporesMobEffects.SPORING.get(), 300));
        areaEffectCloud.setParticle(ParticleTypes.ENTITY_EFFECT);

        this.level.addFreshEntity(areaEffectCloud);
    }

    private void initialEffect(HitResult result) {
        if (!this.level.isClientSide()) {
            Entity entity = result.getType() == HitResult.Type.ENTITY ? ((EntityHitResult)result).getEntity() : null;

            AABB axisAlignedBB = this.getBoundingBox().inflate(4, 2, 4);
            List<LivingEntity> livingEntities = this.level.getEntitiesOfClass(LivingEntity.class, axisAlignedBB);

            if (!livingEntities.isEmpty()) {
                for (LivingEntity livingEntity : livingEntities) {
                    double distanceSq = this.distanceToSqr(livingEntity);
                    if (distanceSq < 16) {
                        double durationMultiplier = 1 - Math.sqrt(distanceSq) / 4;
                        if (livingEntity == entity) {
                            durationMultiplier = 1;
                        }

                        int duration = (int) (durationMultiplier * 300 + 0.5);
                        livingEntity.addEffect(new MobEffectInstance(FromSporesMobEffects.SPORING.get(), duration));
                    }
                }
            }
        }
    }
}
