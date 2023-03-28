package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class BearFindItemsGoal extends Goal {
	protected final GrizzlyBear bear;

	public BearFindItemsGoal(GrizzlyBear bear) {
		this.bear = bear;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	public boolean canUse() {
		if (!this.bear.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
			return false;
		} else if (this.bear.getTarget() == null && this.bear.getLastHurtByMob() == null) {
			if (this.bear.getRandom().nextInt(10) != 0) {
				return false;
			} else {
				List<ItemEntity> list = this.bear.level.getEntitiesOfClass(ItemEntity.class, this.bear.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBear.ALLOWED_ITEMS);
				return !list.isEmpty() && this.bear.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
			}
		} else {
			return false;
		}
	}

	public void tick() {
		List<ItemEntity> list = this.bear.level.getEntitiesOfClass(ItemEntity.class, this.bear.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBear.ALLOWED_ITEMS);
		ItemStack itemstack = this.bear.getItemBySlot(EquipmentSlot.MAINHAND);
		if (itemstack.isEmpty() && !list.isEmpty()) {
			this.bear.getNavigation().moveTo(list.get(0), 1.2F);
		}

	}

	public void start() {
		List<ItemEntity> list = this.bear.level.getEntitiesOfClass(ItemEntity.class, this.bear.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), GrizzlyBear.ALLOWED_ITEMS);
		if (!list.isEmpty()) {
			this.bear.getNavigation().moveTo(list.get(0), 1.2F);
		}
	}
}