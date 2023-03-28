package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class BearHurtByTargetGoal extends HurtByTargetGoal {
	protected final GrizzlyBear bear;

	public BearHurtByTargetGoal(GrizzlyBear bear) {
		super(bear);
		this.bear = bear;
	}

	@Override
	public void start() {
		super.start();
		if (bear.isBaby()) {
			this.alertOthers();
			this.stop();
		}

	}

	@Override
	protected void alertOther(Mob mobIn, LivingEntity targetIn) {
		if (mobIn instanceof GrizzlyBear && !mobIn.isBaby()) {
			super.alertOther(mobIn, targetIn);
		}
	}
}
