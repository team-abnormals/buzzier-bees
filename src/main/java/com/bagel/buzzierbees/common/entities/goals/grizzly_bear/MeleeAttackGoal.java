package com.bagel.buzzierbees.common.entities.goals.grizzly_bear;

import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;

import net.minecraft.entity.LivingEntity;

public class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
    private GrizzlyBearEntity bear;

    public MeleeAttackGoal(GrizzlyBearEntity entityIn) {
        super(entityIn, 1.25D, true);
        bear = entityIn;
    }

    protected void checkAndPerformAttack(LivingEntity entity, double reach) {
        double attackReachSqr = this.getAttackReachSqr(entity);
        if (reach <= attackReachSqr && this.attackTick <= 0) {
            this.attackTick = 20;
            this.attacker.attackEntityAsMob(entity);
            bear.setStanding(false);
        } else if (reach <= attackReachSqr * 2.0D) {
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

    protected double getAttackReachSqr(LivingEntity p_179512_1_) {
        return (double)(4.0F + p_179512_1_.getWidth());
    }
}
