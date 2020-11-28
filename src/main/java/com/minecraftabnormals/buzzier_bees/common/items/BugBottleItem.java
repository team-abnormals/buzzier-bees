package com.minecraftabnormals.buzzier_bees.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class BugBottleItem extends Item {
	private final EntityType<?> typeIn;
	
	public BugBottleItem(EntityType<?> typeIn, Item.Properties properties) {
		super(properties);
		this.typeIn = typeIn;
	}

	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			ItemStack itemstack = context.getItem();
			BlockPos blockpos = context.getPos();
			Direction direction = context.getFace();
			BlockState blockstate = world.getBlockState(blockpos);

			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.offset(direction);
			}

			EntityType<?> entitytype = this.getType(itemstack.getTag());
            world.playSound(context.getPlayer(), context.getPos(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!context.getPlayer().abilities.isCreativeMode) {
				context.getPlayer().setHeldItem(context.getHand(), new ItemStack(Items.GLASS_BOTTLE));
			}
			Entity entity = entitytype.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.BUCKET, true,!Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
			if (entity instanceof MobEntity) {
				((MobEntity)entity).enablePersistence();
			}
			return ActionResultType.SUCCESS;
		}
	}

	public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
		if (p_208076_1_ != null && p_208076_1_.contains("EntityTag", 10)) {
			CompoundNBT compoundnbt = p_208076_1_.getCompound("EntityTag");
			if (compoundnbt.contains("id", 8)) {
				return EntityType.byKey(compoundnbt.getString("id")).orElse(this.typeIn);
			}
		}
		
		return this.typeIn;
	}
}
