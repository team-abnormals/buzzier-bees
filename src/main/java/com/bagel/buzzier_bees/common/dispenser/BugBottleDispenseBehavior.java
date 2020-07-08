package com.bagel.buzzier_bees.common.dispenser;

import com.bagel.buzzier_bees.common.items.BugBottleItem;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;

public class BugBottleDispenseBehavior extends OptionalDispenseBehavior {
	
	public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().get(DispenserBlock.FACING);
        EntityType<?> entitytype = ((BugBottleItem)stack.getItem()).getType(stack.getTag());
        entitytype.spawn(source.getWorld(), stack, (PlayerEntity)null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
        return new ItemStack(Items.GLASS_BOTTLE);
     }
}
