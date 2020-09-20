package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class BBCompat {
	
	public static class CompatMods {
		public static final String ATMOSPHERIC 		= "atmospheric";
		public static final String AUTUMNITY 		= "autumnity";
		public static final String ENVIRONMENTAL 	= "environmental";
		public static final String UPGRADE_AQUATIC 	= "upgrade_aquatic";
	}
	
	public static class CompatBlocks {
		public static final Block FLOWERING_RUSH 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.UPGRADE_AQUATIC, "flowering_rush"));
		
		public static final Block BLUE_DELPHINIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "blue_delphinium"));
		public static final Block WHITE_DELPHINIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "white_delphinium"));
		public static final Block PINK_DELPHINIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "pink_delphinium"));
		public static final Block PURPLE_DELPHNIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "purple_delphinium"));
		public static final Block BIRD_OF_PARADISE 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "bird_of_paradise"));
	}
	
	public static class CompatEffects {
		public static final Effect RELIEF 		= ForgeRegistries.POTIONS.getValue(new ResourceLocation(CompatMods.ATMOSPHERIC, "relief"));
		public static final Effect PERSISTENCE 	= ForgeRegistries.POTIONS.getValue(new ResourceLocation(CompatMods.ATMOSPHERIC, "persistence"));
		public static final Effect FOUL_TASTE 	= ForgeRegistries.POTIONS.getValue(new ResourceLocation(CompatMods.AUTUMNITY, "foul_taste"));
	}
	
	public static void init() {
		DataUtils.registerCompostable(BBBlocks.PINK_CLOVER.get(), 0.65F);		
		DataUtils.registerCompostable(BBBlocks.WHITE_CLOVER.get(), 0.65F);
	}
}
