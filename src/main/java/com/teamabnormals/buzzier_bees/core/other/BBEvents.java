package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.common.entity.animal.Moobloom;
import com.teamabnormals.buzzier_bees.core.BBConfig;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBlockTags;
import com.teamabnormals.buzzier_bees.core.other.tags.BBEntityTypeTags;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBEvents {

	@SubscribeEvent
	public static void onLivingSpawned(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof Mob mob) {
			if (mob.getType().is(BBEntityTypeTags.MOOBLOOM_HOSTILES))
				mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Moobloom.class, false));
		}
	}

	@SubscribeEvent
	public static void renewableFlowers(BonemealEvent event) {
		Level level = event.getLevel();
		BlockPos pos = event.getPos();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();

		if (BBConfig.COMMON.shortFlowerDuplication.get() && block instanceof FlowerBlock && !state.hasBlockEntity() && !state.is(BBBlockTags.FLOWER_BLACKLIST) && !(block instanceof BonemealableBlock bonemealableBlock && bonemealableBlock.isBonemealSuccess(level, level.random, pos, level.getBlockState(pos)))) {
			if (!level.isClientSide()) {
				Block.popResource(level, pos, new ItemStack(block));
			}
			event.setResult(Result.ALLOW);
		}

		if (!BBConfig.COMMON.tallFlowerDuplication.get()) {
			if (block instanceof TallFlowerBlock) {
				event.setCanceled(true);
				event.setResult(Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingTickEvent event) {
		if (event.getEntity() instanceof Phantom phantom) {
			if (phantom.getTarget() instanceof ServerPlayer player) {
				if (player.getEffect(BBMobEffects.SUNNY.get()) != null) {
					phantom.setTarget(null);
				}
			}
		}
	}

	@SubscribeEvent
	public static void bottleBug(EntityInteractSpecific event) {
		ItemStack stack = event.getItemStack();
		Entity target = event.getTarget();
		if (stack.is(Items.GLASS_BOTTLE) && target != null && target.isAlive()) {
			Player player = event.getEntity();
			InteractionHand hand = event.getHand();
			Level level = event.getLevel();
			EntityType<?> targetType = target.getType();

			Item bottle = null;
			if (targetType == EntityType.SILVERFISH) {
				bottle = BBItems.BOTTLE_OF_SILVERFISH.get();
			} else if (targetType == EntityType.ENDERMITE) {
				bottle = BBItems.BOTTLE_OF_ENDERMITE.get();
			} else if (targetType == EntityType.BEE) {
				bottle = BBItems.BOTTLE_OF_BEE.get();
			}

			if (bottle != null) {
				ItemStack bottleItem = new ItemStack(bottle);
				if (targetType == EntityType.BEE) {
					Bee bee = (Bee) target;
					CompoundTag tag = bottleItem.getOrCreateTag();
					tag.putBoolean("HasNectar", bee.hasNectar());
					tag.putBoolean("HasStung", bee.hasStung());
					tag.putInt("AngerTime", bee.getRemainingPersistentAngerTime());
					tag.putInt("Age", bee.getAge());
					tag.putFloat("Health", bee.getHealth());
					if (bee.getPersistentAngerTarget() != null)
						tag.putUUID("AngryAt", bee.getPersistentAngerTarget());
				}

				if (target.hasCustomName()) {
					Component name = target.getCustomName();
					bottleItem.setHoverName(name);
				}

				level.playSound(player, event.getPos(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, 1.0F);
				stack.shrink(1);
				player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
				target.discard();
				if (stack.isEmpty()) {
					player.setItemInHand(hand, bottleItem);
				} else if (!player.getInventory().add(bottleItem)) {
					player.drop(bottleItem, false);
				}

				event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
				event.setCanceled(true);
			}
		}
	}
}
