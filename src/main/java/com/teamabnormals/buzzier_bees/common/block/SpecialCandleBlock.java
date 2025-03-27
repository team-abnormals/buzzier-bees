package com.teamabnormals.buzzier_bees.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.function.ToIntFunction;

public class SpecialCandleBlock extends CandleBlock {
	public static final ToIntFunction<BlockState> DIM_LIGHT_EMISSION = state -> state.getValue(LIT) ? 2 * state.getValue(CANDLES) : 0;
	private final ResourceLocation particleName;

	public SpecialCandleBlock(ResourceLocation particleName, Properties properties) {
		super(properties);
		this.particleName = particleName;
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (state.getValue(LIT)) {
			this.getParticleOffsets(state).forEach((vec3) -> addParticlesAndSound(level, this.particleName, vec3.add(pos.getX(), pos.getY(), pos.getZ()), random));
		}
	}

	public static void addParticlesAndSound(Level level, ResourceLocation particleName, Vec3 vec3, RandomSource random) {
		float f = random.nextFloat();
		if (f < 0.3F) {
			level.addParticle(ParticleTypes.SMOKE, vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
			if (f < 0.17F) {
				level.playLocalSound(vec3.x + 0.5D, vec3.y + 0.5D, vec3.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
			}
		}

		ParticleType<?> options = BuiltInRegistries.PARTICLE_TYPE.get(particleName);
		if (options != null) {
			level.addParticle((ParticleOptions) options, vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
		}
	}
}