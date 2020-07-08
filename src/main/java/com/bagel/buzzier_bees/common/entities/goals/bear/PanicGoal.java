package com.bagel.buzzier_bees.common.entities.goals.bear;

import com.bagel.buzzier_bees.common.entities.AbstractBearEntity;

public class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
	protected final AbstractBearEntity bear;
    public PanicGoal(AbstractBearEntity bear) {
        super(bear, 2.0D);
        this.bear = bear;
     }

     public boolean shouldExecute() {
        return !bear.isChild() && !bear.isBurning() ? false : super.shouldExecute();
     }
  }
