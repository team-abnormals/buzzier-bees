package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import com.teamabnormals.buzzier_bees.core.registry.BBFeatures.BBPlacedFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBGeneration {

	@SubscribeEvent
	public static void addFeatures(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();

		if (DataUtil.matchesKeys(biome, Biomes.FOREST, Biomes.WINDSWEPT_FOREST))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_WHITE_CLOVER);

		if (DataUtil.matchesKeys(biome, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST))
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_PINK_CLOVER);

		if (DataUtil.matchesKeys(biome, Biomes.SUNFLOWER_PLAINS)) {
			event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BBPlacedFeatures.FLOWER_BUTTERCUP);
			event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(BBEntityTypes.MOOBLOOM.get(), 40, 2, 4));
		}
	}
}
