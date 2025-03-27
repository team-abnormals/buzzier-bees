package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.client.particle.ButtercupBloomParticle;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, BuzzierBees.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BUTTERCUP_BLOOM = PARTICLE_TYPES.register("buttercup_bloom", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMALL_SOUL_FIRE_FLAME = PARTICLE_TYPES.register("small_soul_fire_flame", () -> new SimpleParticleType(false));

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(BUTTERCUP_BLOOM.get(), ButtercupBloomParticle.Factory::new);
		event.registerSpriteSet(SMALL_SOUL_FIRE_FLAME.get(), FlameParticle.SmallFlameProvider::new);
	}
}
