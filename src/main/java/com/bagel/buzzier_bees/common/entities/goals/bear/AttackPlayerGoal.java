package com.bagel.buzzier_bees.common.entities.goals.bear;

import java.util.function.Predicate;

import com.bagel.buzzier_bees.common.entities.AbstractBearEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;

public class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
	protected final AbstractBearEntity bear;
	
	public AttackPlayerGoal(AbstractBearEntity bear) {
		super(bear, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
		this.bear = bear;
	}

     public boolean shouldExecute() {
        if (bear.isChild()) {
           return false;
        } else {
           if (super.shouldExecute()) {
              for(AbstractBearEntity polarbearentity : bear.world.getEntitiesWithinAABB(AbstractBearEntity.class, bear.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
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
