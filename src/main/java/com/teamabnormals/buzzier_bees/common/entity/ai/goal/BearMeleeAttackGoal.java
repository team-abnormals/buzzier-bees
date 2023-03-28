package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class BearMeleeAttackGoal extends MeleeAttackGoal {
	protected final GrizzlyBear bear;

	public BearMeleeAttackGoal(GrizzlyBear bear) {
		super(bear, 1.25D, true);
		this.bear = bear;
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double d0 = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= d0 && this.isTimeToAttack()) {
			this.resetAttackCooldown();
			this.mob.doHurtTarget(enemy);
			//bear.setStanding(false);
		} else if (distToEnemySqr <= d0 * 2.0D) {
			if (this.isTimeToAttack()) {
				//bear.setStanding(false);
				this.resetAttackCooldown();
			}

			if (this.getTicksUntilNextAttack() <= 10) {
				//bear.setStanding(true);
				bear.playWarningSound();
			}
		} else {
			this.resetAttackCooldown();
			//bear.setStanding(false);
		}
	}

	@Override
	public void stop() {
		//bear.setStanding(false);
		super.stop();
	}

	@Override
	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return 4.0F + attackTarget.getBbWidth();
	}
}