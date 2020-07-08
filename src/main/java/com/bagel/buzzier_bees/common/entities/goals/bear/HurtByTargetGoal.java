package com.bagel.buzzier_bees.common.entities.goals.bear;

import com.bagel.buzzier_bees.common.entities.AbstractBearEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;

public class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
	protected final AbstractBearEntity bear;
	
    public HurtByTargetGoal(AbstractBearEntity bear) {
        super(bear);
        this.bear = bear;
     }

     /**
      * Execute a one shot task or start executing a continuous task
      */
     public void startExecuting() {
        super.startExecuting();
        if (bear.isChild()) {
           this.alertOthers();
           this.resetTask();
        }

     }

     protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
        if (mobIn instanceof AbstractBearEntity && !mobIn.isChild()) {
           super.setAttackTarget(mobIn, targetIn);
        }

     }
  }
