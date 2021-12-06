package com.minecraftabnormals.buzzier_bees.common.entities;

import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.BearAttackPlayerGoal;
import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.BearHurtByTargetGoal;
import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.BearMeleeAttackGoal;
import com.minecraftabnormals.buzzier_bees.common.entities.goals.bear.BearPanicGoal;
import com.minecraftabnormals.buzzier_bees.core.other.tags.BBItemTags;
import com.minecraftabnormals.buzzier_bees.core.registry.BBEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import java.util.List;
import java.util.function.Predicate;

public class GrizzlyBearEntity extends AnimalEntity {
	private static final DataParameter<Boolean> EATING = EntityDataManager.defineId(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);
	private static final Predicate<ItemEntity> TRUSTED_TARGET_SELECTOR = (entity) -> !entity.hasPickUpDelay() && entity.isAlive();

	private int warningSoundTicks;
	public int eatTicks;

	public GrizzlyBearEntity(EntityType<? extends GrizzlyBearEntity> type, World worldIn) {
		super(type, worldIn);
		this.setCanPickUpLoot(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BearMeleeAttackGoal(this));
		this.goalSelector.addGoal(1, new BearPanicGoal(this));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new GrizzlyBearEntity.FindItemsGoal());
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new BearHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new BearAttackPlayerGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FoxEntity.class, 10, true, true, null));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.FOLLOW_RANGE, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	public boolean isFood(ItemStack stack) {
		return stack.getItem().is(BBItemTags.GRIZZLY_BEAR_FOOD);
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
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
		return BBEntities.GRIZZLY_BEAR.get().create(world);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
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
		EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(itemstackIn);
		if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
			return false;
		} else {
			return equipmentslottype == EquipmentSlotType.MAINHAND && super.canTakeItem(itemstackIn);
		}
	}

	@Override
	public boolean canHoldItem(ItemStack stack) {
		Item item = stack.getItem();
		ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.MAINHAND);
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

			this.spitOutItem(this.getItemBySlot(EquipmentSlotType.MAINHAND));
			this.onItemPickup(itemEntity);
			this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack.split(1));
			this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
			this.take(itemEntity, itemstack.getCount());
			itemEntity.remove();
			this.eatTicks = 0;
		}

	}

	public void aiStep() {
		if (!this.level.isClientSide && this.isAlive() && this.isEffectiveAi()) {
			++this.eatTicks;
			ItemStack stack = this.getItemBySlot(EquipmentSlotType.MAINHAND);
			if (this.canEatItem(stack)) {
				if (this.eatTicks > 600) {
					ItemStack returnStack = stack.finishUsingItem(this.level, this);
					if (!returnStack.isEmpty()) {
						this.setItemSlot(EquipmentSlotType.MAINHAND, returnStack);
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
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if (spawnDataIn == null) {
			spawnDataIn = new AgeableEntity.AgeableData(1.0F);
		}

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	class FindItemsGoal extends Goal {
		public FindItemsGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			if (!GrizzlyBearEntity.this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty()) {
				return false;
			} else if (GrizzlyBearEntity.this.getTarget() == null && GrizzlyBearEntity.this.getLastHurtByMob() == null) {
				if (GrizzlyBearEntity.this.getRandom().nextInt(10) != 0) {
					return false;
				} else {
					List<ItemEntity> list = GrizzlyBearEntity.this.level.getEntitiesOfClass(ItemEntity.class, GrizzlyBearEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBearEntity.TRUSTED_TARGET_SELECTOR);
					return !list.isEmpty() && GrizzlyBearEntity.this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty();
				}
			} else {
				return false;
			}
		}

		public void tick() {
			List<ItemEntity> list = GrizzlyBearEntity.this.level.getEntitiesOfClass(ItemEntity.class, GrizzlyBearEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBearEntity.TRUSTED_TARGET_SELECTOR);
			ItemStack itemstack = GrizzlyBearEntity.this.getItemBySlot(EquipmentSlotType.MAINHAND);
			if (itemstack.isEmpty() && !list.isEmpty()) {
				GrizzlyBearEntity.this.getNavigation().moveTo(list.get(0), 1.2F);
			}

		}

		public void start() {
			List<ItemEntity> list = GrizzlyBearEntity.this.level.getEntitiesOfClass(ItemEntity.class, GrizzlyBearEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBearEntity.TRUSTED_TARGET_SELECTOR);
			if (!list.isEmpty()) {
				GrizzlyBearEntity.this.getNavigation().moveTo(list.get(0), 1.2F);
			}

		}
	}
}
