package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.client.particle.ButtercupBloomParticle;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BuzzierBees.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BBParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BuzzierBees.MOD_ID);

	public static final RegistryObject<SimpleParticleType> BUTTERCUP_BLOOM = PARTICLES.register("buttercup_bloom", () -> new SimpleParticleType(true));

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
		ParticleEngine engine = Minecraft.getInstance().particleEngine;
		engine.register(BUTTERCUP_BLOOM.get(), ButtercupBloomParticle.Factory::new);
	}
}
