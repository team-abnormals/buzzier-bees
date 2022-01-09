package com.teamabnormals.buzzier_bees.common.entities;

import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.buzzier_bees.core.registry.BBEffects;
import com.teamabnormals.buzzier_bees.core.registry.BBEntities;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MoobloomEntity extends Cow implements Shearable, IForgeShearable {

	public MoobloomEntity(EntityType<? extends MoobloomEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public MoobloomEntity getBreedOffspring(ServerLevel world, AgeableMob ageable) {
		return BBEntities.MOOBLOOM.get().create(world);
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(BBItems.MOOBLOOM_SPAWN_EGG.get());
	}

	@Override
	public boolean readyForShearing() {
		return this.isAlive() && !this.isBaby();
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, Level world, BlockPos pos) {
		return this.isAlive() && !this.isBaby();
	}

	public Block getFlower() {
		return BBBlocks.BUTTERCUP.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!level.isClientSide && level.getGameTime() % 20 == 0) {
			for (LivingEntity living : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(7.0D, 3.0D, 7.0D))) {
				if (!(living instanceof MoobloomEntity))
					living.addEffect(new MobEffectInstance(BBEffects.SUNNY.get(), 100, 0, false, false));
			}
		}
	}

	@Override
	public void shear(SoundSource category) {
		this.level.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, category, 1.0F, 1.0F);
		if (!this.level.isClientSide()) {
			((ServerLevel) this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.kill();
			Cow cowentity = EntityType.COW.create(this.level);
			cowentity.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
			cowentity.setHealth(this.getHealth());
			cowentity.yBodyRot = this.yBodyRot;
			if (this.hasCustomName()) {
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isPersistenceRequired()) {
				cowentity.setPersistenceRequired();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.level.addFreshEntity(cowentity);

			for (int i = 0; i < 5; ++i) {
				this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(1.0D), this.getZ(), new ItemStack(this.getFlower())));
			}
		}
	}

	@Override
	public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (!world.isClientSide()) {
			((ServerLevel) this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.kill();
			Cow cowentity = EntityType.COW.create(this.level);
			cowentity.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
			cowentity.setHealth(this.getHealth());
			cowentity.yBodyRot = this.yBodyRot;
			if (this.hasCustomName()) {
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isPersistenceRequired()) {
				cowentity.setPersistenceRequired();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.level.addFreshEntity(cowentity);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int i = 0; i < 3; ++i) {
				items.add(new ItemStack(this.getFlower()));
			}

			return items;
		}
		return java.util.Collections.emptyList();
	}

}
