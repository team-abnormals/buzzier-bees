package com.minecraftabnormals.buzzier_bees.common.dispenser;

import com.minecraftabnormals.buzzier_bees.common.items.BugBottleItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;

public class BugBottleDispenseBehavior extends OptionalDispenseBehavior {

	public ItemStack execute(IBlockSource source, ItemStack stack) {
		Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
		EntityType<?> entitytype = ((BugBottleItem) stack.getItem()).getType(stack.getTag());
		entitytype.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
		return new ItemStack(Items.GLASS_BOTTLE);
	}
}
