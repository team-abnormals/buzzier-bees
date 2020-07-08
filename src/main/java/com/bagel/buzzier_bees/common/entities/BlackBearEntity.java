package com.bagel.buzzier_bees.common.entities;

import com.bagel.buzzier_bees.core.registry.BBEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class BlackBearEntity extends AbstractBearEntity {
   public BlackBearEntity(EntityType<? extends BlackBearEntity> type, World worldIn) {
      super(type, worldIn);
   }

   public AgeableEntity createChild(AgeableEntity ageable) {
      return BBEntities.BLACK_BEAR.get().create(this.world);
   }
}
