package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.buzzier_bees.common.entity.animal.Moobloom;
import com.teamabnormals.buzzier_bees.core.BBConfig;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBlockTags;
import com.teamabnormals.buzzier_bees.core.other.tags.BBEntityTypeTags;
import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBEvents {

	@SubscribeEvent
	public static void onLivingSpawned(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof Mob mob) {
			if (mob.getType().is(BBEntityTypeTags.MOOBLOOM_HOSTILES)) {
				mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Moobloom.class, false));
			}
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
			event.setSuccessful(true);
		}

		if (!BBConfig.COMMON.tallFlowerDuplication.get()) {
			if (block instanceof TallFlowerBlock) {
				event.setCanceled(true);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityUpdate(EntityTickEvent.Pre event) {
		if (event.getEntity() instanceof Phantom phantom) {
			if (phantom.getTarget() instanceof ServerPlayer player) {
				if (player.getEffect(BBMobEffects.SUNNY) != null) {
					phantom.setTarget(null);
				}
			}
		}
	}
}
