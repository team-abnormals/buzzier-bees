package com.bagel.buzzierbees.common.entities.goals.grizzly_bear;

import java.util.EnumSet;

import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;

import net.minecraft.entity.ai.goal.Goal;

public class FindFoodItemsGoal extends Goal {
    public GrizzlyBearEntity GRIZZLY_BEAR;

    public FindFoodItemsGoal(GrizzlyBearEntity entity) {
        this.GRIZZLY_BEAR = entity;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean shouldExecute() {
        return false;
    }
}
