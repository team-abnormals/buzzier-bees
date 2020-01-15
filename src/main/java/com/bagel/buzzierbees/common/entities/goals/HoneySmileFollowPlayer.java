package com.bagel.buzzierbees.common.entities.goals;

import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.common.entities.controllers.HoneySlimeMoveHelperController;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class HoneySmileFollowPlayer extends Goal {
    private static final EntityPredicate ENTITY_PREDICATE = (new EntityPredicate()).setDistance(10.0D).allowInvulnerable().allowFriendlyFire().setSkipAttackChecks().setLineOfSiteRequired();
    private final HoneySlimeEntity slime;
    private PlayerEntity closestPlayer;

    public HoneySmileFollowPlayer(HoneySlimeEntity slimeIn) {
        this.slime = slimeIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        this.closestPlayer = this.slime.world.getClosestPlayer(ENTITY_PREDICATE, this.slime);
        return !this.slime.isPassenger() && this.slime.getLeashed();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.slime.getLookController().setLookPositionWithEntity(this.closestPlayer, (float)(this.slime.getHorizontalFaceSpeed() + 20), (float)this.slime.getVerticalFaceSpeed());
        if (this.slime.getDistanceSq(this.closestPlayer) < 6.25D) {
            this.slime.getNavigator().clearPath();
        } else {
            this.slime.faceEntity(this.closestPlayer, 10.0F, 10.0F);
            ((HoneySlimeMoveHelperController) this.slime.getMoveHelper()).setDirection(this.slime.rotationYaw, true);
            ((HoneySlimeMoveHelperController) this.slime.getMoveHelper()).setSpeed(1.0D);
        }
    }
}
