package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.data.server.modifiers.BBBiomeModifierProvider;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBConfiguredFeatures;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBPlacedFeatures;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BBDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, BBConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, BBPlacedFeatures::bootstrap)
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, BBBiomeModifierProvider::bootstrap);

	public BBDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, Set.of(BuzzierBees.MOD_ID));
	}
}