package com.minecraftabnormals.buzzier_bees.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.registries.ForgeRegistries;

public class CompatCandleBlock extends SoulCandleBlock {
	private final float enchantPowerBonus;
	private final ResourceLocation particle;

	public CompatCandleBlock(float enchantBonus, ResourceLocation particle, Properties properties) {
		super(properties);
		this.enchantPowerBonus = enchantBonus;
		this.particle = particle;
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return enchantPowerBonus * state.get(CANDLES);
	}

	@Override
	public IParticleData getFlameParticle() {
		ParticleType<?> enderFlame = ForgeRegistries.PARTICLE_TYPES.getValue(particle);
		return enderFlame != null ? (IParticleData) enderFlame : super.getFlameParticle();
	}
}
