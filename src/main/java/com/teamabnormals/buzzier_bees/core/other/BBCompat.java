package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.DataUtil.AlternativeDispenseBehavior;
import com.teamabnormals.buzzier_bees.common.entity.Bottleable;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BBCompat {

	public static void register() {
		registerDispenseBehaviors();
	}

	private static void registerDispenseBehaviors() {
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_BEE.get(), EMPTY_BUG_BOTTLE_BEHAVIOR);
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), EMPTY_BUG_BOTTLE_BEHAVIOR);
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), EMPTY_BUG_BOTTLE_BEHAVIOR);
		DataUtil.registerAlternativeDispenseBehavior(new AlternativeDispenseBehavior(BuzzierBees.MOD_ID, Items.GLASS_BOTTLE, (source, stack) -> !BlockUtil.getEntitiesAtOffsetPos(source, LivingEntity.class, entity -> entity instanceof Bottleable).isEmpty(), FILL_BUG_BOTTLE_BEHAVIOR));
	}

	public static DispenseItemBehavior EMPTY_BUG_BOTTLE_BEHAVIOR = new DefaultDispenseItemBehavior() {
		private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

		@Override
		public ItemStack execute(BlockSource source, ItemStack stack) {
			DispensibleContainerItem item = (DispensibleContainerItem) stack.getItem();
			BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
			Level level = source.level();
			if (item.emptyContents(null, level, pos, null, stack)) {
				item.checkExtraContent(null, level, stack, pos);
				return this.consumeWithRemainder(source, stack, new ItemStack(Items.GLASS_BOTTLE));
			} else {
				return this.defaultDispenseItemBehavior.dispense(source, stack);
			}
		}
	};

	public static DispenseItemBehavior FILL_BUG_BOTTLE_BEHAVIOR = new DefaultDispenseItemBehavior() {
		private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

		@Override
		protected ItemStack execute(BlockSource source, ItemStack stack) {
			BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
			Level level = source.level();
			List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), entity -> entity instanceof Bottleable);
			if (!entities.isEmpty()) {
				LivingEntity entity = entities.getFirst();
				if (entity instanceof Bottleable bottleable) {
					if (stack.getItem() == Items.GLASS_BOTTLE && entity.isAlive()) {
						stack.shrink(1);
						entity.playSound(bottleable.getPickupSound(), 1.0F, 1.0F);
						ItemStack newStack = bottleable.getBottleItemStack();
						bottleable.saveToBottleTag(newStack);
						entity.discard();
						source.blockEntity().insertItem(newStack);
						return stack;
					}

				}
			}
			return defaultDispenseItemBehavior.dispense(source, stack);
		}
	};
}
