package com.minecraftabnormals.buzzier_bees.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BeeBottleItem extends Item {

	public BeeBottleItem(EntityType<?> typeIn, Item.Properties properties) {
		super(properties);
	}

	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		if (world.isClientSide()) {
			return ActionResultType.SUCCESS;
		} else {
			ItemStack itemstack = context.getItemInHand();
			BlockPos blockpos = context.getClickedPos();
			Direction direction = context.getClickedFace();
			BlockState blockstate = world.getBlockState(blockpos);

			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.relative(direction);
			}

			world.playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
			CompoundNBT tag = itemstack.getOrCreateTag();
			if (!context.getPlayer().abilities.instabuild) {
				context.getPlayer().setItemInHand(context.getHand(), new ItemStack(Items.GLASS_BOTTLE));
			}
			Entity entity = EntityType.BEE.spawn((ServerWorld) world, itemstack, context.getPlayer(), blockpos1, SpawnReason.BUCKET, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);

			if (entity instanceof BeeEntity) {
				BeeEntity bee = (BeeEntity) entity;

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
				bee.setPersistenceRequired();
			}
			return ActionResultType.SUCCESS;
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null) {
			TextFormatting[] atextformatting = new TextFormatting[]{TextFormatting.GRAY};
			if (compoundnbt.contains("Age")) {
				boolean baby = compoundnbt.getInt("Age") < 0;
				if (baby)
					tooltip.add((new TranslationTextComponent("tooltip.buzzier_bees.is_baby").withStyle(atextformatting)));
			}
			if (compoundnbt.contains("Anger")) {
				boolean angry = compoundnbt.getInt("AngerTime") > 0;
				if (angry)
					tooltip.add((new TranslationTextComponent("tooltip.buzzier_bees.is_angry").withStyle(atextformatting)));
			}
			if (compoundnbt.contains("HasNectar")) {
				boolean nectar = compoundnbt.getBoolean("HasNectar");
				if (nectar)
					tooltip.add((new TranslationTextComponent("tooltip.buzzier_bees.has_nectar").withStyle(atextformatting)));
			}
			if (compoundnbt.contains("HasStung")) {
				boolean stung = compoundnbt.getBoolean("HasStung");
				if (stung)
					tooltip.add((new TranslationTextComponent("tooltip.buzzier_bees.has_stung").withStyle(atextformatting)));
			}
		}
	}
}
