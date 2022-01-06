package com.teamabnormals.buzzier_bees.common.dispenser;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;

public class BeeBottleDispenseBehavior extends OptionalDispenseItemBehavior {

	public ItemStack execute(BlockSource source, ItemStack stack) {
		Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
		CompoundTag tag = stack.getOrCreateTag();
		Entity entity = EntityType.BEE.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);

		if (entity instanceof Bee bee) {
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
