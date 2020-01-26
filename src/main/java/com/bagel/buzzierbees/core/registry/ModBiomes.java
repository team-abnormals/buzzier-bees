package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.biomes.BlueFlowerForestBiome;
import com.bagel.buzzierbees.common.biomes.PurpleFlowerForestBiome;
import com.bagel.buzzierbees.common.biomes.RedFlowerForestBiome;
import com.bagel.buzzierbees.common.biomes.WhiteFlowerForestBiome;
import com.bagel.buzzierbees.common.biomes.YellowFlowerForestBiome;
import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = "buzzierbees", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, BuzzierBees.MODID);
		
    public static RegistryObject<Biome> SILVER_PLAINS = BIOMES.register("silver_plains", () -> new WhiteFlowerForestBiome());
    public static RegistryObject<Biome> SCARLET_HILLS = BIOMES.register("scarlet_hills", () -> new RedFlowerForestBiome());
    public static RegistryObject<Biome> LAVENDER_GROVE = BIOMES.register("lavender_grove", () -> new PurpleFlowerForestBiome());
    public static RegistryObject<Biome> GOLDEN_FIELDS = BIOMES.register("golden_fields", () -> new YellowFlowerForestBiome());
    public static RegistryObject<Biome> SAPPHIRE_CLIFFS = BIOMES.register("sapphire_cliffs", () -> new BlueFlowerForestBiome());
    
    public static void registerBiomesToDictionary() {
        
        BiomeDictionary.addTypes(SILVER_PLAINS.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(SILVER_PLAINS.get(), 1));
        
        BiomeDictionary.addTypes(SCARLET_HILLS.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.OVERWORLD);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SCARLET_HILLS.get(), 1));
        
        BiomeDictionary.addTypes(LAVENDER_GROVE.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(LAVENDER_GROVE.get(), 1));
        
        BiomeDictionary.addTypes(GOLDEN_FIELDS.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(GOLDEN_FIELDS.get(), 1));
        
        BiomeDictionary.addTypes(SAPPHIRE_CLIFFS.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(SAPPHIRE_CLIFFS.get(), 1));
    }
}
