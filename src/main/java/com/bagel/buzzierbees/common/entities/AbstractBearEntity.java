package com.bagel.buzzierbees.common.entities;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.bagel.buzzierbees.api.endimator.ControlledEndimation;
import com.bagel.buzzierbees.api.endimator.Endimation;
import com.bagel.buzzierbees.api.endimator.entity.IEndimatedEntity;
import com.bagel.buzzierbees.common.entities.goals.bear.AttackPlayerGoal;
import com.bagel.buzzierbees.common.entities.goals.bear.EatBerriesGoal;
import com.bagel.buzzierbees.common.entities.goals.bear.HurtByTargetGoal;
import com.bagel.buzzierbees.common.entities.goals.bear.MeleeAttackGoal;
import com.bagel.buzzierbees.common.entities.goals.bear.PanicGoal;
import com.bagel.buzzierbees.core.registry.BBEntities;
import com.bagel.buzzierbees.core.registry.BBItems;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AbstractBearEntity extends AnimalEntity implements IEndimatedEntity {
   private static final DataParameter<Boolean> IS_STANDING = EntityDataManager.createKey(AbstractBearEntity.class, DataSerializers.BOOLEAN);
   private static final DataParameter<Boolean> IS_SLEEPING = EntityDataManager.createKey(AbstractBearEntity.class, DataSerializers.BOOLEAN);

   private float clientSideStandAnimation0;
   private float clientSideStandAnimation;
   private int warningSoundTicks;
   private int animationTick;
   
   private Endimation playingEndimation = BLANK_ANIMATION;
   
   public static final Endimation SLEEP_DOWN = new Endimation(40);
   public static final Endimation SLEEP_UP = new Endimation(40);
   public static final Endimation SLEEP_UP_ANGRY = new Endimation(40);
   public static final ControlledEndimation BREATHING = new ControlledEndimation(8, 0);

   public AbstractBearEntity(EntityType<? extends AbstractBearEntity> type, World worldIn) {
      super(type, worldIn);
   }

   public boolean isBreedingItem(ItemStack stack) {
      return false;
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(0, new SwimGoal(this));
      this.goalSelector.addGoal(1, new MeleeAttackGoal(this));
      this.goalSelector.addGoal(1, new PanicGoal(this));
      this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
      this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
      this.goalSelector.addGoal(7, new AbstractBearEntity.SleepGoal());
      this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
      this.goalSelector.addGoal(10, new EatBerriesGoal(this, (double)1.2F, 12, 2));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
      this.targetSelector.addGoal(2, new AttackPlayerGoal(this));
      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FoxEntity.class, 10, true, true, (Predicate<LivingEntity>)null));
   }

   protected void registerAttributes() {
      super.registerAttributes();
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
      this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
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

   public ItemStack getPickedResult(RayTraceResult target) {
	   return new ItemStack(BBItems.GRIZZLY_BEAR_SPAWN_EGG.get());
   }
   
   protected void registerData() {
      super.registerData();
      this.dataManager.register(IS_STANDING, false);
      this.dataManager.register(IS_SLEEPING, false);
   }

   public void tick() {
      super.tick();
      this.endimateTick();
      if (this.world.isRemote) {
         if (this.clientSideStandAnimation != this.clientSideStandAnimation0) {
            this.recalculateSize();
         }

         this.clientSideStandAnimation0 = this.clientSideStandAnimation;
         if (this.isStanding()) {
            this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation + 1.0F, 0.0F, 6.0F);
         } else {
            this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation - 1.0F, 0.0F, 6.0F);
         }
      }

      if (this.warningSoundTicks > 0) {
         --this.warningSoundTicks;
      }

   }

   public EntitySize getSize(Pose poseIn) {
      if (this.clientSideStandAnimation > 0.0F) {
         float f = this.clientSideStandAnimation / 6.0F;
         float f1 = 1.0F + f;
         return super.getSize(poseIn).scale(1.0F, f1);
      } else {
         return super.getSize(poseIn);
      }
   }

   public boolean attackEntityAsMob(Entity entityIn) {
      boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));
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
   }

   public boolean isSleeping() {
	   return this.dataManager.get(IS_SLEEPING);
   }

   public void setSleeping(boolean standing) {
	   this.dataManager.set(IS_SLEEPING, standing);
   }
   
   @OnlyIn(Dist.CLIENT)
   public float getStandingAnimationScale(float p_189795_1_) {
      return MathHelper.lerp(p_189795_1_, this.clientSideStandAnimation0, this.clientSideStandAnimation) / 6.0F;
   }

   protected float getWaterSlowDown() {
      return 0.98F;
   }

   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
      if (spawnDataIn == null) {
         spawnDataIn = new AgeableEntity.AgeableData();
         ((AgeableEntity.AgeableData)spawnDataIn).func_226258_a_(1.0F);
      }

      return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
   }
   
   public abstract class BaseGoal extends Goal {
	   private final EntityPredicate field_220816_b = (new EntityPredicate()).setDistance(12.0D).setLineOfSiteRequired().setCustomPredicate(AbstractBearEntity.this.new AlertablePredicate());

	   private BaseGoal() {
	   }

	   protected boolean func_220813_g() {
		   BlockPos blockpos = new BlockPos(AbstractBearEntity.this);
		   return !AbstractBearEntity.this.world.canSeeSky(blockpos) && AbstractBearEntity.this.getBlockPathWeight(blockpos) >= 0.0F;
	   }

	   protected boolean func_220814_h() {
		   return !AbstractBearEntity.this.world.getTargettableEntitiesWithinAABB(LivingEntity.class, this.field_220816_b, AbstractBearEntity.this, AbstractBearEntity.this.getBoundingBox().grow(12.0D, 6.0D, 12.0D)).isEmpty();
	   }
   }
   
   class SleepGoal extends AbstractBearEntity.BaseGoal {
	   private int field_220825_c = AbstractBearEntity.this.rand.nextInt(140);

	   public SleepGoal() {
		   this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
	   }	

	   public boolean shouldExecute() {
		   if (AbstractBearEntity.this.moveStrafing == 0.0F && AbstractBearEntity.this.moveVertical == 0.0F && AbstractBearEntity.this.moveForward == 0.0F) {
			   return this.func_220823_j() || AbstractBearEntity.this.isSleeping();
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
	            return AbstractBearEntity.this.world.isDaytime() && this.func_220813_g() && !this.func_220814_h();
	         }
	      }

	      public void resetTask() {
	         this.field_220825_c = AbstractBearEntity.this.rand.nextInt(140);
	      }
	      public void startExecuting() {
	    	  AbstractBearEntity.this.setJumping(false);
	    	  AbstractBearEntity.this.setSleeping(true);
	    	  AbstractBearEntity.this.getNavigator().clearPath();
	    	  AbstractBearEntity.this.getMoveHelper().setMoveTo(AbstractBearEntity.this.getPosX(), AbstractBearEntity.this.getPosY(), AbstractBearEntity.this.getPosZ(), 0.0D);
	      }
	   }
   
   public class AlertablePredicate implements Predicate<LivingEntity> {
	      public boolean test(LivingEntity p_test_1_) {
	         if (p_test_1_ instanceof FoxEntity) {
	            return false;
	         } else if (!(p_test_1_ instanceof ChickenEntity) && !(p_test_1_ instanceof RabbitEntity) && !(p_test_1_ instanceof MonsterEntity)) {
	            if (p_test_1_ instanceof TameableEntity) {
	               return !((TameableEntity)p_test_1_).isTamed();
	            } else if (!(p_test_1_ instanceof PlayerEntity) || !p_test_1_.isSpectator() && !((PlayerEntity)p_test_1_).isCreative()) {
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
		return null;
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
	
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return BBEntities.GRIZZLY_BEAR.get().create(this.world);
	}
}
