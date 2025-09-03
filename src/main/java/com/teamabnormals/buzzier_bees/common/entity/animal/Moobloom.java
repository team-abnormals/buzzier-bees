package com.teamabnormals.buzzier_bees.common.entity.animal;

import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Moobloom extends Cow implements Shearable {

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
	public boolean isShearable(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		return this.readyForShearing();
	}

	public Block getFlower() {
		return BBBlocks.BUTTERCUP.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!this.level().isClientSide && this.level().getGameTime() % 20 == 0) {
			for (LivingEntity living : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(7.0D, 3.0D, 7.0D))) {
				if (!(living instanceof Moobloom))
					living.addEffect(new MobEffectInstance(BBMobEffects.SUNNY, 100, 0, false, false));
			}
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.is(Items.BOWL) && !this.isBaby()) {
			ItemStack stewStack = new ItemStack(Items.SUSPICIOUS_STEW);
			stewStack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, this.getEffectsFromItemStack(new ItemStack(BBBlocks.BUTTERCUP)).get());
			player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, stewStack, false));
			this.playSound(SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY, 1.0F, 1.0F);
			return InteractionResult.sidedSuccess(this.level().isClientSide());
		} else {
			return super.mobInteract(player, hand);
		}
	}

	private Optional<SuspiciousStewEffects> getEffectsFromItemStack(ItemStack stack) {
		SuspiciousEffectHolder effect = SuspiciousEffectHolder.tryGet(stack.getItem());
		return effect != null ? Optional.of(effect.getSuspiciousEffects()) : Optional.empty();
	}

	@Override
	public List<ItemStack> onSheared(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		this.gameEvent(GameEvent.SHEAR, player);
		return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
	}

	public void shear(SoundSource sound) {
		shearInternal(sound).forEach(s -> this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(1.0D), this.getZ(), s)));
	}

	private List<ItemStack> shearInternal(SoundSource sound) {
		this.level().playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, sound, 1.0F, 1.0F);
		if (!this.level().isClientSide()) {
			((ServerLevel) this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
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
