package com.teamabnormals.buzzier_bees.core.mixin;

import com.teamabnormals.buzzier_bees.common.entity.Bottleable;
import com.teamabnormals.buzzier_bees.core.registry.BBDataComponents;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bee.class)
public abstract class BeeMixin extends Animal implements Bottleable {
	private static final EntityDataAccessor<Boolean> FROM_BOTTLE = SynchedEntityData.defineId(Bee.class, EntityDataSerializers.BOOLEAN);

	protected BeeMixin(EntityType<? extends Animal> type, Level worldIn) {
		super(type, worldIn);
	}

	@Shadow
	protected abstract boolean isTiredOfLookingForNectar();

	@Shadow
	public abstract boolean hasNectar();

	@Shadow
	protected abstract boolean isHiveNearFire();

	@Shadow
	public abstract void setHasNectar(boolean hasNectar);

	@Shadow
	public abstract void setHasStung(boolean hasStung);

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Bee;getTarget()Lnet/minecraft/world/entity/LivingEntity;", shift = At.Shift.AFTER), method = "wantsToEnterHive", cancellable = true)
	private void canEnterHive(CallbackInfoReturnable<Boolean> cir) {
		boolean sunny = this.getEffect(BBMobEffects.SUNNY) != null;
		boolean flag = this.isTiredOfLookingForNectar() || this.level().isRaining() || (this.level().isNight() && !sunny) || this.hasNectar();
		cir.setReturnValue(flag && !this.isHiveNearFire());
	}

	@Inject(at = @At("TAIL"), method = "defineSynchedData", cancellable = true)
	private void defineSynchedData(Builder builder, CallbackInfo ci) {
		builder.define(FROM_BOTTLE, false);
	}

	@Inject(at = @At("TAIL"), method = "addAdditionalSaveData", cancellable = true)
	private void addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
		compound.putBoolean("FromBottle", this.fromBottle());
	}

	@Inject(at = @At("TAIL"), method = "readAdditionalSaveData", cancellable = true)
	private void readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
		this.setFromBottle(compound.getBoolean("FromBottle"));
	}

	@Override
	public boolean fromBottle() {
		return this.entityData.get(FROM_BOTTLE);
	}

	@Override
	public void setFromBottle(boolean fromBottle) {
		this.entityData.set(FROM_BOTTLE, fromBottle);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		return Bottleable.bottleMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
	}

	@Override
	public void saveToBottleTag(ItemStack stack) {
		Bottleable.saveDefaultDataToBottleTag(this, stack);
		CustomData.update(BBDataComponents.BOTTLE_ENTITY_DATA.get(), stack, tag -> {
			Bee bee = (Bee) (Object) this;
			if (bee.hasNectar()) {
				tag.putBoolean("HasNectar", bee.hasNectar());
			}

			if (bee.hasStung()) {
				tag.putBoolean("HasStung", bee.hasStung());
			}

			if (bee.getPersistentAngerTarget() != null) {
				tag.putUUID("AngryAt", bee.getPersistentAngerTarget());
			}

			tag.putInt("AngerTime", bee.getRemainingPersistentAngerTime());
			tag.putInt("Age", bee.getAge());
		});
	}

	@Override
	public void loadFromBottleTag(CompoundTag tag) {
		Bee bee = (Bee) (Object) this;
		Bottleable.loadDefaultDataFromBottleTag(this, tag);
		if (tag.contains("HasNectar")) {
			bee.setHasNectar(tag.getBoolean("HasNectar"));
		}

		if (tag.contains("HasStung")) {
			bee.setHasStung(tag.getBoolean("HasStung"));
		}

		if (tag.contains("AngryAt")) {
			bee.setPersistentAngerTarget(tag.getUUID("AngryAt"));
		}

		if (tag.contains("AngerTime")) {
			bee.setRemainingPersistentAngerTime(tag.getInt("AngerTime"));
		}

		if (tag.contains("Age")) {
			this.setAge(tag.getInt("Age"));
		}
	}

	@Override
	public ItemStack getBottleItemStack() {
		return new ItemStack(BBItems.BOTTLE_OF_BEE.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BOTTLE_FILL_DRAGONBREATH;
	}

	@Override
	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBottle();
	}
}
