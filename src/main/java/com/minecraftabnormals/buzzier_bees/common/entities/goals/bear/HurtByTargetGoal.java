package com.minecraftabnormals.buzzier_bees.common.entities.goals.bear;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;

public class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
	protected final GrizzlyBearEntity bear;

	public HurtByTargetGoal(GrizzlyBearEntity bear) {
		super(bear);
		this.bear = bear;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void start() {
		super.start();
		if (bear.isBaby()) {
			this.alertOthers();
			this.stop();
		}

	}

	protected void alertOther(MobEntity mobIn, LivingEntity targetIn) {
		if (mobIn instanceof GrizzlyBearEntity && !mobIn.isBaby()) {
			super.alertOther(mobIn, targetIn);
		}

	}
}
