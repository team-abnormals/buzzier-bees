package com.minecraftabnormals.buzzier_bees.common.entities.goals.bear;

import com.minecraftabnormals.buzzier_bees.common.entities.GrizzlyBearEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
	protected final GrizzlyBearEntity bear;

	public AttackPlayerGoal(GrizzlyBearEntity bear) {
		super(bear, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>) null);
		this.bear = bear;
	}

	public boolean shouldExecute() {
		if (bear.isChild()) {
			return false;
		} else {
			if (super.shouldExecute()) {
				for (GrizzlyBearEntity polarbearentity : bear.world.getEntitiesWithinAABB(GrizzlyBearEntity.class, bear.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
					if (polarbearentity.isChild()) {
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
