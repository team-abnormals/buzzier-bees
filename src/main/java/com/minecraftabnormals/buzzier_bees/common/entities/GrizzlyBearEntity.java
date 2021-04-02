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
	private static final DataParameter<Boolean> IS_STANDING = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SITTING = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SLEEPING = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);

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

	public boolean isBreedingItem(ItemStack stack) {
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
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 30.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	protected SoundEvent getAmbientSound() {
		return this.isChild() ? SoundEvents.ENTITY_POLAR_BEAR_AMBIENT_BABY : SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_POLAR_BEAR_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
	}

	public void playWarningSound() {
		if (this.warningSoundTicks <= 0) {
			this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, this.getSoundPitch());
			this.warningSoundTicks = 40;
		}

	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(IS_STANDING, false);
		this.dataManager.register(IS_SLEEPING, false);
		this.dataManager.register(IS_SITTING, false);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
		return BBEntities.GRIZZLY_BEAR.get().create(world);
	}

	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
//		ItemStack itemstack = player.getHeldItem(hand);
//		Item item = itemstack.getItem();
//		if (item == Items.SWEET_BERRIES) {
//			if(!this.isSitting()) this.setSitting(true);
//			if(!this.world.isRemote && !this.isEndimationPlaying(SIT_DOWN)) {
//				NetworkUtil.setPlayingAnimationMessage(this, SIT_DOWN);
//			}
//		}
		return super.func_230254_b_(player, hand);
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

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
		if (flag) {
			this.applyEnchantments(this, entityIn);
		}

		return flag;
	}

	public boolean isStanding() {
		return this.dataManager.get(IS_STANDING);
	}

	public void setStanding(boolean standing) {
		this.dataManager.set(IS_STANDING, standing);
		if (standing && !this.world.isRemote && !this.isEndimationPlaying(ATTACK)) {
			NetworkUtil.setPlayingAnimationMessage(this, ATTACK);
		}
	}

	public boolean isSleeping() {
		return this.dataManager.get(IS_SLEEPING);
	}

	public void setSleeping(boolean standing) {
		this.dataManager.set(IS_SLEEPING, standing);
	}

	public boolean isSitting() {
		return this.dataManager.get(IS_SITTING);
	}

	public void setSitting(boolean sitting) {
		this.dataManager.set(IS_SITTING, sitting);
	}

	protected float getWaterSlowDown() {
		return 0.98F;
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if (spawnDataIn == null) {
			spawnDataIn = new AgeableEntity.AgeableData(1.0F);
		}

		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public abstract class BaseGoal extends Goal {
		private final EntityPredicate field_220816_b = (new EntityPredicate()).setDistance(12.0D).setLineOfSiteRequired().setCustomPredicate(new AlertablePredicate());

		private BaseGoal() {
		}

		protected boolean func_220813_g() {
			BlockPos blockpos = new BlockPos(GrizzlyBearEntity.this.getPositionVec());
			return !GrizzlyBearEntity.this.world.canSeeSky(blockpos) && GrizzlyBearEntity.this.getBlockPathWeight(blockpos) >= 0.0F;
		}

		protected boolean func_220814_h() {
			return !GrizzlyBearEntity.this.world.getTargettableEntitiesWithinAABB(LivingEntity.class, this.field_220816_b, GrizzlyBearEntity.this, GrizzlyBearEntity.this.getBoundingBox().grow(12.0D, 6.0D, 12.0D)).isEmpty();
		}
	}

	class SleepGoal extends GrizzlyBearEntity.BaseGoal {
		private int field_220825_c = GrizzlyBearEntity.this.rand.nextInt(140);

		public SleepGoal() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
		}

		public boolean shouldExecute() {
			if (GrizzlyBearEntity.this.moveStrafing == 0.0F && GrizzlyBearEntity.this.moveVertical == 0.0F && GrizzlyBearEntity.this.moveForward == 0.0F) {
				return this.func_220823_j() || GrizzlyBearEntity.this.isSleeping();
			} else {
				return false;
			}
		}

		public boolean shouldContinueExecuting() {
			return this.func_220823_j();
		}

		private boolean func_220823_j() {
			if (this.field_220825_c > 0) {
				--this.field_220825_c;
				return false;
			} else {
				return GrizzlyBearEntity.this.world.isDaytime() && this.func_220813_g() && !this.func_220814_h();
			}
		}

		public void resetTask() {
			this.field_220825_c = GrizzlyBearEntity.this.rand.nextInt(140);
		}

		public void startExecuting() {
			GrizzlyBearEntity.this.setJumping(false);
			GrizzlyBearEntity.this.setSleeping(true);
			GrizzlyBearEntity.this.getNavigator().clearPath();
			GrizzlyBearEntity.this.getMoveHelper().setMoveTo(GrizzlyBearEntity.this.getPosX(), GrizzlyBearEntity.this.getPosY(), GrizzlyBearEntity.this.getPosZ(), 0.0D);
		}
	}

	public static class AlertablePredicate implements Predicate<LivingEntity> {
		public boolean test(LivingEntity p_test_1_) {
			if (p_test_1_ instanceof FoxEntity) {
				return false;
			} else if (!(p_test_1_ instanceof ChickenEntity) && !(p_test_1_ instanceof RabbitEntity) && !(p_test_1_ instanceof MonsterEntity)) {
				if (p_test_1_ instanceof TameableEntity) {
					return !((TameableEntity) p_test_1_).isTamed();
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
			if (distToEnemySqr <= d0 && this.func_234040_h_()) {
				this.func_234039_g_();
				this.attacker.attackEntityAsMob(enemy);
				GrizzlyBearEntity.this.setStanding(false);
			} else if (distToEnemySqr <= d0 * 2.0D) {
				if (this.func_234040_h_()) {
					GrizzlyBearEntity.this.setStanding(false);
					this.func_234039_g_();
				}

				if (this.func_234041_j_() <= 10) {
					GrizzlyBearEntity.this.setStanding(true);
					GrizzlyBearEntity.this.playWarningSound();
				}
			} else {
				this.func_234039_g_();
				GrizzlyBearEntity.this.setStanding(false);
			}

		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void resetTask() {
			GrizzlyBearEntity.this.setStanding(false);
			super.resetTask();
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getWidth());
		}
	}
}
