package com.minecraftabnormals.buzzier_bees.common.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BumblebeeEntity extends CreatureEntity implements IFlyingAnimal {
	@Nullable
	private int underWaterTicks;

	public BumblebeeEntity(EntityType<? extends BumblebeeEntity> fly, World world) {
		super(fly, world);
		this.moveController = new FlyingMovementController(this, 20, true);
		this.lookController = new LookController(this);
		this.setPathPriority(PathNodeType.WATER, -1.0F);
		this.setPathPriority(PathNodeType.COCOA, -1.0F);
		this.setPathPriority(PathNodeType.FENCE, -1.0F);
	}

	protected void registerData() {
		super.registerData();
	}

	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		return worldIn.getBlockState(pos).isAir() ? 10.0F : 0.0F;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.ROTTEN_FLESH), false));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double) 1.4F, true));
		this.goalSelector.addGoal(2, new BumblebeeEntity.WanderGoal());
		this.goalSelector.addGoal(3, new SwimGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(new Class[0]));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<ZombieEntity>(this, ZombieEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
	}


	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.5F;
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeBeeStingDamage(this), (float) ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
		if (flag) {
			this.applyEnchantments(this, entityIn);
			if (entityIn instanceof LivingEntity) {
				int i = 0;
				if (this.world.getDifficulty() == Difficulty.NORMAL) {
					i = 5;
				} else if (this.world.getDifficulty() == Difficulty.HARD) {
					i = 9;
				}

				if (i > 0) {
					((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WEAKNESS, i * 20, 0));
				}
			}

			this.setAttackTarget((LivingEntity) null);
			this.playSound(SoundEvents.ENTITY_BEE_STING, 1.0F, 1.0F);
		}

		return flag;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	protected void updateAITasks() {
		if (this.isInWaterOrBubbleColumn()) {
			++this.underWaterTicks;
		} else {
			this.underWaterTicks = 0;
		}

		if (this.underWaterTicks > 20) {
			this.attackEntityFrom(DamageSource.DROWN, 1.0F);
		}
	}

	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(Items.BEE_SPAWN_EGG);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 2.0D)
				.createMutableAttribute(Attributes.FLYING_SPEED, (double) 0.8F)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.3F)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
	}

	protected PathNavigator createNavigator(World worldIn) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
			public boolean canEntityStandOnPos(BlockPos pos) {
				return !this.world.getBlockState(pos.down()).isAir();
			}

			public void tick() {
				super.tick();
			}
		};
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanSwim(false);
		flyingpathnavigator.setCanEnterDoors(true);
		return flyingpathnavigator;
	}


	protected SoundEvent getAmbientSound() {
		return null;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_BEE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_BEE_DEATH;
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	protected boolean makeFlySound() {
		return true;
	}

	public boolean func_226391_a_(Entity p_226391_1_) {
		if (p_226391_1_ instanceof LivingEntity) {
			this.setRevengeTarget((LivingEntity) p_226391_1_);
		}

		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			Entity entity = source.getTrueSource();
			if (!this.world.isRemote && entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canEntityBeSeen(entity) && !this.isAIDisabled()) {
				this.func_226391_a_(entity);
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void handleFluidJump(ITag<Fluid> fluidTag) {
		this.setMotion(this.getMotion().add(0.0D, 0.01D, 0.0D));
	}

	public boolean func_225507_h_() {
		return false;
	}

	class WanderGoal extends Goal {
		WanderGoal() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean shouldExecute() {
			return BumblebeeEntity.this.navigator.noPath() && BumblebeeEntity.this.rand.nextInt(10) == 0;
		}

		public boolean shouldContinueExecuting() {
			return BumblebeeEntity.this.navigator.hasPath();
		}

		public void startExecuting() {
			Vector3d vec3d = this.func_226509_g_();
			if (vec3d != null) {
				BumblebeeEntity.this.navigator.setPath(BumblebeeEntity.this.navigator.getPathToPos(new BlockPos(vec3d), 1), 1.0D);
			}

		}

		@Nullable
		private Vector3d func_226509_g_() {
			Vector3d vec3d;
			vec3d = BumblebeeEntity.this.getLook(0.0F);

			Vector3d vec3d2 = RandomPositionGenerator.findAirTarget(BumblebeeEntity.this, 8, 7, vec3d, ((float) Math.PI / 2F), 2, 1);
			return vec3d2 != null ? vec3d2 : RandomPositionGenerator.findGroundTarget(BumblebeeEntity.this, 8, 4, -2, vec3d, (double) ((float) Math.PI / 2F));
		}
	}
}
