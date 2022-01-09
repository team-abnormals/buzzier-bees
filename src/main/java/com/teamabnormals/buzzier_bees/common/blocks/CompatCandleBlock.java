package com.teamabnormals.buzzier_bees.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
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
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return enchantPowerBonus * state.getValue(CANDLES);
	}

	@Override
	public ParticleOptions getFlameParticle() {
		ParticleType<?> enderFlame = ForgeRegistries.PARTICLE_TYPES.getValue(particle);
		return enderFlame != null ? (ParticleOptions) enderFlame : super.getFlameParticle();
	}
}
