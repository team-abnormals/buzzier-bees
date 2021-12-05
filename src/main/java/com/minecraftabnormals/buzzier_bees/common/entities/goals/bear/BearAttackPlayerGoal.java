package com.minecraftabnormals.buzzier_bees.common.entities.goals.bear;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class BearAttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
	protected final GrizzlyBearEntity bear;

	public BearAttackPlayerGoal(GrizzlyBearEntity bear) {
		super(bear, PlayerEntity.class, 20, true, true, null);
		this.bear = bear;
	}

	@Override
	public boolean canUse() {
		if (bear.isBaby()) {
			return false;
		} else {
			if (super.canUse()) {
				for (GrizzlyBearEntity polarbearentity : bear.level.getEntitiesOfClass(GrizzlyBearEntity.class, bear.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
					if (polarbearentity.isBaby()) {
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
