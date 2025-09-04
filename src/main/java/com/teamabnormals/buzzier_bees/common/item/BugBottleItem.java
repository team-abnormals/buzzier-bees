package com.teamabnormals.buzzier_bees.common.item;

import com.teamabnormals.buzzier_bees.common.entity.Bottleable;
import com.teamabnormals.buzzier_bees.core.registry.BBDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;

public class BugBottleItem extends Item implements DispensibleContainerItem {
	private final EntityType<?> type;

	public BugBottleItem(EntityType<?> type, Item.Properties properties) {
		super(properties);
		this.type = type;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		Player player = context.getPlayer();
		ItemStack stack = context.getItemInHand();

		this.playEmptySound(player, level, context.getClickedPos());
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else {
			BlockPos pos = context.getClickedPos();
			BlockState state = level.getBlockState(pos);
			BlockPos relativePos = state.getCollisionShape(level, pos).isEmpty() ? pos : pos.relative(context.getClickedFace());
			if (this.emptyContents(player, level, relativePos, null, stack)) {
				this.checkExtraContent(player, level, stack, relativePos);

				if (!player.getAbilities().instabuild) {
					player.setItemInHand(context.getHand(), new ItemStack(Items.GLASS_BOTTLE));
				}

				return InteractionResult.CONSUME;
			} else {
				return InteractionResult.FAIL;
			}
		}
	}

	public void checkExtraContent(@Nullable Player player, Level level, ItemStack containerStack, BlockPos pos) {
		if (level instanceof ServerLevel) {
			this.spawn((ServerLevel) level, containerStack, pos);
			level.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
		}
	}

	@Override
	public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult result) {
		return level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
	}

	protected void playEmptySound(@Nullable Player player, LevelAccessor level, BlockPos pos) {
		level.playSound(player, pos, SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, 1.0F);
	}

	private void spawn(ServerLevel serverLevel, ItemStack bottleedMobStack, BlockPos pos) {
		if (this.type.spawn(serverLevel, bottleedMobStack, null, pos, MobSpawnType.BUCKET, true, false) instanceof Bottleable bottleable) {
			CustomData customdata = bottleedMobStack.getOrDefault(BBDataComponents.BOTTLE_ENTITY_DATA, CustomData.EMPTY);
			bottleable.loadFromBottleTag(customdata.copyTag());
			bottleable.setFromBottle(true);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (this.type == EntityType.BEE) {
			CustomData beeData = stack.getOrDefault(BBDataComponents.BOTTLE_ENTITY_DATA, CustomData.EMPTY);
			CompoundTag tag = beeData.copyTag();
			if (tag != null) {
				if (tag.contains("Age") && tag.getInt("Age") < 0) {
					tooltip.add((Component.translatable("tooltip.buzzier_bees.is_baby").withStyle(ChatFormatting.GRAY)));
				}

				if (tag.contains("AngerTime") && tag.getInt("AngerTime") > 0) {
					tooltip.add((Component.translatable("tooltip.buzzier_bees.is_angry").withStyle(ChatFormatting.GRAY)));
				}

				if (tag.contains("HasNectar") && tag.getBoolean("HasNectar")) {
					tooltip.add((Component.translatable("tooltip.buzzier_bees.has_nectar").withStyle(ChatFormatting.GRAY)));
				}

				if (tag.contains("HasStung") && tag.getBoolean("HasStung")) {
					tooltip.add((Component.translatable("tooltip.buzzier_bees.has_stung").withStyle(ChatFormatting.GRAY)));
				}
			}
		}
	}
}
