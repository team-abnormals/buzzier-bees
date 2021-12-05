package com.minecraftabnormals.buzzier_bees.common.entities.goals.bear;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;

public class BearHurtByTargetGoal extends HurtByTargetGoal {
	protected final GrizzlyBearEntity bear;

	public BearHurtByTargetGoal(GrizzlyBearEntity bear) {
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
	protected void alertOther(MobEntity mobIn, LivingEntity targetIn) {
		if (mobIn instanceof GrizzlyBearEntity && !mobIn.isBaby()) {
			super.alertOther(mobIn, targetIn);
		}
	}
}
