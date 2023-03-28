package com.teamabnormals.buzzier_bees.common.entity.animal;

import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearAttackPlayerGoal;
import com.teamabnormals.buzzier_bees.common.entity.ai.goal.BearFindItemsGoal;
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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
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
import java.util.function.Predicate;

public class GrizzlyBear extends Animal {
	private static final EntityDataAccessor<Boolean> HONEY = SynchedEntityData.defineId(GrizzlyBear.class, EntityDataSerializers.BOOLEAN);
	public static final Predicate<ItemEntity> ALLOWED_ITEMS = (itemEntity) -> !itemEntity.hasPickUpDelay() && itemEntity.isAlive();

	public int eatTicks;

	public GrizzlyBear(EntityType<? extends GrizzlyBear> type, Level worldIn) {
		super(type, worldIn);
		this.setCanPickUpLoot(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(1, new BearPanicGoal(this));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new BearFindItemsGoal(this));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

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
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("Honey", this.isHoney());

	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setHoney(tag.getBoolean("Honey"));
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
			this.level.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
			if (stack.isEmpty()) {
				player.setItemInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
			} else if (!player.getInventory().add(new ItemStack(Items.HONEY_BOTTLE))) {
				player.drop(new ItemStack(Items.HONEY_BOTTLE), false);
			}

			level.gameEvent(player, GameEvent.FLUID_PICKUP, this.position());
		}

		return super.mobInteract(player, hand);
	}

	private boolean canEatItem(ItemStack stack) {
		return stack.isEdible() && this.getTarget() == null && this.onGround;
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
		if (!stack.isEmpty() && !this.level.isClientSide) {
			ItemEntity entity = new ItemEntity(this.level, this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, stack);
			entity.setPickUpDelay(40);
			entity.setThrower(this.getUUID());
			this.playSound(SoundEvents.FOX_SPIT, 1.0F, 1.0F);
			this.level.addFreshEntity(entity);
		}
	}

	private void spawnItem(ItemStack stack) {
		ItemEntity entity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), stack);
		this.level.addFreshEntity(entity);
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
				} else if (this.eatTicks > 500 && this.random.nextFloat() < 0.1F) {
					this.playSound(this.getEatingSound(stack), 1.0F, 1.0F);
					this.level.broadcastEntityEvent(this, (byte) 45);
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

	public void tick() {
		super.tick();
	}

	public boolean doHurtTarget(Entity target) {
		boolean flag = target.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
		if (flag) {
			this.doEnchantDamageEffects(this, target);
		}

		return flag;
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


}
