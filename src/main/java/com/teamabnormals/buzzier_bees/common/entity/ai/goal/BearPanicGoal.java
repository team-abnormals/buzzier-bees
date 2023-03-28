package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class BearPanicGoal extends PanicGoal {

	public BearPanicGoal(GrizzlyBear bear) {
		super(bear, 2.0D);
	}

	@Override
	public boolean canUse() {
		return (this.mob.isBaby() || this.mob.isOnFire()) && super.canUse();
	}
}
