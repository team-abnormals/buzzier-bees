package com.bagel.buzzierbees.common.entities.goals.bear;

import com.bagel.buzzierbees.common.entities.AbstractBearEntity;

import net.minecraft.entity.LivingEntity;

public class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
	protected final AbstractBearEntity bear;
    public MeleeAttackGoal(AbstractBearEntity bear) {
        super(bear, 1.25D, true);
        this.bear = bear;
     }

     protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
           this.attackTick = 20;
           this.attacker.attackEntityAsMob(enemy);
           bear.setStanding(false);
        } else if (distToEnemySqr <= d0 * 2.0D) {
           if (this.attackTick <= 0) {
              bear.setStanding(false);
              this.attackTick = 20;
           }

           if (this.attackTick <= 10) {
              bear.setStanding(true);
              bear.playWarningSound();
           }
        } else {
           this.attackTick = 20;
           bear.setStanding(false);
        }

     }

     public void resetTask() {
        bear.setStanding(false);
        super.resetTask();
     }

     protected double getAttackReachSqr(LivingEntity attackTarget) {
        return (double)(4.0F + attackTarget.getWidth());
     }
  }