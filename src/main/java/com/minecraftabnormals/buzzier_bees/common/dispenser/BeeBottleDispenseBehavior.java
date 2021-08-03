package com.minecraftabnormals.buzzier_bees.common.dispenser;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;

public class BeeBottleDispenseBehavior extends OptionalDispenseBehavior {

	public ItemStack execute(IBlockSource source, ItemStack stack) {
		Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
		CompoundNBT tag = stack.getOrCreateTag();
		Entity entity = EntityType.BEE.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);

		if (entity instanceof BeeEntity) {
			BeeEntity bee = (BeeEntity) entity;

			int anger = tag.contains("AngerTime") ? tag.getInt("AngerTime") : 0;
			int age = tag.contains("Age") ? tag.getInt("Age") : 0;
			boolean nectar = tag.contains("HasNectar") && tag.getBoolean("HasNectar");
			boolean stung = tag.contains("HasStung") && tag.getBoolean("HasStung");

			bee.setAge(age);
			bee.setHasNectar(nectar);
			bee.setHasStung(stung);
			bee.setRemainingPersistentAngerTime(anger);
		}
		return new ItemStack(Items.GLASS_BOTTLE);
	}
}
