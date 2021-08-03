package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.buzzier_bees.client.particle.ButtercupBloomParticle;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BBParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BuzzierBees.MOD_ID);
	public static final RegistryObject<BasicParticleType> BUTTERCUP_BLOOM = PARTICLES.register("buttercup_bloom", () -> new BasicParticleType(true));

	@EventBusSubscriber(modid = BuzzierBees.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			Minecraft.getInstance().particleEngine.register(BUTTERCUP_BLOOM.get(), ButtercupBloomParticle.Factory::new);
		}
	}
}
