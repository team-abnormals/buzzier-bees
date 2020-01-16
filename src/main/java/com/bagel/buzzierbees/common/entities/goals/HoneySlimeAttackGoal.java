package com.bagel.buzzierbees.common.entities.goals;

import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.common.entities.controllers.HoneySlimeMoveHelperController;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class HoneySlimeAttackGoal extends Goal {
    private final HoneySlimeEntity slime;
    private int growTieredTimer;

    public HoneySlimeAttackGoal(HoneySlimeEntity slimeIn) {
        this.slime = slimeIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean shouldExecute() {
        LivingEntity livingentity = this.slime.getAttackTarget();
        if (livingentity == null) {

            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else {
            return livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).abilities.disableDamage ? false : this.slime.getMoveHelper() instanceof HoneySlimeMoveHelperController;
        }
    }

    public void startExecuting() {
        this.growTieredTimer = 300;
        this.slime.isAngry = true;
        super.startExecuting();
    }

    public boolean shouldContinueExecuting() {
        LivingEntity livingentity = this.slime.getAttackTarget();
        if (livingentity == null) {
            this.slime.isAngry = false;
            return false;
        } else if (!livingentity.isAlive()) {
            this.slime.isAngry = false;
            return false;
        } else if (livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).abilities.disableDamage) {
            this.slime.isAngry = false;
            return false;
        } else {
            if (--this.growTieredTimer > 0) {
                return true;
            }
            else {
                this.slime.isAngry = false;
                return false;
            }
        }
    }

    public void tick() {
        this.slime.faceEntity(this.slime.getAttackTarget(), 10.0F, 10.0F);
        ((HoneySlimeMoveHelperController) this.slime.getMoveHelper()).setDirection(this.slime.rotationYaw, this.slime.canDamagePlayer());
    }
}