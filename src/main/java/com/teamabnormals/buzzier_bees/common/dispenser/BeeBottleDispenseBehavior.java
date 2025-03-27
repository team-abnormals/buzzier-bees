package com.teamabnormals.buzzier_bees.common.dispenser;

import com.teamabnormals.buzzier_bees.core.registry.BBDataComponents;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;

public class BeeBottleDispenseBehavior extends OptionalDispenseItemBehavior {

	public ItemStack execute(BlockSource source, ItemStack stack) {
		Direction direction = source.state().getValue(DispenserBlock.FACING);
		CompoundTag tag = stack.get(BBDataComponents.BOTTLE_BEE_DATA).copyTag();
		Entity entity = EntityType.BEE.spawn(source.level(), stack, null, source.pos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);

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
