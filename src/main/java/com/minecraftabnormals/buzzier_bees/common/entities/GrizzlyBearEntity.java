package com.minecraftabnormals.buzzier_bees.common.entities;

import com.minecraftabnormals.abnormals_core.core.endimator.ControlledEndimation;
import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.abnormals_core.core.endimator.entity.IEndimatedEntity;
import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.AttackPlayerGoal;
import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.EatBerriesGoal;
import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.HurtByTargetGoal;
import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.PanicGoal;
import com.minecraftabnormals.buzzier_bees.core.registry.BBEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class GrizzlyBearEntity extends AnimalEntity implements IEndimatedEntity {
	private static final DataParameter<Boolean> IS_STANDING = EntityDataManager.defineId(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SITTING = EntityDataManager.defineId(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SLEEPING = EntityDataManager.defineId(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);

	private int warningSoundTicks;
	private int animationTick;

	private Endimation playingEndimation = BLANK_ANIMATION;

	public static final Endimation ATTACK = new Endimation(20);
	public static final Endimation SLEEP_DOWN = new Endimation(40);
	public static final Endimation SIT_DOWN = new Endimation(20);
	public static final ControlledEndimation BREATHING = new ControlledEndimation(8, 0);

	public GrizzlyBearEntity(EntityType<? extends GrizzlyBearEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public boolean isFood(ItemStack stack) {
		return false;
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal());
		this.goalSelector.addGoal(1, new PanicGoal(this));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new GrizzlyBearEntity.SleepGoal());
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(10, new EatBerriesGoal(this, 1.2F, 12, 2));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new AttackPlayerGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FoxEntity.class, 10, true, true, null));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.FOLLOW_RANGE, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	protected SoundEvent getAmbientSound() {
		return this.isBaby() ? SoundEvents.POLAR_BEAR_AMBIENT_BABY : SoundEvents.POLAR_BEAR_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.POLAR_BEAR_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.POLAR_BEAR_DEATH;
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.POLAR_BEAR_STEP, 0.15F, 1.0F);
	}

	public void playWarningSound() {
		if (this.warningSoundTicks <= 0) {
			this.playSound(SoundEvents.POLAR_BEAR_WARNING, 1.0F, this.getVoicePitch());
			this.warningSoundTicks = 40;
		}

	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_STANDING, false);
		this.entityData.define(IS_SLEEPING, false);
		this.entityData.define(IS_SITTING, false);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
		return BBEntities.GRIZZLY_BEAR.get().create(world);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
//		ItemStack itemstack = player.getHeldItem(hand);
//		Item item = itemstack.getItem();
//		if (item == Items.SWEET_BERRIES) {
//			if(!this.isSitting()) this.setSitting(true);
//			if(!this.world.isRemote && !this.isEndimationPlaying(SIT_DOWN)) {
//				NetworkUtil.setPlayingAnimationMessage(this, SIT_DOWN);
//			}
//		}
		return super.mobInteract(player, hand);
	}

	public void tick() {
		super.tick();
		this.endimateTick();
//      if(!this.world.isRemote && !this.isEndimationPlaying(SLEEP_DOWN)) {
//    	  NetworkUtil.setPlayingAnimationMessage(this, SLEEP_DOWN);
//      }

		if (this.warningSoundTicks > 0) {
			--this.warningSoundTicks;
		}

	}

	public boolean doHurtTarget(Entity entityIn) {
		boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
		if (flag) {
			this.doEnchantDamageEffects(this, entityIn);
		}

		return flag;
	}

	public boolean isStanding() {
		return this.entityData.get(IS_STANDING);
	}

	public void setStanding(boolean standing) {
		this.entityData.set(IS_STANDING, standing);
		if (standing && !this.level.isClientSide && !this.isEndimationPlaying(ATTACK)) {
			NetworkUtil.setPlayingAnimationMessage(this, ATTACK);
		}
	}

	public boolean isSleeping() {
		return this.entityData.get(IS_SLEEPING);
	}

	public void setSleeping(boolean standing) {
		this.entityData.set(IS_SLEEPING, standing);
	}

	public boolean isSitting() {
		return this.entityData.get(IS_SITTING);
	}

	public void setSitting(boolean sitting) {
		this.entityData.set(IS_SITTING, sitting);
	}

	protected float getWaterSlowDown() {
		return 0.98F;
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if (spawnDataIn == null) {
			spawnDataIn = new AgeableEntity.AgeableData(1.0F);
		}

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public abstract class BaseGoal extends Goal {
		private final EntityPredicate alertableTargeting = (new EntityPredicate()).range(12.0D).allowUnseeable().selector(new AlertablePredicate());

		private BaseGoal() {
		}

		protected boolean hasShelter() {
			BlockPos blockpos = new BlockPos(GrizzlyBearEntity.this.position());
			return !GrizzlyBearEntity.this.level.canSeeSky(blockpos) && GrizzlyBearEntity.this.getWalkTargetValue(blockpos) >= 0.0F;
		}

		protected boolean alertable() {
			return !GrizzlyBearEntity.this.level.getNearbyEntities(LivingEntity.class, this.alertableTargeting, GrizzlyBearEntity.this, GrizzlyBearEntity.this.getBoundingBox().inflate(12.0D, 6.0D, 12.0D)).isEmpty();
		}
	}

	class SleepGoal extends GrizzlyBearEntity.BaseGoal {
		private int countdown = GrizzlyBearEntity.this.random.nextInt(140);

		public SleepGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
		}

		public boolean canUse() {
			if (GrizzlyBearEntity.this.xxa == 0.0F && GrizzlyBearEntity.this.yya == 0.0F && GrizzlyBearEntity.this.zza == 0.0F) {
				return this.canSleep() || GrizzlyBearEntity.this.isSleeping();
			} else {
				return false;
			}
		}

		public boolean canContinueToUse() {
			return this.canSleep();
		}

		private boolean canSleep() {
			if (this.countdown > 0) {
				--this.countdown;
				return false;
			} else {
				return GrizzlyBearEntity.this.level.isDay() && this.hasShelter() && !this.alertable();
			}
		}

		public void stop() {
			this.countdown = GrizzlyBearEntity.this.random.nextInt(140);
		}

		public void start() {
			GrizzlyBearEntity.this.setJumping(false);
			GrizzlyBearEntity.this.setSleeping(true);
			GrizzlyBearEntity.this.getNavigation().stop();
			GrizzlyBearEntity.this.getMoveControl().setWantedPosition(GrizzlyBearEntity.this.getX(), GrizzlyBearEntity.this.getY(), GrizzlyBearEntity.this.getZ(), 0.0D);
		}
	}

	public static class AlertablePredicate implements Predicate<LivingEntity> {
		public boolean test(LivingEntity p_test_1_) {
			if (p_test_1_ instanceof FoxEntity) {
				return false;
			} else if (!(p_test_1_ instanceof ChickenEntity) && !(p_test_1_ instanceof RabbitEntity) && !(p_test_1_ instanceof MonsterEntity)) {
				if (p_test_1_ instanceof TameableEntity) {
					return !((TameableEntity) p_test_1_).isTame();
				} else if (!(p_test_1_ instanceof PlayerEntity) || !p_test_1_.isSpectator() && !((PlayerEntity) p_test_1_).isCreative()) {
					return !p_test_1_.isSleeping() && !p_test_1_.isDiscrete();
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}

	@Override
	public Endimation[] getEndimations() {
		return new Endimation[]{
				SLEEP_DOWN,
				ATTACK,
				SIT_DOWN
		};
	}

	@Override
	public Endimation getPlayingEndimation() {
		return this.playingEndimation;
	}

	@Override
	public int getAnimationTick() {
		return this.animationTick;
	}

	@Override
	public void setAnimationTick(int animationTick) {
		this.animationTick = animationTick;
	}

	@Override
	public void setPlayingEndimation(Endimation endimationToPlay) {
		this.onEndimationEnd(this.playingEndimation);
		this.playingEndimation = endimationToPlay;
		this.setAnimationTick(0);
	}

	class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {

		public MeleeAttackGoal() {
			super(GrizzlyBearEntity.this, 1.25D, true);
		}

		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.isTimeToAttack()) {
				this.resetAttackCooldown();
				this.mob.doHurtTarget(enemy);
				GrizzlyBearEntity.this.setStanding(false);
			} else if (distToEnemySqr <= d0 * 2.0D) {
				if (this.isTimeToAttack()) {
					GrizzlyBearEntity.this.setStanding(false);
					this.resetAttackCooldown();
				}

				if (this.getTicksUntilNextAttack() <= 10) {
					GrizzlyBearEntity.this.setStanding(true);
					GrizzlyBearEntity.this.playWarningSound();
				}
			} else {
				this.resetAttackCooldown();
				GrizzlyBearEntity.this.setStanding(false);
			}

		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void stop() {
			GrizzlyBearEntity.this.setStanding(false);
			super.stop();
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getBbWidth());
		}
	}
}
