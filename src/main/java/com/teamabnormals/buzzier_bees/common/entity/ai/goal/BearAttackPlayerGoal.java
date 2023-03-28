package com.teamabnormals.buzzier_bees.common.entity.ai.goal;

import com.teamabnormals.buzzier_bees.common.entity.animal.GrizzlyBear;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class BearAttackPlayerGoal extends NearestAttackableTargetGoal<Player> {
	protected final GrizzlyBear bear;

	public BearAttackPlayerGoal(GrizzlyBear bear) {
		super(bear, Player.class, 20, true, true, null);
		this.bear = bear;
	}

	@Override
	public boolean canUse() {
		if (bear.isBaby()) {
			return false;
		} else {
			if (super.canUse()) {
				for (GrizzlyBear polarbearentity : bear.level.getEntitiesOfClass(GrizzlyBear.class, bear.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
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
