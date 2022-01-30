package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBearEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class BearAttackPlayerGoal extends NearestAttackableTargetGoal<Player> {
	protected final GrizzlyBearEntity bear;

	public BearAttackPlayerGoal(GrizzlyBearEntity bear) {
		super(bear, Player.class, 20, true, true, null);
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
