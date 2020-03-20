package com.bagel.buzzierbees.common.entities.goals.grizzly_bear;

import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;

public class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
    private GrizzlyBearEntity bear;

    public HurtByTargetGoal(GrizzlyBearEntity entityIn) {
        super(entityIn, new Class[0]);
        bear = entityIn;
    }

    public void startExecuting() {
        super.startExecuting();
        if (bear.isChild()) {
            this.alertOthers();
            this.resetTask();
        }

    }

    protected void setAttackTarget(MobEntity p_220793_1_, LivingEntity p_220793_2_) {
        if (p_220793_1_ instanceof GrizzlyBearEntity && !p_220793_1_.isChild()) {
            super.setAttackTarget(p_220793_1_, p_220793_2_);
        }

    }
}
