package com.minecraftabnormals.buzzier_bees.common.entities.goals.bear;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;

public class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
	protected final GrizzlyBearEntity bear;

	public PanicGoal(GrizzlyBearEntity bear) {
		super(bear, 2.0D);
		this.bear = bear;
	}

	public boolean shouldExecute() {
		return (bear.isChild() || bear.isBurning()) && super.shouldExecute();
	}
}
