package com.teamabnormals.buzzier_bees.core.registry.datapack;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBiomeTags;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.RemoveSpawnsBiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddSpawnsBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BBBiomeModifiers {

	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		removeSpawn(context, "remove_animal/cow", BBBiomeTags.HAS_MOOBLOOM, EntityType.COW);
		addSpawn(context, "add_animal/moobloom", BBBiomeTags.HAS_MOOBLOOM, new MobSpawnSettings.SpawnerData(BBEntityTypes.MOOBLOOM.get(), 12, 4, 4));

		addFeature(context, "add_feature/buttercup", BBBiomeTags.HAS_BUTTERCUP, GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_BUTTERCUP);
		addFeature(context, "add_feature/white_clover", BBBiomeTags.HAS_WHITE_CLOVER, GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_WHITE_CLOVER);
		addFeature(context, "add_feature/pink_clover", BBBiomeTags.HAS_PINK_CLOVER, GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_PINK_CLOVER);
	}

	@SafeVarargs
	private static void addFeature(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
		register(context, name, () -> new AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
	}

	private static void addSpawn(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, MobSpawnSettings.SpawnerData... spawns) {
		register(context, name, () -> new AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
	}

	private static void removeSpawn(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, EntityType<?>... types) {
		register(context, "remove_spawn/" + name, () -> new RemoveSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), HolderSet.direct(Stream.of(types).map(BuiltInRegistries.ENTITY_TYPE::wrapAsHolder).collect(Collectors.toList()))));
	}

	private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
		context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BuzzierBees.location("add_feature/" + name)), modifier.get());
	}

	@SafeVarargs
	private static HolderSet<PlacedFeature> featureSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
	}
}