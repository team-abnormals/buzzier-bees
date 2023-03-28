package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class BearAttackPlayerGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

	public BearAttackPlayerGoal(Mob mob, Class<T> targetType, int randomInterval, boolean mustSee, boolean mustReach, @Nullable Predicate<LivingEntity> predicate) {
		super(mob, targetType, randomInterval, mustSee, mustReach, predicate);
	}

	@Override
	public boolean canUse() {
		if (this.mob.isBaby()) {
			return false;
		} else {
			if (super.canUse()) {
				for (GrizzlyBear bear : this.mob.level.getEntitiesOfClass(GrizzlyBear.class, this.mob.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
					if (bear.isBaby()) {
						return true;
					}
				}
			}
			return false;
		}
	}

	@Override
	protected double getFollowDistance() {
		return super.getFollowDistance() * 0.5D;
	}
}
