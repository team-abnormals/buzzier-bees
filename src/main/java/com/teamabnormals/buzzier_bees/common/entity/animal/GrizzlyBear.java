package com.teamabnormals.buzzier_bees.common.entity.animal;

import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearAttackPlayerGoal;
import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearHurtByTargetGoal;
import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearPanicGoal;
import com.teamabnormals.buzzier_bees.core.other.tags.BBItemTags;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class GrizzlyBear extends Animal {
	private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(GrizzlyBear.class, EntityDataSerializers.BYTE);

	private static final EntityDataAccessor<Boolean> HONEY = SynchedEntityData.defineId(GrizzlyBear.class, EntityDataSerializers.BOOLEAN);
	public static final Predicate<ItemEntity> ALLOWED_ITEMS = (itemEntity) -> !itemEntity.hasPickUpDelay() && itemEntity.isAlive();

	public int eatTicks;

	public GrizzlyBear(EntityType<? extends GrizzlyBear> type, Level worldIn) {
		super(type, worldIn);
		this.lookControl = new GrizzlyBearLookControl();
		this.moveControl = new GrizzlyMoveControl();
		this.setCanPickUpLoot(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new BearPanicGoal(this));
		this.goalSelector.addGoal(2, new SeekShelterGoal(1.25D));
		this.goalSelector.addGoal(3, new GrizzlyMeleeAttackGoal(1.0D, false));
		this.goalSelector.addGoal(4, new SleepGoal());
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		// this.goalSelector.addGoal(8, new BearFindItemsGoal(this));

		this.targetSelector.addGoal(1, new BearHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new BearAttackPlayerGoal<>(this, LivingEntity.class, 10, true, true, living -> living instanceof Player || living instanceof Monster));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Fox.class, 10, true, true, null));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	public boolean isFood(ItemStack stack) {
		return stack.is(BBItemTags.GRIZZLY_BEAR_FOOD);
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

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HONEY, false);
		this.entityData.define(DATA_FLAGS_ID, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("Honey", this.isHoney());
		tag.putBoolean("Sleeping", this.isSleeping());

	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setHoney(tag.getBoolean("Honey"));
		this.setSleeping(tag.getBoolean("Sleeping"));
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable) {
		return BBEntityTypes.GRIZZLY_BEAR.get().create(world);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (this.isHoney() && stack.is(Items.GLASS_BOTTLE)) {
			stack.shrink(1);
			this.level().playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
			if (stack.isEmpty()) {
				player.setItemInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
			} else if (!player.getInventory().add(new ItemStack(Items.HONEY_BOTTLE))) {
				player.drop(new ItemStack(Items.HONEY_BOTTLE), false);
			}

			this.level().gameEvent(player, GameEvent.FLUID_PICKUP, this.position());
		}

		return super.mobInteract(player, hand);
	}

	private boolean canEatItem(ItemStack stack) {
		return stack.isEdible() && this.getTarget() == null && this.onGround();
	}

	@Override
	public boolean canTakeItem(ItemStack stack) {
		EquipmentSlot slot = Mob.getEquipmentSlotForItem(stack);
		if (!this.getItemBySlot(slot).isEmpty()) {
			return false;
		} else {
			return slot == EquipmentSlot.MAINHAND && super.canTakeItem(stack);
		}
	}

	@Override
	public boolean canHoldItem(ItemStack stack) {
		Item item = stack.getItem();
		ItemStack heldItem = this.getItemBySlot(EquipmentSlot.MAINHAND);
		return heldItem.isEmpty() || this.eatTicks > 0 && item.isEdible() && !heldItem.getItem().isEdible();
	}

	private void spitOutItem(ItemStack stack) {
		if (!stack.isEmpty() && !this.level().isClientSide) {
			ItemEntity entity = new ItemEntity(this.level(), this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, stack);
			entity.setPickUpDelay(40);
			entity.setThrower(this.getUUID());
			this.playSound(SoundEvents.FOX_SPIT, 1.0F, 1.0F);
			this.level().addFreshEntity(entity);
		}
	}

	private void spawnItem(ItemStack stack) {
		ItemEntity entity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), stack);
		this.level().addFreshEntity(entity);
	}

	@Override
	protected void pickUpItem(ItemEntity entity) {
		ItemStack stack = entity.getItem();
		if (this.canHoldItem(stack)) {
			int i = stack.getCount();
			if (i > 1) {
				this.spawnItem(stack.split(i - 1));
			}

			this.spitOutItem(this.getItemBySlot(EquipmentSlot.MAINHAND));
			this.onItemPickup(entity);
			this.setItemSlot(EquipmentSlot.MAINHAND, stack.split(1));
			this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
			this.take(entity, stack.getCount());
			entity.discard();
			this.eatTicks = 0;
		}

	}

	public void aiStep() {
		if (!this.level().isClientSide && this.isAlive() && this.isEffectiveAi()) {
			++this.eatTicks;
			ItemStack stack = this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (this.canEatItem(stack)) {
				if (this.eatTicks > 600) {
					ItemStack returnStack = stack.finishUsingItem(this.level(), this);
					if (!returnStack.isEmpty()) {
						this.setItemSlot(EquipmentSlot.MAINHAND, returnStack);
					}

					this.eatTicks = 0;
				} else if (this.eatTicks > 500 && this.random.nextFloat() < 0.1F) {
					this.playSound(this.getEatingSound(stack), 1.0F, 1.0F);
					this.level().broadcastEntityEvent(this, (byte) 45);
				}
			}
		}

		super.aiStep();
	}

	public boolean isHoney() {
		return this.entityData.get(HONEY);
	}

	public void setHoney(boolean honey) {
		this.entityData.set(HONEY, honey);
	}

	public boolean isSleeping() {
		return this.getFlag(32);
	}

	public void setSleeping(boolean sleeping) {
		this.setFlag(32, sleeping);
	}

	private void setFlag(int flag, boolean value) {
		if (value) {
			this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) | flag));
		} else {
			this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) & ~flag));
		}

	}

	private boolean getFlag(int p_28609_) {
		return (this.entityData.get(DATA_FLAGS_ID) & p_28609_) != 0;
	}

	public void clearStates() {
		this.setSleeping(false);
	}

	public void tick() {
		super.tick();
		if (this.isEffectiveAi()) {
			boolean flag = this.isInWater();
			if (flag || this.getTarget() != null || this.level().isThundering()) {
				this.setSleeping(false);
			}
		}
	}

	public boolean doHurtTarget(Entity target) {
		boolean flag = target.hurt(this.level().damageSources().mobAttack(this), (float) ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
		if (flag) {
			this.doEnchantDamageEffects(this, target);
		}

		return flag;
	}

	boolean canMove() {
		return !this.isSleeping();
	}

	protected float getWaterSlowDown() {
		return 0.98F;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
		if (spawnData == null) {
			spawnData = new AgeableMob.AgeableMobGroupData(1.0F);
		}

		return super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);
	}

	abstract class GrizzlyBehaviorGoal extends Goal {
		private final TargetingConditions alertableTargeting = TargetingConditions.forCombat().range(16.0D).ignoreLineOfSight().selector(new GrizzlyBearAlertableEntitiesSelector());

		protected boolean hasShelter() {
			BlockPos blockpos = BlockPos.containing(GrizzlyBear.this.getX(), GrizzlyBear.this.getBoundingBox().maxY, GrizzlyBear.this.getZ());
			return !GrizzlyBear.this.level().canSeeSky(blockpos) && GrizzlyBear.this.getWalkTargetValue(blockpos) >= 0.0F;
		}

		protected boolean alertable() {
			return !GrizzlyBear.this.level().getNearbyEntities(LivingEntity.class, this.alertableTargeting, GrizzlyBear.this, GrizzlyBear.this.getBoundingBox().inflate(12.0D, 6.0D, 12.0D)).isEmpty();
		}
	}


	public static class GrizzlyBearAlertableEntitiesSelector implements Predicate<LivingEntity> {
		public boolean test(LivingEntity entity) {
			if (entity instanceof GrizzlyBear) {
				return false;
			} else if (!(entity instanceof Chicken) && !(entity instanceof Rabbit)) {
				if (entity instanceof TamableAnimal) {
					return !((TamableAnimal) entity).isTame();
				} else if (!(entity instanceof Player) || !entity.isSpectator() && !((Player) entity).isCreative()) {
					return !entity.isSleeping() && !entity.isDiscrete();
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}

	public class GrizzlyBearLookControl extends LookControl {
		public GrizzlyBearLookControl() {
			super(GrizzlyBear.this);
		}

		public void tick() {
			if (!GrizzlyBear.this.isSleeping()) {
				super.tick();
			}
		}
	}

	class GrizzlyMeleeAttackGoal extends MeleeAttackGoal {
		public GrizzlyMeleeAttackGoal(double p_28720_, boolean p_28721_) {
			super(GrizzlyBear.this, p_28720_, p_28721_);
		}

		public boolean canUse() {
			return !GrizzlyBear.this.isSleeping() && super.canUse();
		}
	}

	class GrizzlyMoveControl extends MoveControl {
		public GrizzlyMoveControl() {
			super(GrizzlyBear.this);
		}

		public void tick() {
			if (GrizzlyBear.this.canMove()) {
				super.tick();
			}

		}
	}

	class GrizzlyLookAtPlayerGoal extends LookAtPlayerGoal {

		public GrizzlyLookAtPlayerGoal(Mob p_25520_, Class<? extends LivingEntity> p_25521_, float p_25522_) {
			super(p_25520_, p_25521_, p_25522_);
		}

		public boolean canContinueToUse() {
			if (!GrizzlyBear.this.canMove()) {
				return false;
			}

			return super.canContinueToUse();
		}
	}

	class SeekShelterGoal extends FleeSunGoal {
		private int interval = reducedTickDelay(100);

		public SeekShelterGoal(double p_28777_) {
			super(GrizzlyBear.this, p_28777_);
		}

		public boolean canUse() {
			if (!GrizzlyBear.this.isSleeping() && this.mob.getTarget() == null) {
				if (GrizzlyBear.this.level().isThundering() && GrizzlyBear.this.level().canSeeSky(this.mob.blockPosition())) {
					return this.setWantedPos();
				} else if (this.interval > 0) {
					--this.interval;
					return false;
				} else {
					this.interval = 100;
					BlockPos blockpos = this.mob.blockPosition();
					return GrizzlyBear.this.level().isDay() && GrizzlyBear.this.level().canSeeSky(blockpos) && !((ServerLevel) GrizzlyBear.this.level()).isVillage(blockpos) && this.setWantedPos();
				}
			} else {
				return false;
			}
		}

		public void start() {
			GrizzlyBear.this.clearStates();
			super.start();
		}
	}

	class SleepGoal extends GrizzlyBehaviorGoal {
		private static final int WAIT_TIME_BEFORE_SLEEP = reducedTickDelay(140);
		private int countdown = GrizzlyBear.this.random.nextInt(WAIT_TIME_BEFORE_SLEEP);

		public SleepGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
		}

		public boolean canUse() {
			if (GrizzlyBear.this.xxa == 0.0F && GrizzlyBear.this.yya == 0.0F && GrizzlyBear.this.zza == 0.0F) {
				return this.canSleep() || GrizzlyBear.this.isSleeping();
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
				return GrizzlyBear.this.level().isDay() && this.hasShelter() && !this.alertable() && !GrizzlyBear.this.isInPowderSnow;
			}
		}

		public void stop() {
			this.countdown = GrizzlyBear.this.random.nextInt(WAIT_TIME_BEFORE_SLEEP);
			GrizzlyBear.this.clearStates();
		}

		public void start() {
			GrizzlyBear.this.setJumping(false);
			GrizzlyBear.this.setSleeping(true);
			GrizzlyBear.this.getNavigation().stop();
			GrizzlyBear.this.getMoveControl().setWantedPosition(GrizzlyBear.this.getX(), GrizzlyBear.this.getY(), GrizzlyBear.this.getZ(), 0.0D);
		}
	}
}
