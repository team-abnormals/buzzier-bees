package com.minecraftabnormals.buzzier_bees.common.entities;

import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.minecraftabnormals.buzzier_bees.core.registry.BBEffects;
import com.minecraftabnormals.buzzier_bees.core.registry.BBEntities;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IShearable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MoobloomEntity extends CowEntity implements IShearable, IForgeShearable {

	public MoobloomEntity(EntityType<? extends MoobloomEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public MoobloomEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
		return BBEntities.MOOBLOOM.get().create(world);
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(BBItems.MOOBLOOM_SPAWN_EGG.get());
	}

	@Override
	public boolean isShearable() {
		return this.isAlive() && !this.isChild();
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, World world, BlockPos pos) {
		return this.isAlive() && !this.isChild();
	}

	public Block getFlower() {
		return BBBlocks.BUTTERCUP.get();
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (!world.isRemote && world.getGameTime() % 20 == 0) {
			for (LivingEntity living : this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(7.0D, 3.0D, 7.0D))) {
				if (!(living instanceof MoobloomEntity))
					living.addPotionEffect(new EffectInstance(BBEffects.SUNNY.get(), 100, 0, false, false));
			}
		}
	}

	@Override
	public void shear(SoundCategory category) {
		this.world.playMovingSound(null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, category, 1.0F, 1.0F);
		if (!this.world.isRemote()) {
			((ServerWorld) this.world).spawnParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.remove();
			CowEntity cowentity = EntityType.COW.create(this.world);
			cowentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
			cowentity.setHealth(this.getHealth());
			cowentity.renderYawOffset = this.renderYawOffset;
			if (this.hasCustomName()) {
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isNoDespawnRequired()) {
				cowentity.enablePersistence();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.world.addEntity(cowentity);

			for (int i = 0; i < 5; ++i) {
				this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosYHeight(1.0D), this.getPosZ(), new ItemStack(this.getFlower())));
			}
		}
	}

	@Override
	public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
		world.playMovingSound(null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
		if (!world.isRemote()) {
			((ServerWorld) this.world).spawnParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.remove();
			CowEntity cowentity = EntityType.COW.create(this.world);
			cowentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
			cowentity.setHealth(this.getHealth());
			cowentity.renderYawOffset = this.renderYawOffset;
			if (this.hasCustomName()) {
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isNoDespawnRequired()) {
				cowentity.enablePersistence();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.world.addEntity(cowentity);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int i = 0; i < 3; ++i) {
				items.add(new ItemStack(this.getFlower()));
			}

			return items;
		}
		return java.util.Collections.emptyList();
	}

}
