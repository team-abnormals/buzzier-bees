package com.bagel.buzzierbees.common.entities;

import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.bagel.buzzierbees.common.entities.goals.grizzly_bear.AttackPlayerGoal;
import com.bagel.buzzierbees.core.registry.BBEntities;
import com.bagel.buzzierbees.core.registry.BBItems;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings({"static-access", "unchecked", "rawtypes", "unused"})
public class GrizzlyBearEntity extends AnimalEntity {
    private static final DataParameter<Boolean> IS_STANDING     = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_SITTING      = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);;
    private static final DataParameter<Boolean> IS_SLEEPING     = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> CAUGHT_ENTITY   = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> ATTACK_COOLDOWN = EntityDataManager.createKey(GrizzlyBearEntity.class, DataSerializers.VARINT);

    private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(
            Items.COD,
            Items.SALMON,
            Items.HONEYCOMB,
            Items.HONEY_BOTTLE,
            Items.SWEET_BERRIES,
            Items.BEEF,
            Items.PORKCHOP,
            Items.CHICKEN,
            Items.MUTTON,
            BBItems.CRYSTALLIZED_HONEY.get(),
            BBItems.STICKY_HONEY_WAND.get(),
            BBItems.GLAZED_PORKCHOP.get()
    );

    private static final Predicate<Entity> STALKABLE_PREY = (entity) -> {
        if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            LivingEntity livingentity = (LivingEntity)entity;
            return livingentity.getLastAttackedEntity() != null && livingentity.getLastAttackedEntityTime() < livingentity.ticksExisted + 600;
        }
    };
    private static final Predicate<Entity> IS_PREY = (entity) -> {
        return  entity instanceof RabbitEntity ||
                entity instanceof HoneySlimeEntity ||
                entity instanceof SalmonEntity ||
                entity instanceof CodEntity;
    };
    private static final Predicate<Entity> SHOULD_AVOID = (entity) -> {
        return !entity.isDiscrete() && EntityPredicates.CAN_AI_TARGET.test(entity);
    };

    private float clientSideStandAnimation0;
    private float clientSideStandAnimation;
    private int warningSoundTicks;
    private float interestedAngle;
    private float interestedAngleO;

    public GrizzlyBearEntity(EntityType<? extends GrizzlyBearEntity> p_i50249_1_, World p_i50249_2_) {
        super(p_i50249_1_, p_i50249_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, BeeEntity.class, 8.0F, 1.6D, 1.4D, (beeEntity) -> !this.isSleeping()));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new AttackPlayerGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, WolfEntity.class, 10, true, true, (wolfEntity) -> !((WolfEntity)wolfEntity).isTamed()));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractFishEntity.class, 20, false, false, (fishEntity) -> fishEntity instanceof CodEntity || fishEntity instanceof SalmonEntity));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, AnimalEntity.class, 10, false, false, (animal) -> animal instanceof RabbitEntity));
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_STANDING, false);
        this.dataManager.register(IS_SITTING, false);
        this.dataManager.register(IS_SLEEPING, false);
        this.dataManager.register(CAUGHT_ENTITY, 0);
        this.dataManager.register(ATTACK_COOLDOWN, 0);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Standing", this.isSleeping());
        compound.putBoolean("Sitting", this.isSitting());
        compound.putBoolean("Sleeping", this.isSleeping());
        compound.putInt("AttackCooldown", this.getAttackCooldown());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSleeping(compound.getBoolean("Standing"));
        this.setSitting(compound.getBoolean("Sitting"));
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setAttackCooldown(compound.getInt("AttackCooldown"));
    }

    public boolean isStanding() {
        return this.dataManager.get(IS_STANDING);
    }

    public void setStanding(boolean value) {
        this.dataManager.set(IS_STANDING, value);
    }

    public boolean isSitting() {
        return this.dataManager.get(IS_SITTING);
    }

    public void setSitting(boolean value) {
        this.dataManager.set(IS_SITTING, value);
    }

    public boolean isSleeping() {
        return this.dataManager.get(IS_SLEEPING);
    }

    public void setSleeping(boolean value) {
        this.dataManager.set(IS_SLEEPING, value);
    }

    public int getAttackCooldown() {
        return this.dataManager.get(ATTACK_COOLDOWN);
    }

    public void setAttackCooldown(int value) {
        this.dataManager.set(ATTACK_COOLDOWN, value);
    }

	public boolean isPray(Entity entity) {
        return this.IS_PREY.test(entity);
    }

    public AgeableEntity createChild(AgeableEntity entity) {
        return BBEntities.GRIZZLY_BEAR.create(this.world);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_ITEMS.test(stack);
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(BBItems.GRIZZLY_BEAR_SPAWN_EGG.get());
    }

    public static boolean grizzlyBearSpawningConditions(EntityType<GrizzlyBearEntity> p_223320_0_, IWorld p_223320_1_, SpawnReason p_223320_2_, BlockPos p_223320_3_, Random p_223320_4_) {
        Biome lvt_5_1_ = p_223320_1_.getBiome(p_223320_3_);
        if (lvt_5_1_ != Biomes.FOREST && lvt_5_1_ != Biomes.TAIGA) {
            return canSpawnOn(p_223320_0_, p_223320_1_, p_223320_2_, p_223320_3_, p_223320_4_);
        } else {
            return true;
        }
    }

    protected SoundEvent getAmbientSound() {
        return this.isChild() ? SoundEvents.ENTITY_POLAR_BEAR_AMBIENT_BABY : SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_POLAR_BEAR_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
    }

    public void playWarningSound() {
        if (this.warningSoundTicks <= 0) {
            this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, this.getSoundPitch());
            this.warningSoundTicks = 40;
        }
    }

    public void tick() {
        super.tick();
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

        this.interestedAngleO = this.interestedAngle;
        if (false) {
            this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
        } else {
            this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
        }
    }

    private boolean canEatItem(ItemStack itemStackIn) {
        return itemStackIn.getItem().isFood() && this.getAttackTarget() == null && this.onGround && !this.isSleeping();
    }

    @OnlyIn(Dist.CLIENT)
    public float func_213475_v(float p_213475_1_) {
        return MathHelper.lerp(p_213475_1_, this.interestedAngleO, this.interestedAngle) * 0.11F * (float)Math.PI;
    }


    public EntitySize getSize(Pose p_213305_1_) {
        if (this.clientSideStandAnimation > 0.0F) {
            float lvt_2_1_ = this.clientSideStandAnimation / 6.0F;
            float lvt_3_1_ = 1.0F + lvt_2_1_;
            return super.getSize(p_213305_1_).scale(1.0F, lvt_3_1_);
        } else {
            return super.getSize(p_213305_1_);
        }
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        boolean lvt_2_1_ = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));
        if (lvt_2_1_) {
            this.applyEnchantments(this, p_70652_1_);
        }

        return lvt_2_1_;
    }

    @OnlyIn(Dist.CLIENT)
    public float getStandingAnimationScale(float p_189795_1_) {
        return MathHelper.lerp(p_189795_1_, this.clientSideStandAnimation0, this.clientSideStandAnimation) / 6.0F;
    }

    protected float getWaterSlowDown() {
        return 0.98F;
    }

    public ILivingEntityData onInitialSpawn(IWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        if (p_213386_4_ == null) {
            p_213386_4_ = new AgeableData();
            ((AgeableData)p_213386_4_).func_226258_a_(1.0F);
        }

        return super.onInitialSpawn(p_213386_1_, p_213386_2_, p_213386_3_, (ILivingEntityData)p_213386_4_, p_213386_5_);
    }

    class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
        public PanicGoal() {
            super(GrizzlyBearEntity.this, 2.0D);
        }

        public boolean shouldExecute() {
            return !GrizzlyBearEntity.this.isChild() && !GrizzlyBearEntity.this.isBurning() ? false : super.shouldExecute();
        }
    }
}