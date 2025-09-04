package com.teamabnormals.buzzier_bees.common.entity;

import com.teamabnormals.buzzier_bees.core.registry.BBDataComponents;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.Optional;

public interface Bottleable {
	boolean fromBottle();

	void setFromBottle(boolean fromBottle);

	void saveToBottleTag(ItemStack stack);

	void loadFromBottleTag(CompoundTag tag);

	ItemStack getBottleItemStack();

	SoundEvent getPickupSound();

	@Deprecated
	static void saveDefaultDataToBottleTag(Mob mob, ItemStack bottle) {
		bottle.set(DataComponents.CUSTOM_NAME, mob.getCustomName());
		CustomData.update(BBDataComponents.BOTTLE_ENTITY_DATA.get(), bottle, tag -> {
			if (mob.isNoAi()) {
				tag.putBoolean("NoAI", mob.isNoAi());
			}

			if (mob.isSilent()) {
				tag.putBoolean("Silent", mob.isSilent());
			}

			if (mob.isNoGravity()) {
				tag.putBoolean("NoGravity", mob.isNoGravity());
			}

			if (mob.hasGlowingTag()) {
				tag.putBoolean("Glowing", mob.hasGlowingTag());
			}

			if (mob.isInvulnerable()) {
				tag.putBoolean("Invulnerable", mob.isInvulnerable());
			}

			tag.putFloat("Health", mob.getHealth());
		});
	}

	@Deprecated
	static void loadDefaultDataFromBottleTag(Mob mob, CompoundTag tag) {
		if (tag.contains("NoAI")) {
			mob.setNoAi(tag.getBoolean("NoAI"));
		}

		if (tag.contains("Silent")) {
			mob.setSilent(tag.getBoolean("Silent"));
		}

		if (tag.contains("NoGravity")) {
			mob.setNoGravity(tag.getBoolean("NoGravity"));
		}

		if (tag.contains("Glowing")) {
			mob.setGlowingTag(tag.getBoolean("Glowing"));
		}

		if (tag.contains("Invulnerable")) {
			mob.setInvulnerable(tag.getBoolean("Invulnerable"));
		}

		if (tag.contains("Health", 99)) {
			mob.setHealth(tag.getFloat("Health"));
		}
	}

	static <T extends LivingEntity & Bottleable> Optional<InteractionResult> bottleMobPickup(Player player, InteractionHand hand, T entity) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem() == Items.GLASS_BOTTLE && entity.isAlive()) {
			entity.playSound(entity.getPickupSound(), 1.0F, 1.0F);
			ItemStack bottleStack = entity.getBottleItemStack();
			entity.saveToBottleTag(bottleStack);
			player.setItemInHand(hand, ItemUtils.createFilledResult(itemstack, player, bottleStack, false));
			Level level = entity.level();
			if (!level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bottleStack);
			}

			entity.discard();
			return Optional.of(InteractionResult.sidedSuccess(level.isClientSide));
		} else {
			return Optional.empty();
		}
	}

}
