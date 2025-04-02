package com.teamabnormals.buzzier_bees.core.registry.datapack;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class BBBannerPatterns {
	public static final ResourceKey<BannerPattern> HONEYCOMB = create("honeycomb");

	public static void bootstrap(BootstrapContext<BannerPattern> context) {
		register(context, HONEYCOMB);
	}

	private static ResourceKey<BannerPattern> create(String name) {
		return ResourceKey.create(Registries.BANNER_PATTERN, BuzzierBees.location(name));
	}

	public static void register(BootstrapContext<BannerPattern> context, ResourceKey<BannerPattern> resourceKey) {
		context.register(resourceKey, new BannerPattern(resourceKey.location(), "block.minecraft.banner." + resourceKey.location().toShortLanguageKey()));
	}
}