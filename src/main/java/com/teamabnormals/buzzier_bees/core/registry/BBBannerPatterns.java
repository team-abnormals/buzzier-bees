package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBBannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, BuzzierBees.MOD_ID);

	public static final RegistryObject<BannerPattern> HONEYCOMB = BANNER_PATTERNS.register("honeycomb", () -> new BannerPattern("hny"));
}