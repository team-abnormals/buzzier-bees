package com.teamabnormals.buzzier_bees.common.dispenser;

import com.teamabnormals.buzzier_bees.common.item.BugBottleItem;
import com.teamabnormals.buzzier_bees.core.registry.BBDataComponents;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.DispenserBlock;

public class BugBottleDispenseBehavior extends OptionalDispenseItemBehavior {

	public ItemStack execute(BlockSource source, ItemStack stack) {
		Direction direction = source.blockEntity().getBlockState().getValue(DispenserBlock.FACING);
		EntityType<?> entitytype = ((BugBottleItem) stack.getItem()).getType(stack.getOrDefault(BBDataComponents.BOTTLE_BUG_DATA.get(), CustomData.EMPTY).copyTag());
		entitytype.spawn(source.level(), stack, null, source.pos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
		return new ItemStack(Items.GLASS_BOTTLE);
	}
}
