package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBConfiguredFeatures;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBPlacedFeatures;
import com.teamabnormals.buzzier_bees.core.registry.datapack.BBBannerPatterns;
import com.teamabnormals.buzzier_bees.core.registry.datapack.BBBiomeModifiers;
import com.teamabnormals.buzzier_bees.core.registry.datapack.BBPaintingVariants;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BBDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.BANNER_PATTERN, BBBannerPatterns::bootstrap)
			.add(Registries.PAINTING_VARIANT, BBPaintingVariants::bootstrap)
			.add(Registries.CONFIGURED_FEATURE, BBConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, BBPlacedFeatures::bootstrap)
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BBBiomeModifiers::bootstrap);

	public BBDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, Set.of(BuzzierBees.MOD_ID));
	}
}