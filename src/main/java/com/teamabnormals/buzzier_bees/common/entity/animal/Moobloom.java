package com.teamabnormals.buzzier_bees.common.entity.animal;

import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.IForgeShearable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Moobloom extends Cow implements Shearable, IForgeShearable {

	public Moobloom(EntityType<? extends Moobloom> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public Moobloom getBreedOffspring(ServerLevel level, AgeableMob ageable) {
		return BBEntityTypes.MOOBLOOM.get().create(level);
	}

	@Override
	public boolean readyForShearing() {
		return this.isAlive() && !this.isBaby();
	}

	@Override
	public boolean isShearable(ItemStack stack, Level level, BlockPos pos) {
		return this.readyForShearing();
	}

	public Block getFlower() {
		return BBBlocks.BUTTERCUP.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!level.isClientSide && level.getGameTime() % 20 == 0) {
			for (LivingEntity living : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(7.0D, 3.0D, 7.0D))) {
				if (!(living instanceof Moobloom))
					living.addEffect(new MobEffectInstance(BBMobEffects.SUNNY.get(), 100, 0, false, false));
			}
		}
	}

	@Override
	public List<ItemStack> onSheared(Player player, ItemStack item, Level world, BlockPos pos, int fortune) {
		this.gameEvent(GameEvent.SHEAR, player);
		return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
	}

	public void shear(SoundSource sound) {
		shearInternal(sound).forEach(s -> this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(1.0D), this.getZ(), s)));
	}

	private List<ItemStack> shearInternal(SoundSource sound) {
		this.level.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, sound, 1.0F, 1.0F);
		if (!this.level.isClientSide()) {
			((ServerLevel) this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.convertTo(EntityType.COW, false);
			List<ItemStack> items = new ArrayList<>();
			for (int i = 0; i < 3; ++i) {
				items.add(new ItemStack(this.getFlower()));
			}
			return items;
		}
		return Collections.emptyList();

	}

}
