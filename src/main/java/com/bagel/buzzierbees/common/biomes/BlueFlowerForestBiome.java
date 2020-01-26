package com.bagel.buzzierbees.common.biomes;

import com.bagel.buzzierbees.common.biomes.utils.BlueForestFlowerBlockStateProvider;
import com.google.common.collect.ImmutableList;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public final class BlueFlowerForestBiome extends Biome {
	
	public static final BlockClusterFeatureConfig BLUE_FLOWER_FOREST = (new BlockClusterFeatureConfig.Builder(new BlueForestFlowerBlockStateProvider(), new SimpleBlockPlacer())).func_227315_a_(64).func_227322_d_();

	   public BlueFlowerForestBiome() {
		  super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.3625F).scale(1.0F).temperature(0.7F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent("forest"));
	      this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.006D, MineshaftStructure.Type.NORMAL)));
	      this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	      DefaultBiomeFeatures.addCarvers(this);
	      DefaultBiomeFeatures.addStructures(this);
	      DefaultBiomeFeatures.addLakes(this);
	      DefaultBiomeFeatures.addMonsterRooms(this);
	      DefaultBiomeFeatures.addStoneVariants(this);
	      DefaultBiomeFeatures.addOres(this);
	      DefaultBiomeFeatures.addSedimentDisks(this);
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
	    		  Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
	    				  Feature.DARK_OAK_TREE.withConfiguration(DefaultBiomeFeatures.field_226822_q_).func_227227_a_(0.1F)), 
	    				  Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.field_226739_a_)
	    				  )).func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));

	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.withConfiguration(DefaultBiomeFeatures.field_226826_u_).func_227228_a_(Placement.NOISE_HEIGHTMAP_DOUBLE.func_227446_a_(new NoiseDependant(-0.8D, 5, 10))));
	      this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
	    		  Feature.field_227247_y_.withConfiguration(BLUE_FLOWER_FOREST).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(100))));
	      DefaultBiomeFeatures.addSparseGrass(this);
	      DefaultBiomeFeatures.addMushrooms(this);
	      DefaultBiomeFeatures.addReedsAndPumpkins(this);
	      DefaultBiomeFeatures.addSprings(this);
	      DefaultBiomeFeatures.addFreezeTopLayer(this);
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
	      this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
	   }
	   
	   @OnlyIn(Dist.CLIENT)
	   public int getFoliageColor() {
	      return 6988031;
	   }
	}
