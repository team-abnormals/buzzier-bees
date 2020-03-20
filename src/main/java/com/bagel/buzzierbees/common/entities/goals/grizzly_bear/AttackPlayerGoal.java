package com.bagel.buzzierbees.common.entities.goals.grizzly_bear;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.bagel.buzzierbees.common.entities.GrizzlyBearEntity;

import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;

public class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
    private GrizzlyBearEntity bear;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public AttackPlayerGoal(GrizzlyBearEntity entityIn) {
        super(entityIn, PlayerEntity.class, 20, true, true, (Predicate)null);
        bear = entityIn;
    }

    public boolean shouldExecute() {
        if (bear.isChild()) {
            return false;
        } else {
            if (super.shouldExecute()) {
                List<GrizzlyBearEntity> lvt_1_1_ = bear.world.getEntitiesWithinAABB(GrizzlyBearEntity.class, bear.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
                @SuppressWarnings("rawtypes")
				Iterator var2 = lvt_1_1_.iterator();

                while(var2.hasNext()) {
                    GrizzlyBearEntity lvt_3_1_ = (GrizzlyBearEntity)var2.next();
                    if (lvt_3_1_.isChild()) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    protected double getTargetDistance() {
        return super.getTargetDistance() * 0.5D;
    }
}