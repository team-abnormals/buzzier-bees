package com.teamabnormals.buzzier_bees.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Objects;

public class BugBottleItem extends Item {
	private final EntityType<?> typeIn;

	public BugBottleItem(EntityType<?> typeIn, Item.Properties properties) {
		super(properties);
		this.typeIn = typeIn;
	}

	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		if (world.isClientSide) {
			return InteractionResult.SUCCESS;
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

			EntityType<?> entitytype = this.getType(itemstack.getTag());
			world.playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
			if (!context.getPlayer().getAbilities().instabuild) {
				context.getPlayer().setItemInHand(context.getHand(), new ItemStack(Items.GLASS_BOTTLE));
			}
			Entity entity = entitytype.spawn((ServerLevel) world, itemstack, context.getPlayer(), blockpos1, MobSpawnType.BUCKET, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
			if (entity instanceof Mob) {
				((Mob) entity).setPersistenceRequired();
			}
			return InteractionResult.SUCCESS;
		}
	}

	public EntityType<?> getType(@Nullable CompoundTag p_208076_1_) {
		if (p_208076_1_ != null && p_208076_1_.contains("EntityTag", 10)) {
			CompoundTag compoundnbt = p_208076_1_.getCompound("EntityTag");
			if (compoundnbt.contains("id", 8)) {
				return EntityType.byString(compoundnbt.getString("id")).orElse(this.typeIn);
			}
		}

		return this.typeIn;
	}
}
