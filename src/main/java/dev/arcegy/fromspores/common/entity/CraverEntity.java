package dev.arcegy.fromspores.common.entity;

import dev.arcegy.fromspores.core.registry.FromSporesMobEffects;
import dev.arcegy.fromspores.core.registry.FromSporesSoundEvents;
import dev.arcegy.fromspores.core.tags.FromSporesBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CraverEntity extends Monster implements NeutralMob {
    public int targetChangeTime;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;
    public CraverEntity(EntityType<? extends Monster> craver, Level world) {
        super(craver, world);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 35.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.ATTACK_DAMAGE, 1.5F)
                .add(Attributes.ATTACK_SPEED, 0.3F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.35F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Hoglin.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Zoglin.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractPiglin.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Pig.class, true));
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static boolean checkCraverSpawnRules(EntityType<CraverEntity> craver, LevelAccessor world, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return world.getBlockState((pos.below())).is(FromSporesBlockTags.CRAVERS_SPAWNABLE_ON);
    }

    public void setTarget(@Nullable LivingEntity entity) {
        if (entity == null) {
            this.targetChangeTime = 0;
        }
        else {
            this.targetChangeTime = this.tickCount;
        }
        super.setTarget(entity);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        }
        else {
            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).addEffect(new MobEffectInstance(FromSporesMobEffects.SPORING.get(), 200), this);
            }
            return true;
        }
    }

    @Override
    public boolean canBeAffected(MobEffectInstance mobEffectInstance) {
        return mobEffectInstance.getEffect() == FromSporesMobEffects.SPORING.get() ? false : super.canBeAffected(mobEffectInstance);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return  this.remainingPersistentAngerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        this.remainingPersistentAngerTime = time;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@Nullable UUID target) {
        this.persistentAngerTarget = target;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FromSporesSoundEvents.CRAVER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return FromSporesSoundEvents.CRAVER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FromSporesSoundEvents.CRAVER_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.13F;
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public void aiStep() {
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }
        super.aiStep();
    }
}
