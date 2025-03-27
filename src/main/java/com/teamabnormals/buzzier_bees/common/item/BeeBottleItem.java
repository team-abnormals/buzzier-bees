package com.teamabnormals.buzzier_bees.common.item;

import com.teamabnormals.buzzier_bees.core.registry.BBDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BeeBottleItem extends Item {

	public BeeBottleItem(EntityType<?> typeIn, Item.Properties properties) {
		super(properties);
	}

	public InteractionResult useOn(UseOnContext context) {

		Level world = context.getLevel();
		world.playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.BLOCKS, 1.0F, 1.0F);
		if (world.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else {
			ItemStack stack = context.getItemInHand();
			BlockPos blockpos = context.getClickedPos();
			Direction direction = context.getClickedFace();
			BlockState blockstate = world.getBlockState(blockpos);

			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.relative(direction);
			}

			if (!context.getPlayer().getAbilities().instabuild) {
				context.getPlayer().setItemInHand(context.getHand(), new ItemStack(Items.GLASS_BOTTLE));
			}
			Entity entity = EntityType.BEE.spawn((ServerLevel) world, stack, context.getPlayer(), blockpos1, MobSpawnType.BUCKET, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);

			if (entity instanceof Bee bee) {
				CustomData beeData = stack.getOrDefault(BBDataComponents.BOTTLE_BEE_DATA, CustomData.EMPTY);
				CompoundTag tag = beeData.copyTag();
				int anger = tag.contains("AngerTime") ? tag.getInt("AngerTime") : 0;
				UUID angryAt = tag.contains("AngryAt") ? tag.getUUID("AngryAt") : null;
				int age = tag.contains("Age") ? tag.getInt("Age") : 0;
				boolean nectar = tag.contains("HasNectar") && tag.getBoolean("HasNectar");
				boolean stung = tag.contains("HasStung") && tag.getBoolean("HasStung");
				float health = tag.contains("Health") ? tag.getFloat("Health") : 10.0F;

				bee.setAge(age);
				bee.setHasNectar(nectar);
				bee.setHasStung(stung);
				bee.setRemainingPersistentAngerTime(anger);
				if (angryAt != null) bee.setPersistentAngerTarget(angryAt);
				bee.setHealth(health);
			}
			return InteractionResult.CONSUME;
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		CustomData beeData = stack.getOrDefault(BBDataComponents.BOTTLE_BEE_DATA, CustomData.EMPTY);
		CompoundTag tag = beeData.copyTag();
		if (tag != null) {
			ChatFormatting[] atextformatting = new ChatFormatting[]{ChatFormatting.GRAY};
			if (tag.contains("Age")) {
				boolean baby = tag.getInt("Age") < 0;
				if (baby)
					tooltip.add((Component.translatable("tooltip.buzzier_bees.is_baby").withStyle(atextformatting)));
			}
			if (tag.contains("AngerTime")) {
				boolean angry = tag.getInt("AngerTime") > 0;
				if (angry)
					tooltip.add((Component.translatable("tooltip.buzzier_bees.is_angry").withStyle(atextformatting)));
			}
			if (tag.contains("HasNectar")) {
				boolean nectar = tag.getBoolean("HasNectar");
				if (nectar)
					tooltip.add((Component.translatable("tooltip.buzzier_bees.has_nectar").withStyle(atextformatting)));
			}
			if (tag.contains("HasStung")) {
				boolean stung = tag.getBoolean("HasStung");
				if (stung)
					tooltip.add((Component.translatable("tooltip.buzzier_bees.has_stung").withStyle(atextformatting)));
			}
		}
	}
}
