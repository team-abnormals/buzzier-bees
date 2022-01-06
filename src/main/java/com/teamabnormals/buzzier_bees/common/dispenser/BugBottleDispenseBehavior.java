package com.teamabnormals.buzzier_bees.common.dispenser;

import com.teamabnormals.buzzier_bees.common.item.BugBottleItem;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;

public class BugBottleDispenseBehavior extends OptionalDispenseItemBehavior {

	public ItemStack execute(BlockSource source, ItemStack stack) {
		Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
		EntityType<?> entitytype = ((BugBottleItem) stack.getItem()).getType(stack.getTag());
		entitytype.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
		return new ItemStack(Items.GLASS_BOTTLE);
	}
}
