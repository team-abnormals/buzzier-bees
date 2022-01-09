package com.teamabnormals.buzzier_bees.common.entities.goals.bear;

import com.teamabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class BearPanicGoal extends PanicGoal {
	protected final GrizzlyBearEntity bear;

	public BearPanicGoal(GrizzlyBearEntity bear) {
		super(bear, 2.0D);
		this.bear = bear;
	}

	@Override
	public boolean canUse() {
		return (bear.isBaby() || bear.isOnFire()) && super.canUse();
	}
}
