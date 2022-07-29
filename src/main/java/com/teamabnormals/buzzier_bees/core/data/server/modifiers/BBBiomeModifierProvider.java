package com.teamabnormals.buzzier_bees.core.data.server.modifiers;

import com.mojang.serialization.JsonOps;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBBiomeTags;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddSpawnsBiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.RemoveSpawnsBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BBBiomeModifierProvider {

	public static JsonCodecProvider<BiomeModifier> create(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		RegistryAccess access = RegistryAccess.builtinCopy();
		Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<PlacedFeature> placedFeatures = access.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
		HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();

		addModifier(modifiers, "remove_animal/cow", new RemoveSpawnsBiomeModifier(tag(biomeRegistry, BBBiomeTags.HAS_MOOBLOOM), HolderSet.direct(List.of(ForgeRegistries.ENTITY_TYPES.getHolder(EntityType.COW).get()))));

		addModifier(modifiers, "add_animal/moobloom", new AddSpawnsBiomeModifier(tag(biomeRegistry, BBBiomeTags.HAS_MOOBLOOM), List.of(new MobSpawnSettings.SpawnerData(BBEntityTypes.MOOBLOOM.get(), 12, 4, 4))));
		addModifier(modifiers, "add_feature/buttercup", new AddFeaturesBiomeModifier(tag(biomeRegistry, BBBiomeTags.HAS_BUTTERCUP), of(placedFeatures, BBPlacedFeatures.FLOWER_BUTTERCUP), GenerationStep.Decoration.VEGETAL_DECORATION));

		addModifier(modifiers, "add_feature/white_clover", new AddFeaturesBiomeModifier(tag(biomeRegistry, BBBiomeTags.HAS_WHITE_CLOVER), of(placedFeatures, BBPlacedFeatures.FLOWER_WHITE_CLOVER), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/pink_clover", new AddFeaturesBiomeModifier(tag(biomeRegistry, BBBiomeTags.HAS_PINK_CLOVER), of(placedFeatures, BBPlacedFeatures.FLOWER_PINK_CLOVER), GenerationStep.Decoration.VEGETAL_DECORATION));

		return JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, BuzzierBees.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, access), ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
	}

	private static HolderSet<Biome> tag(Registry<Biome> biomeRegistry, TagKey<Biome> tagKey) {
		return new HolderSet.Named<>(biomeRegistry, tagKey);
	}

	private static void addModifier(HashMap<ResourceLocation, BiomeModifier> modifiers, String name, BiomeModifier modifier) {
		modifiers.put(new ResourceLocation(BuzzierBees.MOD_ID, name), modifier);
	}

	@SafeVarargs
	@SuppressWarnings("ConstantConditions")
	private static HolderSet<PlacedFeature> of(Registry<PlacedFeature> placedFeatures, RegistryObject<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(registryObject -> placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}
}