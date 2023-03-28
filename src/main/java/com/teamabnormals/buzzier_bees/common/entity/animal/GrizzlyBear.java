package com.teamabnormals.buzzier_bees.common.entity.animal;

import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearAttackPlayerGoal;
import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearHurtByTargetGoal;
import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearMeleeAttackGoal;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class GrizzlyBear extends Animal {
	private static final EntityDataAccessor<Boolean> EATING = SynchedEntityData.defineId(GrizzlyBear.class, EntityDataSerializers.BOOLEAN);
	private static final Predicate<ItemEntity> TRUSTED_TARGET_SELECTOR = (entity) -> !entity.hasPickUpDelay() && entity.isAlive();

	private int warningSoundTicks;
	public int eatTicks;

	public GrizzlyBear(EntityType<? extends GrizzlyBear> type, Level worldIn) {
		super(type, worldIn);
		this.setCanPickUpLoot(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new BearMeleeAttackGoal(this));
		this.goalSelector.addGoal(1, new BearPanicGoal(this));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new GrizzlyBear.FindItemsGoal());
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new BearHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new BearAttackPlayerGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Fox.class, 10, true, true, null));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.FOLLOW_RANGE, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D);
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

	public void playWarningSound() {
		if (this.warningSoundTicks <= 0) {
			this.playSound(SoundEvents.POLAR_BEAR_WARNING, 1.0F, this.getVoicePitch());
			this.warningSoundTicks = 40;
		}
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(EATING, false);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable) {
		return BBEntityTypes.GRIZZLY_BEAR.get().create(world);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() == Items.NAUTILUS_SHELL)
			this.setEating(true);
		else if (stack.getItem() == Items.HEART_OF_THE_SEA)
			this.setEating(false);
		return super.mobInteract(player, hand);
	}

	private boolean canEatItem(ItemStack itemStackIn) {
		return itemStackIn.getItem().isEdible() && this.getTarget() == null && this.onGround;
	}

	@Override
	public boolean canTakeItem(ItemStack itemstackIn) {
		EquipmentSlot equipmentslottype = Mob.getEquipmentSlotForItem(itemstackIn);
		if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
			return false;
		} else {
			return equipmentslottype == EquipmentSlot.MAINHAND && super.canTakeItem(itemstackIn);
		}
	}

	@Override
	public boolean canHoldItem(ItemStack stack) {
		Item item = stack.getItem();
		ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
		return itemstack.isEmpty() || this.eatTicks > 0 && item.isEdible() && !itemstack.getItem().isEdible();
	}

	private void spitOutItem(ItemStack stackIn) {
		if (!stackIn.isEmpty() && !this.level.isClientSide) {
			ItemEntity itementity = new ItemEntity(this.level, this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, stackIn);
			itementity.setPickUpDelay(40);
			itementity.setThrower(this.getUUID());
			this.playSound(SoundEvents.FOX_SPIT, 1.0F, 1.0F);
			this.level.addFreshEntity(itementity);
		}
	}

	private void spawnItem(ItemStack stackIn) {
		ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), stackIn);
		this.level.addFreshEntity(itementity);
	}

	@Override
	protected void pickUpItem(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (this.canHoldItem(itemstack)) {
			int i = itemstack.getCount();
			if (i > 1) {
				this.spawnItem(itemstack.split(i - 1));
			}

			this.spitOutItem(this.getItemBySlot(EquipmentSlot.MAINHAND));
			this.onItemPickup(itemEntity);
			this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
			this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
			this.take(itemEntity, itemstack.getCount());
			itemEntity.discard();
			this.eatTicks = 0;
		}

	}

	public void aiStep() {
		if (!this.level.isClientSide && this.isAlive() && this.isEffectiveAi()) {
			++this.eatTicks;
			ItemStack stack = this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (this.canEatItem(stack)) {
				if (this.eatTicks > 600) {
					ItemStack returnStack = stack.finishUsingItem(this.level, this);
					if (!returnStack.isEmpty()) {
						this.setItemSlot(EquipmentSlot.MAINHAND, returnStack);
					}

					this.eatTicks = 0;
					this.setEating(false);
				} else if (this.eatTicks > 560 && this.random.nextFloat() < 0.1F) {
					this.setEating(true);
					this.playSound(this.getEatingSound(stack), 1.0F, 1.0F);
					this.level.broadcastEntityEvent(this, (byte) 45);
				}
			}
		}

		super.aiStep();
	}

	public boolean isEating() {
		return this.entityData.get(EATING);
	}

	public void setEating(boolean eating) {
		this.entityData.set(EATING, eating);
	}

	public void tick() {
		super.tick();
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

	protected float getWaterSlowDown() {
		return 0.98F;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		if (spawnDataIn == null) {
			spawnDataIn = new AgeableMob.AgeableMobGroupData(1.0F);
		}

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	class FindItemsGoal extends Goal {
		public FindItemsGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			if (!GrizzlyBear.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
				return false;
			} else if (GrizzlyBear.this.getTarget() == null && GrizzlyBear.this.getLastHurtByMob() == null) {
				if (GrizzlyBear.this.getRandom().nextInt(10) != 0) {
					return false;
				} else {
					List<ItemEntity> list = GrizzlyBear.this.level.getEntitiesOfClass(ItemEntity.class, GrizzlyBear.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBear.TRUSTED_TARGET_SELECTOR);
					return !list.isEmpty() && GrizzlyBear.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
				}
			} else {
				return false;
			}
		}

		public void tick() {
			List<ItemEntity> list = GrizzlyBear.this.level.getEntitiesOfClass(ItemEntity.class, GrizzlyBear.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBear.TRUSTED_TARGET_SELECTOR);
			ItemStack itemstack = GrizzlyBear.this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (itemstack.isEmpty() && !list.isEmpty()) {
				GrizzlyBear.this.getNavigation().moveTo(list.get(0), 1.2F);
			}

		}

		public void start() {
			List<ItemEntity> list = GrizzlyBear.this.level.getEntitiesOfClass(ItemEntity.class, GrizzlyBear.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBear.TRUSTED_TARGET_SELECTOR);
			if (!list.isEmpty()) {
				GrizzlyBear.this.getNavigation().moveTo(list.get(0), 1.2F);
			}

		}
	}
}
