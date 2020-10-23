package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.buzzier_bees.common.dispenser.BeeBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.common.dispenser.BugBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class BBCompat {
	
	public static class CompatMods {
		public static final String ATMOSPHERIC 		= "atmospheric";
		public static final String AUTUMNITY 		= "autumnity";
		public static final String ENVIRONMENTAL 	= "environmental";
		public static final String UPGRADE_AQUATIC 	= "upgrade_aquatic";
		public static final String ENDERGETIC 		= "endergetic";
	}
	
	// Uses Blocks since they are already registered when accessed (@link BBTrades.java)
	public static class CompatBlocks {
		public static final Block FLOWERING_RUSH 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.UPGRADE_AQUATIC, "flowering_rush"));
		
		public static final Block BLUE_DELPHINIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "blue_delphinium"));
		public static final Block WHITE_DELPHINIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "white_delphinium"));
		public static final Block PINK_DELPHINIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "pink_delphinium"));
		public static final Block PURPLE_DELPHNIUM 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "purple_delphinium"));
		public static final Block BIRD_OF_PARADISE 	= ForgeRegistries.BLOCKS.getValue(new ResourceLocation(CompatMods.ENVIRONMENTAL, "bird_of_paradise"));
	}
	
	// Uses ResourceLocations since these will not be registered at the time of obtaining
	public static class CompatEffects {
		public static final ResourceLocation RELIEF 		= new ResourceLocation(CompatMods.ATMOSPHERIC, "relief");
		public static final ResourceLocation WORSENING 		= new ResourceLocation(CompatMods.ATMOSPHERIC, "worsening");
		public static final ResourceLocation PERSISTENCE 	= new ResourceLocation(CompatMods.ATMOSPHERIC, "persistence");
		public static final ResourceLocation FOUL_TASTE 	= new ResourceLocation(CompatMods.AUTUMNITY, "foul_taste");
	}
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(BBBlocks.PINK_CLOVER.get(), 0.65F);		
		DataUtils.registerCompostable(BBBlocks.WHITE_CLOVER.get(), 0.65F);
		DataUtils.registerCompostable(BBItems.FOUR_LEAF_CLOVER.get(), 0.65F);
	}
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(BBBlocks.SPRUCE_BEEHIVE.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.BIRCH_BEEHIVE.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.JUNGLE_BEEHIVE.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.DARK_OAK_BEEHIVE.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.ACACIA_BEEHIVE.get(), 5, 20);
	}
	
	public static void registerDispenserBehaviors() {
		BuzzierBees.REGISTRY_HELPER.processSpawnEggDispenseBehaviors();
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
	}
}
