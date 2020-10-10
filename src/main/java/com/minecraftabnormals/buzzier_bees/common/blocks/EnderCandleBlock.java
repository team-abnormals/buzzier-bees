package com.minecraftabnormals.buzzier_bees.common.blocks;

import com.minecraftabnormals.buzzier_bees.core.other.BBCompat.CompatMods;

import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.registries.ForgeRegistries;

public class EnderCandleBlock extends SoulCandleBlock {

	public EnderCandleBlock(DyeColor color, Properties properties) {
		super(color, properties);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return (0.3F * state.get(CANDLES));
	}

	@Override
	public IParticleData getFlameParticle() {
		ParticleType<?> enderFlame = ForgeRegistries.PARTICLE_TYPES.getValue(new ResourceLocation(CompatMods.ENDERGETIC, "ender_flame"));
		return enderFlame != null ? (IParticleData) enderFlame : super.getFlameParticle();
	}
}
