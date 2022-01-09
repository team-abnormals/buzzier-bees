package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.common.entities.MoobloomEntity;
import com.teamabnormals.buzzier_bees.core.BBConfig;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBlockTags;
import com.teamabnormals.buzzier_bees.core.other.tags.BBEntityTags;
import com.teamabnormals.buzzier_bees.core.registry.BBEffects;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
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
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBEvents {

	@SubscribeEvent
	public static void onLivingSpawned(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Mob) {
			Mob mob = (Mob) entity;
			if (mob.getType().is(BBEntityTags.MOOBLOOM_HOSTILES))
				mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, MoobloomEntity.class, false));
		}
	}

	@SubscribeEvent
	public static void renewableFlowers(RightClickBlock event) {
		Player player = event.getPlayer();
		Level world = event.getWorld();
		BlockPos pos = event.getPos();
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		ItemStack stack = player.getItemInHand(event.getHand());
		if (stack.getItem() != Items.BONE_MEAL)
			return;

		if (BBConfig.COMMON.shortFlowerDuplication.get() && block instanceof FlowerBlock && !state.hasBlockEntity() && !state.is(BBBlockTags.FLOWER_BLACKLIST) && !(block instanceof BonemealableBlock && ((BonemealableBlock) block).isBonemealSuccess(world, world.random, pos, state))) {
			if (!player.isCreative())
				stack.shrink(1);
			player.swing(event.getHand());
			if (world.isClientSide())
				BoneMealItem.addGrowthParticles(world, pos, world.random.nextInt(12));
			Block.popResource(world, pos, new ItemStack(block, 1));
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
	public static void onEntityUpdate(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if (entity instanceof Phantom) {
			if (((Phantom) entity).getTarget() instanceof ServerPlayer) {
				ServerPlayer player = (ServerPlayer) ((Phantom) entity).getTarget();
				if (player.getEffect(BBEffects.SUNNY.get()) != null) {
					((Phantom) entity).setTarget(null);
				}
			}
		}
	}

	@SubscribeEvent
	public static void bottleBug(PlayerInteractEvent.EntityInteractSpecific event) {
		if (event.getTarget() != null && !event.getWorld().isClientSide) {

			ItemStack itemstack = event.getPlayer().getItemInHand(event.getHand());
			Item item = itemstack.getItem();

			Item bottle = null;
			boolean successful = false;

			Entity target = event.getTarget();
			EntityType<?> targetType = target.getType();
			Player player = event.getPlayer();
			InteractionHand hand = event.getHand();

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
				bottleItem = bottleItem.setHoverName(name);
			}

			if (successful && target.isAlive()) {
				if (item == Items.GLASS_BOTTLE) {
					itemstack.shrink(1);
					event.getWorld().playSound(player, event.getPos(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, 1.0F);
					player.awardStat(Stats.ITEM_USED.get(event.getItemStack().getItem()));
					event.getTarget().kill();
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
