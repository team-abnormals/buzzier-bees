package com.bagel.buzzierbees.common.entities;

import com.bagel.buzzierbees.common.entities.controllers.HoneySlimeMoveHelperController;
import com.bagel.buzzierbees.common.entities.goals.*;
import com.bagel.buzzierbees.core.registry.ModEntities;
import com.bagel.buzzierbees.core.registry.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.storage.loot.LootTables;

import javax.annotation.Nullable;
import java.util.*;

@SuppressWarnings("deprecation")
public class HoneySlimeEntity extends AnimalEntity implements IMob, net.minecraftforge.common.IShearable {
   private static final DataParameter<Boolean> STICKY = EntityDataManager.createKey(HoneySlimeEntity.class, DataSerializers.BOOLEAN);
   private static final Ingredient BREEDING_ITEM = Ingredient.fromItems(Items.SUGAR);

   public float squishAmount;
   public float squishFactor;
   public float prevSquishFactor;

   public boolean isAngry;
   public boolean desticked;
   private boolean wasOnGround;
   private int growSticknessTimer;

   public HoneySlimeEntity(EntityType<? extends HoneySlimeEntity> type, World worldIn) {
      super(type, worldIn);
      this.moveController = new HoneySlimeMoveHelperController(this);
   }

   protected void registerGoals() {
	  this.goalSelector.addGoal(0, new HoneySlimeFloatGoal(this));
      this.goalSelector.addGoal(1, new HoneySlimeBreedGoal(this, 1.0D));
      this.goalSelector.addGoal(2, new HoneySlimeTemptGoal(this, 1.2D, BREEDING_ITEM));
      this.goalSelector.addGoal(4, new HoneySlimeHopGoal(this));
      this.goalSelector.addGoal(5, new HoneySlimeFaceRandomGoal(this));
      this.goalSelector.addGoal(6, new HurtByTargetGoal(this, new Class[0]));
      this.goalSelector.addGoal(7, new HoneySlimeAttackGoal(this));
   }

   protected void registerData() {
      super.registerData();
      //this.dataManager.register(SLIME_SIZE, 1);
      this.dataManager.register(STICKY, true);
   }

   protected void registerAttributes() {
      super.registerAttributes();
      this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
   }

   public void writeAdditional(CompoundNBT compound) {
      super.writeAdditional(compound);
      compound.putBoolean("desticked", this.desticked);
      compound.putBoolean("wasOnGround", this.wasOnGround);
   }

   /**
    * (abstract) Protected helper method to read subclass entity data from NBT.
    */
   public void readAdditional(CompoundNBT compound) {
      super.readAdditional(compound);
      this.desticked = compound.getBoolean("desticked");
      this.wasOnGround = compound.getBoolean("wasOnGround");
   }

   protected IParticleData getSquishParticle() {
      return ParticleTypes.field_229429_ai_;
   }

   protected boolean func_225511_J_() {
      return !this.isChild();
   }

   public boolean processInteract(PlayerEntity player, Hand hand) {
      ItemStack itemstack = player.getHeldItem(hand);
      World world = player.getEntityWorld();
      if (!this.isChild()) {
         //Bottling
         if (itemstack.getItem() == Items.GLASS_BOTTLE) {
            world.playSound(player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            if (!player.abilities.isCreativeMode) {
               itemstack.shrink(1);
               if (itemstack.isEmpty()) {
                  player.setHeldItem(hand, new ItemStack(Items.field_226638_pX_));
               } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.field_226638_pX_))) {
                  player.dropItem(new ItemStack(Items.field_226638_pX_), false);
               }
            }
            performEffect(this, 1);
            return true;
         }
         //Wanding
         else if (itemstack.getItem() == ModItems.HONEY_WAND.get()) {
            world.playSound(player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), SoundEvents.field_226139_eT_, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            if (!player.abilities.isCreativeMode) {
               player.setHeldItem(hand, new ItemStack(ModItems.STICKY_HONEY_WAND.get()));
            }
            performEffect(this, 1);
            return true;
         }
      }
      return super.processInteract(player, hand);
   }

   public void performEffect(LivingEntity entity, int amplifier) {
      if (entity instanceof HoneySlimeEntity) {
         //If it desticked - regular slime'll appear
         if (((HoneySlimeEntity) entity).desticked) {

            SlimeEntity slimeentity = new SlimeEntity(EntityType.SLIME, this.world);
            slimeentity.setLocationAndAngles(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (entity.rotationYaw), entity.rotationPitch);
            slimeentity.setNoAI(((MobEntity) entity).isAIDisabled());
            if (entity.hasCustomName()) {
               slimeentity.setCustomName(entity.getCustomName());
               slimeentity.setCustomNameVisible(entity.isCustomNameVisible());
            }

            slimeentity.setInvulnerable(this.isInvulnerable());
            slimeentity.setSlimeSize(2, true);
            slimeentity.setLocationAndAngles(this.func_226277_ct_(), this.func_226278_cu_() + 0.5D, this.func_226281_cx_(), this.rand.nextFloat() * 360.0F, 0.0F);
            this.world.addEntity(slimeentity);
            if (slimeentity.getHealth() > 0) {
               entity.world.addEntity(slimeentity);
               entity.remove(true);
            }
         }
         else {
            ((HoneySlimeEntity) entity).desticked = true;
         }
      }
   }

   public void setExperienceValue(int value) {
      this.experienceValue = value;
   }


   /**
    * Called to update the entity's position/logic.
    */
   public void tick() {
      if (this.desticked) {
         if (--this.growSticknessTimer <= 0) {
            growSticknessTimer = 300;
            desticked = false;
         }
      }

      this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
      this.prevSquishFactor = this.squishFactor;
      super.tick();
      if (this.onGround && !this.wasOnGround) {
         int i = 2;

         if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
         for (int j = 0; j < i * 8; ++j) {
            float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
            float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
            this.world.addParticle(this.getSquishParticle(), this.func_226277_ct_() + (double) f2, this.func_226278_cu_(), this.func_226281_cx_() + (double) f3, 0.0D, 0.0D, 0.0D);
         }

         this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
         this.squishAmount = -0.5F;
      } else if (!this.onGround && this.wasOnGround) {
         this.squishAmount = 1.0F;
      }

      this.wasOnGround = this.onGround;
      this.alterSquishAmount();
   }

   protected void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   /**
    * Gets the amount of time the slime needs to wait between jumps.
    */
   public int getJumpDelay() {
      return this.rand.nextInt(20) + 10;
   }

   public void recalculateSize() {
      double d0 = this.func_226277_ct_();
      double d1 = this.func_226278_cu_();
      double d2 = this.func_226281_cx_();
      super.recalculateSize();
      this.setPosition(d0, d1, d2);
   }

   public void notifyDataManagerChange(DataParameter<?> key) {
      this.recalculateSize();
      this.rotationYaw = this.rotationYawHead;
      this.renderYawOffset = this.rotationYawHead;
      if (this.isInWater() && this.rand.nextInt(20) == 0) {
         this.doWaterSplashEffect();
      }
      super.notifyDataManagerChange(key);
   }

   @SuppressWarnings("unchecked")
   public EntityType<? extends HoneySlimeEntity> getType() {
      return (EntityType<? extends HoneySlimeEntity>) super.getType();
   }

   @Override
   public void remove(boolean keepData) {
      super.remove(keepData);
   }

   public void applyEntityCollision(Entity entityIn) {
      super.applyEntityCollision(entityIn);
   }
   
   protected void dealDamage(LivingEntity entityIn) {
      if (this.isAlive()) {
         int i = 2;
         if (this.getDistanceSq(entityIn) < 0.6D * (double) i * 0.6D * (double) i && this.canEntityBeSeen(entityIn) && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), this.func_225512_er_())) {
            this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.applyEnchantments(this, entityIn);
         }
      }
   }

   protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
      return 0.625F * sizeIn.height;
   }

   public boolean canDamagePlayer() {
      return !this.isChild() && this.isServerWorld();
   }

   protected float func_225512_er_() {
      return (float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return this.isChild() ? SoundEvents.ENTITY_SLIME_HURT_SMALL : SoundEvents.ENTITY_SLIME_HURT;
   }

   protected SoundEvent getDeathSound() {
      return this.isChild() ? SoundEvents.ENTITY_SLIME_DEATH_SMALL : SoundEvents.ENTITY_SLIME_DEATH;
   }

   protected SoundEvent getSquishSound() {
      return this.isChild() ? SoundEvents.ENTITY_SLIME_SQUISH_SMALL : SoundEvents.ENTITY_SLIME_SQUISH;
   }

   protected ResourceLocation getLootTable() {
      return this.isChild() ? this.getType().getLootTable() : LootTables.EMPTY;
   }

   public static boolean honeySlimeCondition(EntityType<HoneySlimeEntity> honeySlime, IWorld world, SpawnReason reason, BlockPos position, Random randomIn) {
      if (world.getWorldInfo().getGenerator().handleSlimeSpawnReduction(randomIn, world) && randomIn.nextInt(6) != 1) {
         return false;
      }
      else {
         if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Biome biome = world.func_226691_t_(position);
            if (biome == Biomes.FLOWER_FOREST && position.getY() > 50 && position.getY() < 90 && randomIn.nextFloat() < 0.5F && world.getLight(position) >= randomIn.nextInt(8)) {
               return func_223315_a(honeySlime, world, reason, position, randomIn);
            }
         }

         return false;
      }
   }

   public float getSoundVolume() {
      return 0.4F * (float) (isChild() ? 1 : 2);
   }

   public int getVerticalFaceSpeed() {
      return 0;
   }

   public boolean makesSoundOnJump() {
      return !this.isChild();
   }

   protected void jump() {
      Vec3d vec3d = this.getMotion();
      this.setMotion(vec3d.x, (double) this.getJumpUpwardsMotion(), vec3d.z);
      this.isAirBorne = true;
   }

   //Спавн крч
   @Nullable
   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
      return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
   }

   @Nullable
   @Override
   public AgeableEntity createChild(AgeableEntity ageable) {
      HoneySlimeEntity honeySlimeEntity = ModEntities.HONEY_SLIME.create(this.world);
      return honeySlimeEntity;
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return BREEDING_ITEM.test(stack);
   }

   public SoundEvent getJumpSound() {
      return this.isChild() ? SoundEvents.ENTITY_SLIME_JUMP_SMALL : SoundEvents.ENTITY_SLIME_JUMP;
   }

   protected boolean spawnCustomParticles() {
      return false;
   }

   public void onCollideWithPlayer(PlayerEntity entityIn) {
      if (this.canDamagePlayer() && this.isAngry) {
         this.dealDamage(entityIn);
      }
   }
}