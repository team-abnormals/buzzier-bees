package com.teamabnormals.buzzier_bees.core.mixin;

import com.teamabnormals.buzzier_bees.common.entity.Bottleable;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Endermite.class)
public abstract class EndermiteMixin extends Monster implements Bottleable {
	private static final EntityDataAccessor<Boolean> FROM_BOTTLE = SynchedEntityData.defineId(Endermite.class, EntityDataSerializers.BOOLEAN);

	protected EndermiteMixin(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(FROM_BOTTLE, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("FromBottle", this.fromBottle());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
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
	}

	@Override
	public void loadFromBottleTag(CompoundTag tag) {
		Bottleable.loadDefaultDataFromBottleTag(this, tag);
	}

	@Override
	public ItemStack getBottleItemStack() {
		return new ItemStack(BBItems.BOTTLE_OF_ENDERMITE.get());
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
