package com.linnett.hidden_caves.common.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class GrasshopperEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState drowningAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int jumpCooldown = 0;

    private static final float ATTACK_DAMAGE = 5.0F;

    public GrasshopperEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.7F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 15D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 640;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }


    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 10F, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.3f);
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.jumpCooldown <= 0 && this.random.nextFloat() < 0.01F) {
            this.jump();
            this.jumpCooldown = 100;
        } else {
            --this.jumpCooldown;
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    private void jump() {
        double jumpStrength = 1.5;
        double forwardStrength = 1.0;

        double dx = this.getLookAngle().x * forwardStrength;
        double dz = this.getLookAngle().z * forwardStrength;
        double dy = jumpStrength;

        this.setDeltaMovement(this.getDeltaMovement().add(dx, dy, dz));
        this.hasImpulse = true;
    }

    private void applyDrowningEffect() {
        this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 0, 0.8));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNIFFER_DEATH;
    }

    @Override
    public void die(DamageSource pSource) {
        super.die(pSource);

        int numPrismarine = this.random.nextInt(2) + 1;

        for (int i = 0; i < numPrismarine; i++) {
            ItemStack prismarineStack = new ItemStack(Items.PRISMARINE_CRYSTALS);
            ItemEntity itemEntity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), prismarineStack);
            this.level().addFreshEntity(itemEntity);
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean damage = super.hurt(pSource, pAmount);
        if (damage && pSource.getEntity() instanceof Player) {
            Player attacker = (Player) pSource.getEntity();

            this.setAggressive(true);
            this.setTarget(attacker);

            if (this.getNavigation() != null && attacker != null) {
                this.getNavigation().moveTo(attacker, 1.5);
            }

            for (GrasshopperEntity nearbyGrasshopper : this.level().getEntitiesOfClass(GrasshopperEntity.class, this.getBoundingBox().inflate(10.0))) {
                if (nearbyGrasshopper != this && !nearbyGrasshopper.isAlliedTo(attacker)) {
                    nearbyGrasshopper.setTarget(attacker);
                }
            }
        }

        return damage;
    }

    public void attack(Entity target) {
        if (target instanceof Player) {
            target.hurt(this.damageSources().mobAttack(this), ATTACK_DAMAGE);
            this.swing(InteractionHand.MAIN_HAND);

            // Включаем анимацию атаки
            this.attackAnimationState.start(this.tickCount);
        }
    }

    private void attackPlayer(Player player) {
        this.setTarget(player);
        this.getNavigation().moveTo(player, 1.0);
    }

    private void avoidPlayer(Player player) {
        double xDiff = this.getX() - player.getX();
        double zDiff = this.getZ() - player.getZ();
        double distance = Math.sqrt(xDiff * xDiff + zDiff * zDiff);

        double jumpStrength = 0.5;

        this.setDeltaMovement(this.getDeltaMovement().add(xDiff / distance * jumpStrength, 0.5, zDiff / distance * jumpStrength));
        this.hasImpulse = true;
    }
}
