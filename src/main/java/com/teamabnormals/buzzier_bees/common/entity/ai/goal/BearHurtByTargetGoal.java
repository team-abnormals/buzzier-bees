package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class BearHurtByTargetGoal extends HurtByTargetGoal {

	public BearHurtByTargetGoal(GrizzlyBear bear) {
		super(bear);
	}

	@Override
	public void start() {
		super.start();
		if (this.mob.isBaby()) {
			this.alertOthers();
			this.stop();
		}
	}

	@Override
	protected void alertOther(Mob mob, LivingEntity target) {
		if (mob instanceof GrizzlyBear && !mob.isBaby()) {
			super.alertOther(mob, target);
		}
	}
}
