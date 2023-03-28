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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
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
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBEvents {

	@SubscribeEvent
	public static void onLivingSpawned(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Mob mob) {
			if (mob.getType().is(BBEntityTypeTags.MOOBLOOM_HOSTILES))
				mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Moobloom.class, false));
		}
	}

	@SubscribeEvent
	public static void renewableFlowers(RightClickBlock event) {
		Player player = event.getEntity();
		Level level = event.getLevel();
		BlockPos pos = event.getPos();
		BlockState state = level.getBlockState(pos);
		Block block = level.getBlockState(pos).getBlock();
		ItemStack stack = player.getItemInHand(event.getHand());
		if (stack.getItem() != Items.BONE_MEAL)
			return;

		if (BBConfig.COMMON.shortFlowerDuplication.get() && block instanceof FlowerBlock && !state.hasBlockEntity() && !state.is(BBBlockTags.FLOWER_BLACKLIST) && !(block instanceof BonemealableBlock && ((BonemealableBlock) block).isBonemealSuccess(level, level.random, pos, level.getBlockState(pos)))) {
			if (!player.isCreative())
				stack.shrink(1);
			player.swing(event.getHand());
			if (level.isClientSide())
				BoneMealItem.addGrowthParticles(level, pos, level.random.nextInt(12));
			Block.popResource(level, pos, new ItemStack(block, 1));
			event.setCanceled(true);
			event.setResult(Event.Result.ALLOW);
		}

		if (!BBConfig.COMMON.tallFlowerDuplication.get()) {
			if (block instanceof TallFlowerBlock) {
				event.setCanceled(true);
				event.setResult(Event.Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof Phantom) {
			if (((Phantom) entity).getTarget() instanceof ServerPlayer player) {
				if (player.getEffect(BBMobEffects.SUNNY.get()) != null) {
					((Phantom) entity).setTarget(null);
				}
			}
		}
	}

	@SubscribeEvent
	public static void bottleBug(PlayerInteractEvent.EntityInteractSpecific event) {
		if (event.getTarget() != null && !event.getLevel().isClientSide) {

			Player player = event.getEntity();
			InteractionHand hand = event.getHand();

			ItemStack itemstack = player.getItemInHand(hand);
			Item item = itemstack.getItem();

			Item bottle = null;
			boolean successful = false;

			Entity target = event.getTarget();
			EntityType<?> targetType = target.getType();

			if (targetType == EntityType.SILVERFISH) {
				bottle = BBItems.BOTTLE_OF_SILVERFISH.get();
				successful = true;
			}
			if (targetType == EntityType.ENDERMITE) {
				bottle = BBItems.BOTTLE_OF_ENDERMITE.get();
				successful = true;
			}
			if (targetType == EntityType.BEE) {
				bottle = BBItems.BOTTLE_OF_BEE.get();
				successful = true;
			}
			ItemStack bottleItem = new ItemStack(bottle);

			if (targetType == EntityType.BEE) {
				Bee bee = (Bee) target;
				CompoundTag tag = bottleItem.getOrCreateTag();
				tag.putBoolean("HasNectar", bee.hasNectar());
				tag.putBoolean("HasStung", bee.hasStung());
				tag.putInt("AngerTime", bee.getRemainingPersistentAngerTime());
				if (bee.getPersistentAngerTarget() != null) tag.putUUID("AngryAt", bee.getPersistentAngerTarget());
				tag.putInt("Age", bee.getAge());
				tag.putFloat("Health", bee.getHealth());
			}

			if (target.hasCustomName()) {
				Component name = target.getCustomName();
				bottleItem.setHoverName(name);
			}

			if (successful && target.isAlive()) {
				if (item == Items.GLASS_BOTTLE) {
					itemstack.shrink(1);
					event.getLevel().playSound(player, event.getPos(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, 1.0F);
					player.awardStat(Stats.ITEM_USED.get(event.getItemStack().getItem()));
					event.getTarget().discard();
					if (itemstack.isEmpty()) {
						player.setItemInHand(hand, bottleItem);
					} else if (!player.getInventory().add(bottleItem)) {
						player.drop(bottleItem, false);
					}
					player.swing(hand);
				}
			}
		}
	}
}
