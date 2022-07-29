package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.client.particle.ButtercupBloomParticle;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BBParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BuzzierBees.MOD_ID);

	public static final RegistryObject<SimpleParticleType> BUTTERCUP_BLOOM = PARTICLE_TYPES.register("buttercup_bloom", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> SMALL_SOUL_FIRE_FLAME = PARTICLE_TYPES.register("small_soul_fire_flame", () -> new SimpleParticleType(false));

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
		event.register(BUTTERCUP_BLOOM.get(), ButtercupBloomParticle.Factory::new);
		event.register(SMALL_SOUL_FIRE_FLAME.get(), FlameParticle.SmallFlameProvider::new);
	}
}
