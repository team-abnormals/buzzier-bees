package com.minecraftabnormals.buzzier_bees.common.entities;

import com.minecraftabnormals.buzzier_bees.core.registry.BBEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlackBearEntity extends AbstractBearEntity {
   public BlackBearEntity(EntityType<? extends BlackBearEntity> type, World worldIn) {
      super(type, worldIn);
   }

   @Override
   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
      return BBEntities.BLACK_BEAR.get().create(world);
   }
}
