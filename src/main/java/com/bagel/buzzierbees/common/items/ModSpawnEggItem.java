package com.bagel.buzzierbees.common.items;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.bagel.buzzierbees.common.entities.ModEntities;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModSpawnEggItem extends SpawnEggItem {
   private Supplier<EntityType<?>> entity;

   public ModSpawnEggItem(Supplier<EntityType<?>> entityTypeIn, int primaryColorIn, int secondaryColorIn, Item.Properties builder) {
      super(entityTypeIn.get(), primaryColorIn, secondaryColorIn, builder);
      entity = entityTypeIn;
   }

   @Override
   public EntityType<?> getType(CompoundNBT compound) {
      if (compound != null && compound.contains("EntityTag", 10)) {
         CompoundNBT entityTag = compound.getCompound("EntityTag");

         if (entityTag.contains("id", 8)) {
            return EntityType.byKey(entityTag.getString("id")).orElse(entity.get());
         }
      }
      return entity.get();
   }
}