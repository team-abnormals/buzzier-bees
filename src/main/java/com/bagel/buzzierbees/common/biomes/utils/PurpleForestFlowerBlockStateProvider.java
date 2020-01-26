package com.bagel.buzzierbees.common.biomes.utils;

import java.util.Random;

import com.bagel.buzzierbees.core.registry.ModBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;

public class PurpleForestFlowerBlockStateProvider extends BlockStateProvider {
	   private static final BlockState[] field_227401_b_ = new BlockState[]{
			   Blocks.ALLIUM.getDefaultState(), 
			   Blocks.PINK_TULIP.getDefaultState(),
			   ModBlocks.PINK_CLOVER.get().getDefaultState()};

	   public PurpleForestFlowerBlockStateProvider() {
	      super(BlockStateProviderType.field_227397_d_);
	   }

	   public <T> PurpleForestFlowerBlockStateProvider(Dynamic<T> p_i225856_1_) {
	      this();
	   }

	   public BlockState func_225574_a_(Random p_225574_1_, BlockPos p_225574_2_) {
	      double d0 = MathHelper.clamp((1.0D + Biome.INFO_NOISE.noiseAt((double)p_225574_2_.getX() / 48.0D, (double)p_225574_2_.getZ() / 48.0D, false)) / 2.0D, 0.0D, 0.9999D);
	      return field_227401_b_[(int)(d0 * (double)field_227401_b_.length)];
	   }

	   public <T> T serialize(DynamicOps<T> p_218175_1_) {
	      Builder<T, T> builder = ImmutableMap.builder();
	      builder.put(p_218175_1_.createString("type"), p_218175_1_.createString(Registry.BLOCK_STATE_PROVIDER_TYPE.getKey(this.field_227393_a_).toString()));
	      return (new Dynamic<>(p_218175_1_, p_218175_1_.createMap(builder.build()))).getValue();
	   }
	}
