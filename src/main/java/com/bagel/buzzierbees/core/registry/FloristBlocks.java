package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.blocks.florist.CompatFlowerPotBlock;
import com.bagel.buzzierbees.common.blocks.florist.CompatHangingFlowerPotBlock;
import com.bagel.buzzierbees.common.blocks.florist.HangingFlowerPotBlock;
import com.bagel.buzzierbees.core.util.CompatBlocks;
import com.bagel.buzzierbees.core.util.PropertyUtils;
import com.bagel.buzzierbees.core.util.RegistryUtils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraftforge.fml.RegistryObject;

@SuppressWarnings("deprecation")
public class FloristBlocks {
	
	// New Flower Pots //
	
	public static final RegistryObject<Block> POTTED_BIRD_OF_PARADISE	= RegistryUtils.createBlockNoItem("potted_bird_of_paradise", () -> new FlowerPotBlock(BBBlocks.BIRD_OF_PARADISE.get(), PropertyUtils.POT));
	
	public static final RegistryObject<Block> POTTED_WHEAT     		= RegistryUtils.createBlockNoItem("potted_wheat_seeds", () -> new FlowerPotBlock(Blocks.WHEAT, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_CARROT     	= RegistryUtils.createBlockNoItem("potted_carrot", () -> new FlowerPotBlock(Blocks.CARROTS, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_POTATO     	= RegistryUtils.createBlockNoItem("potted_potato", () -> new FlowerPotBlock(Blocks.POTATOES, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_BEETROOT     	= RegistryUtils.createBlockNoItem("potted_beetroot_seeds", () -> new FlowerPotBlock(Blocks.BEETROOTS, PropertyUtils.POT));
//	public static final RegistryObject<Block> POTTED_SWEET_BERRIES	= RegistryUtils.createBlockNoItem("potted_sweet_berries", () -> new FlowerPotBlock(Blocks.SWEET_BERRY_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_SUGAR_CANE     = RegistryUtils.createBlockNoItem("potted_sugar_cane", () -> new FlowerPotBlock(Blocks.SUGAR_CANE, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_NETHER_WART    = RegistryUtils.createBlockNoItem("potted_nether_wart", () -> new FlowerPotBlock(Blocks.NETHER_WART, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_CHORUS_PLANT   = RegistryUtils.createBlockNoItem("potted_chorus_plant", () -> new FlowerPotBlock(Blocks.CHORUS_PLANT, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PUMPKIN     	= RegistryUtils.createBlockNoItem("potted_pumpkin", () -> new FlowerPotBlock(Blocks.PUMPKIN, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_MELON     		= RegistryUtils.createBlockNoItem("potted_melon", () -> new FlowerPotBlock(Blocks.MELON, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_ROSE_BUSH     	= RegistryUtils.createBlockNoItem("potted_rose_bush", () -> new FlowerPotBlock(Blocks.ROSE_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_SUNFLOWER     	= RegistryUtils.createBlockNoItem("potted_sunflower", () -> new FlowerPotBlock(Blocks.SUNFLOWER, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_LILAC     		= RegistryUtils.createBlockNoItem("potted_lilac", () -> new FlowerPotBlock(Blocks.LILAC, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PEONY     		= RegistryUtils.createBlockNoItem("potted_peony", () -> new FlowerPotBlock(Blocks.PEONY, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_GRASS     		= RegistryUtils.createBlockNoItem("potted_grass", () -> new FlowerPotBlock(Blocks.GRASS, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_TALL_GRASS     = RegistryUtils.createBlockNoItem("potted_tall_grass", () -> new FlowerPotBlock(Blocks.TALL_GRASS, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_LARGE_FERN    	= RegistryUtils.createBlockNoItem("potted_large_fern", () -> new FlowerPotBlock(Blocks.LARGE_FERN, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_CARVED_PUMPKIN = RegistryUtils.createBlockNoItem("potted_carved_pumpkin", () -> new FlowerPotBlock(Blocks.CARVED_PUMPKIN, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_JACK_O_LANTERN = RegistryUtils.createBlockNoItem("potted_jack_o_lantern", () -> new FlowerPotBlock(Blocks.JACK_O_LANTERN, PropertyUtils.POT_BRIGHT));
//	public static final RegistryObject<Block> POTTED_COCOA_BEANS 	= RegistryUtils.createBlockNoItem("potted_cocoa_beans", () -> new FlowerPotBlock(Blocks.COCOA, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_SEA_PICKLE 	= RegistryUtils.createBlockNoItem("potted_sea_pickle", () -> new FlowerPotBlock(Blocks.SEA_PICKLE, PropertyUtils.POT_LIGHT));

	public static final RegistryObject<Block> POTTED_OVERWORLD_CORROCK 			= RegistryUtils.createBlockNoItem("potted_corrock_overworld", () -> new CompatFlowerPotBlock(() -> CompatBlocks.OVERWORLD_CORROCK.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_NETHER_CORROCK 			= RegistryUtils.createBlockNoItem("potted_corrock_nether", () -> new CompatFlowerPotBlock(() -> CompatBlocks.NETHER_CORROCK.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_END_CORROCK 				= RegistryUtils.createBlockNoItem("potted_corrock_end", () -> new CompatFlowerPotBlock(() -> CompatBlocks.END_CORROCK.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_OVERWORLD_CORROCK_CROWN	= RegistryUtils.createBlockNoItem("potted_corrock_crown_standing_overworld", () -> new CompatFlowerPotBlock(() -> CompatBlocks.OVERWORLD_CORROCK_CROWN.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_NETHER_CORROCK_CROWN		= RegistryUtils.createBlockNoItem("potted_corrock_crown_standing_nether", () -> new CompatFlowerPotBlock(() -> CompatBlocks.NETHER_CORROCK_CROWN.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_END_CORROCK_CROWN			= RegistryUtils.createBlockNoItem("potted_corrock_crown_standing_end", () -> new CompatFlowerPotBlock(() -> CompatBlocks.END_CORROCK_CROWN.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_TALL_POISE_BUSH			= RegistryUtils.createBlockNoItem("potted_poise_grass_tall", () -> new CompatFlowerPotBlock(() -> CompatBlocks.TALL_POISE_BUSH.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_BLUE_DELPHINIUM			= RegistryUtils.createBlockNoItem("potted_blue_delphinium", () -> new CompatFlowerPotBlock(() -> CompatBlocks.BLUE_DELPHINIUM.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PINK_DELPHINIUM			= RegistryUtils.createBlockNoItem("potted_pink_delphinium", () -> new CompatFlowerPotBlock(() -> CompatBlocks.PINK_DELPHINIUM.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_WHITE_DELPHINIUM			= RegistryUtils.createBlockNoItem("potted_white_delphinium", () -> new CompatFlowerPotBlock(() -> CompatBlocks.WHITE_DELPHINIUM.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PURPLE_DELPHINIUM			= RegistryUtils.createBlockNoItem("potted_purple_delphinium", () -> new CompatFlowerPotBlock(() -> CompatBlocks.PURPLE_DELPHINIUM.get(), PropertyUtils.POT));

	// Hanging Flower Pots //
	
	public static final RegistryObject<Block> HANGING_FLOWER_POT  				= RegistryUtils.createBlockNoItem("hanging_flower_pot", () -> new HangingFlowerPotBlock(() -> Blocks.AIR, PropertyUtils.POT));
	
	public static final RegistryObject<Block> HANGING_POTTED_CARTWHEEL 	  		= RegistryUtils.createBlockNoItem("hanging_potted_cartwheel", () -> new HangingFlowerPotBlock(() -> BBBlocks.CARTWHEEL.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUEBELL     		= RegistryUtils.createBlockNoItem("hanging_potted_bluebell", () -> new HangingFlowerPotBlock(() -> BBBlocks.BLUEBELL.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_VIOLET 	  		= RegistryUtils.createBlockNoItem("hanging_potted_violet", () -> new HangingFlowerPotBlock(() -> BBBlocks.VIOLET.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DIANTHUS 	  		= RegistryUtils.createBlockNoItem("hanging_potted_jolyce", () -> new HangingFlowerPotBlock(() -> BBBlocks.DIANTHUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_COLUMBINE 	  		= RegistryUtils.createBlockNoItem("hanging_potted_columbine", () -> new HangingFlowerPotBlock(() -> BBBlocks.COLUMBINE.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_CLOVER 		= RegistryUtils.createBlockNoItem("hanging_potted_white_clover", () -> new HangingFlowerPotBlock(() -> BBBlocks.WHITE_CLOVER.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_CLOVER  		= RegistryUtils.createBlockNoItem("hanging_potted_pink_clover", () -> new HangingFlowerPotBlock(() -> BBBlocks.PINK_CLOVER.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YELLOW_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_daybloom", () -> new HangingFlowerPotBlock(() -> BBBlocks.YELLOW_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ORANGE_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_orange_hibiscus", () -> new HangingFlowerPotBlock(() -> BBBlocks.ORANGE_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_RED_HIBISCUS 		= RegistryUtils.createBlockNoItem("hanging_potted_red_hibiscus", () -> new HangingFlowerPotBlock(() -> BBBlocks.RED_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_HIBISCUS  	= RegistryUtils.createBlockNoItem("hanging_potted_pink_hibiscus", () -> new HangingFlowerPotBlock(() -> BBBlocks.PINK_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_MAGENTA_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_magenta_hibiscus", () -> new HangingFlowerPotBlock(() -> BBBlocks.MAGENTA_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_purple_hibiscus", () -> new HangingFlowerPotBlock(() -> BBBlocks.PURPLE_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BIRD_OF_PARADISE	= RegistryUtils.createBlockNoItem("hanging_potted_bird_of_paradise", () -> new HangingFlowerPotBlock(() -> BBBlocks.BIRD_OF_PARADISE.get(), PropertyUtils.POT));
	
	public static final RegistryObject<Block> HANGING_POTTED_BAMBOO     		= RegistryUtils.createBlockNoItem("hanging_potted_bamboo", () -> new HangingFlowerPotBlock(() -> Blocks.BAMBOO, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OAK_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_oak_sapling", () -> new HangingFlowerPotBlock(() -> Blocks.OAK_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SPRUCE_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_spruce_sapling", () -> new HangingFlowerPotBlock(() -> Blocks.SPRUCE_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BIRCH_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_birch_sapling", () -> new HangingFlowerPotBlock(() -> Blocks.BIRCH_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_JUNGLE_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_jungle_sapling", () -> new HangingFlowerPotBlock(() -> Blocks.JUNGLE_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ACACIA_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_acacia_sapling", () -> new HangingFlowerPotBlock(() -> Blocks.ACACIA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DARK_OAK_SAPLING   = RegistryUtils.createBlockNoItem("hanging_potted_dark_oak_sapling", () -> new HangingFlowerPotBlock(() -> Blocks.DARK_OAK_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_FERN     			= RegistryUtils.createBlockNoItem("hanging_potted_fern", () -> new HangingFlowerPotBlock(() -> Blocks.FERN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DANDELION     		= RegistryUtils.createBlockNoItem("hanging_potted_dandelion", () -> new HangingFlowerPotBlock(() -> Blocks.DANDELION, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_POPPY     			= RegistryUtils.createBlockNoItem("hanging_potted_poppy", () -> new HangingFlowerPotBlock(() -> Blocks.POPPY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_ORCHID 		= RegistryUtils.createBlockNoItem("hanging_potted_blue_orchid", () -> new HangingFlowerPotBlock(() -> Blocks.BLUE_ORCHID, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ALLIUM     		= RegistryUtils.createBlockNoItem("hanging_potted_allium", () -> new HangingFlowerPotBlock(() -> Blocks.ALLIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_AZURE_BLUET     	= RegistryUtils.createBlockNoItem("hanging_potted_azure_bluet", () -> new HangingFlowerPotBlock(() -> Blocks.AZURE_BLUET, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_RED_TULIP     		= RegistryUtils.createBlockNoItem("hanging_potted_red_tulip", () -> new HangingFlowerPotBlock(() -> Blocks.RED_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ORANGE_TULIP     	= RegistryUtils.createBlockNoItem("hanging_potted_orange_tulip", () -> new HangingFlowerPotBlock(() -> Blocks.ORANGE_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_TULIP     	= RegistryUtils.createBlockNoItem("hanging_potted_white_tulip", () -> new HangingFlowerPotBlock(() -> Blocks.WHITE_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_TULIP     	= RegistryUtils.createBlockNoItem("hanging_potted_pink_tulip", () -> new HangingFlowerPotBlock(() -> Blocks.PINK_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OXEYE_DAISY     	= RegistryUtils.createBlockNoItem("hanging_potted_oxeye_daisy", () -> new HangingFlowerPotBlock(() -> Blocks.OXEYE_DAISY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CORNFLOWER     	= RegistryUtils.createBlockNoItem("hanging_potted_cornflower", () -> new HangingFlowerPotBlock(() -> Blocks.CORNFLOWER, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LILY_OF_THE_VALLEY	= RegistryUtils.createBlockNoItem("hanging_potted_lily_of_the_valley", () -> new HangingFlowerPotBlock(() -> Blocks.LILY_OF_THE_VALLEY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WITHER_ROSE     	= RegistryUtils.createBlockNoItem("hanging_potted_wither_rose", () -> new HangingFlowerPotBlock(() -> Blocks.WITHER_ROSE, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_RED_MUSHROOM     	= RegistryUtils.createBlockNoItem("hanging_potted_red_mushroom", () -> new HangingFlowerPotBlock(() -> Blocks.RED_MUSHROOM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BROWN_MUSHROOM     = RegistryUtils.createBlockNoItem("hanging_potted_brown_mushroom", () -> new HangingFlowerPotBlock(() -> Blocks.BROWN_MUSHROOM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DEAD_BUSH     		= RegistryUtils.createBlockNoItem("hanging_potted_dead_bush", () -> new HangingFlowerPotBlock(() -> Blocks.DEAD_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CACTUS     		= RegistryUtils.createBlockNoItem("hanging_potted_cactus", () -> new HangingFlowerPotBlock(() -> Blocks.CACTUS, PropertyUtils.POT));
	
	public static final RegistryObject<Block> HANGING_POTTED_WHEAT     		= RegistryUtils.createBlockNoItem("hanging_potted_wheat_seeds", () -> new HangingFlowerPotBlock(() -> Blocks.WHEAT, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CARROT     	= RegistryUtils.createBlockNoItem("hanging_potted_carrot", () -> new HangingFlowerPotBlock(() -> Blocks.CARROTS, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_POTATO     	= RegistryUtils.createBlockNoItem("hanging_potted_potato", () -> new HangingFlowerPotBlock(() -> Blocks.POTATOES, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BEETROOT     	= RegistryUtils.createBlockNoItem("hanging_potted_beetroot_seeds", () -> new HangingFlowerPotBlock(() -> Blocks.BEETROOTS, PropertyUtils.POT));
//	public static final RegistryObject<Block> HANGING_POTTED_SWEET_BERRIES	= RegistryUtils.createBlockNoItem("hanging_potted_sweet_berries", () -> new HangingFlowerPotBlock(() -> Blocks.SWEET_BERRY_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SUGAR_CANE     = RegistryUtils.createBlockNoItem("hanging_potted_sugar_cane", () -> new HangingFlowerPotBlock(() -> Blocks.SUGAR_CANE, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_NETHER_WART    = RegistryUtils.createBlockNoItem("hanging_potted_nether_wart", () -> new HangingFlowerPotBlock(() -> Blocks.NETHER_WART, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CHORUS_PLANT   = RegistryUtils.createBlockNoItem("hanging_potted_chorus_plant", () -> new HangingFlowerPotBlock(() -> Blocks.CHORUS_PLANT, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PUMPKIN     	= RegistryUtils.createBlockNoItem("hanging_potted_pumpkin", () -> new HangingFlowerPotBlock(() -> Blocks.PUMPKIN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_MELON     		= RegistryUtils.createBlockNoItem("hanging_potted_melon", () -> new HangingFlowerPotBlock(() -> Blocks.MELON, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ROSE_BUSH     	= RegistryUtils.createBlockNoItem("hanging_potted_rose_bush", () -> new HangingFlowerPotBlock(() -> Blocks.ROSE_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SUNFLOWER     	= RegistryUtils.createBlockNoItem("hanging_potted_sunflower", () -> new HangingFlowerPotBlock(() -> Blocks.SUNFLOWER, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LILAC     		= RegistryUtils.createBlockNoItem("hanging_potted_lilac", () -> new HangingFlowerPotBlock(() -> Blocks.LILAC, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PEONY     		= RegistryUtils.createBlockNoItem("hanging_potted_peony", () -> new HangingFlowerPotBlock(() -> Blocks.PEONY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_GRASS     		= RegistryUtils.createBlockNoItem("hanging_potted_grass", () -> new HangingFlowerPotBlock(() -> Blocks.GRASS, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_TALL_GRASS     = RegistryUtils.createBlockNoItem("hanging_potted_tall_grass", () -> new HangingFlowerPotBlock(() -> Blocks.TALL_GRASS, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LARGE_FERN    	= RegistryUtils.createBlockNoItem("hanging_potted_large_fern", () -> new HangingFlowerPotBlock(() -> Blocks.LARGE_FERN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CARVED_PUMPKIN = RegistryUtils.createBlockNoItem("hanging_potted_carved_pumpkin", () -> new HangingFlowerPotBlock(() -> Blocks.CARVED_PUMPKIN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_JACK_O_LANTERN = RegistryUtils.createBlockNoItem("hanging_potted_jack_o_lantern", () -> new HangingFlowerPotBlock(() -> Blocks.JACK_O_LANTERN, PropertyUtils.POT_BRIGHT));
//	public static final RegistryObject<Block> HANGING_POTTED_COCOA_BEANS 	= RegistryUtils.createBlockNoItem("hanging_potted_cocoa_beans", () -> new HangingFlowerPotBlock(() -> Blocks.COCOA, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SEA_PICKLE 	= RegistryUtils.createBlockNoItem("hanging_potted_sea_pickle", () -> new HangingFlowerPotBlock(() -> Blocks.SEA_PICKLE, PropertyUtils.POT_LIGHT));

	public static final RegistryObject<Block> HANGING_POTTED_WHITE_WISTERIA_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_white_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.WHITE_WISTERIA_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_WISTERIA_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_blue_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.BLUE_WISTERIA_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_WISTERIA_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_pink_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.PINK_WISTERIA_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_WISTERIA_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_purple_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.PURPLE_WISTERIA_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LAVENDER_BLOSSOM_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_lavender_blossom_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.LAVENDER_BLOSSOM_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ORANGE_BLOSSOM_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_orange_blossom_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.ORANGE_BLOSSOM_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_BLOSSOM_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_pink_blossom_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.PINK_BLOSSOM_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_BLOSSOM_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_blue_blossom_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.BLUE_BLOSSOM_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YELLOW_BLOSSOM_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_yellow_blossom_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.YELLOW_BLOSSOM_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ROSEWOOD_SAPLING 			= RegistryUtils.createBlockNoItem("hanging_potted_rosewood_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.ROSEWOOD_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YUCCA_SAPLING 				= RegistryUtils.createBlockNoItem("hanging_potted_yucca_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.YUCCA_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_KOUSA_SAPLING 				= RegistryUtils.createBlockNoItem("hanging_potted_kousa_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.KOUSA_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ASPEN_SAPLING 				= RegistryUtils.createBlockNoItem("hanging_potted_aspen_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.ASPEN_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_MAPLE_SAPLING 				= RegistryUtils.createBlockNoItem("hanging_potted_maple_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.MAPLE_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YELLOW_MAPLE_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_yellow_maple_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.YELLOW_MAPLE_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ORANGE_MAPLE_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_orange_maple_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.ORANGE_MAPLE_SAPLING.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_RED_MAPLE_SAPLING			= RegistryUtils.createBlockNoItem("hanging_potted_red_maple_sapling", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.RED_MAPLE_SAPLING.get(), PropertyUtils.POT));
	
	public static final RegistryObject<Block> HANGING_POTTED_POISE_BUSH 				= RegistryUtils.createBlockNoItem("hanging_potted_poise_grass", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.POISE_BUSH.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_PICKERELWEED 			= RegistryUtils.createBlockNoItem("hanging_potted_pickerel_weed_blue", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.BLUE_PICKERELWEED.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_PICKERELWEED 		= RegistryUtils.createBlockNoItem("hanging_potted_pickerel_weed_purple", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.PURPLE_PICKERELWEED.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_SEAROCKET 			= RegistryUtils.createBlockNoItem("hanging_potted_searocket_white", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.WHITE_SEAROCKET.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_SEAROCKET 			= RegistryUtils.createBlockNoItem("hanging_potted_searocket_pink", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.PINK_SEAROCKET.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_GLOWSHROOM 				= RegistryUtils.createBlockNoItem("hanging_potted_glowshroom", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.GLOWSHROOM.get(), PropertyUtils.POT_BRIGHT));
	public static final RegistryObject<Block> HANGING_POTTED_WARM_MONKEY_BRUSH 			= RegistryUtils.createBlockNoItem("hanging_potted_warm_monkey_brush", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.WARM_MONKEY_BRUSH.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_HOT_MONKEY_BRUSH 			= RegistryUtils.createBlockNoItem("hanging_potted_monkey_brush", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.HOT_MONKEY_BRUSH.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SCALDING_MONKEY_BRUSH 		= RegistryUtils.createBlockNoItem("hanging_potted_scalding_monkey_brush", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.SCALDING_MONKEY_BRUSH.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_GILIA 						= RegistryUtils.createBlockNoItem("hanging_potted_gilia", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.GILIA.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YUCCA_FLOWER 				= RegistryUtils.createBlockNoItem("hanging_potted_yucca_flower", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.YUCCA_FLOWER.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BARREL_CACTUS 				= RegistryUtils.createBlockNoItem("hanging_potted_barrel_cactus", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.BARREL_CACTUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CATTAIL	 				= RegistryUtils.createBlockNoItem("hanging_potted_cattail", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.CATTAIL.get(), PropertyUtils.POT));

	public static final RegistryObject<Block> HANGING_POTTED_BAMBOO_TORCH     		= RegistryUtils.createBlockNoItem("hanging_potted_bamboo_torch", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.BAMBOO_TORCH.get(), PropertyUtils.POT_BRIGHT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_DELPHINIUM     	= RegistryUtils.createBlockNoItem("hanging_potted_white_delphinium", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.WHITE_DELPHINIUM.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_DELPHINIUM     	= RegistryUtils.createBlockNoItem("hanging_potted_blue_delphinium", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.BLUE_DELPHINIUM.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_DELPHINIUM     	= RegistryUtils.createBlockNoItem("hanging_potted_pink_delphinium", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.PINK_DELPHINIUM.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_DELPHINIUM		= RegistryUtils.createBlockNoItem("hanging_potted_purple_delphinium", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.PURPLE_DELPHINIUM.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OVERWORLD_CORROCK_CROWN= RegistryUtils.createBlockNoItem("hanging_potted_corrock_crown_standing_overworld", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.OVERWORLD_CORROCK_CROWN.get(), PropertyUtils.POT_LIGHT));
	public static final RegistryObject<Block> HANGING_POTTED_NETHER_CORROCK_CROWN   = RegistryUtils.createBlockNoItem("hanging_potted_corrock_crown_standing_nether", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.NETHER_CORROCK_CROWN.get(), PropertyUtils.POT_LIGHT));
	public static final RegistryObject<Block> HANGING_POTTED_END_CORROCK_CROWN		= RegistryUtils.createBlockNoItem("hanging_potted_corrock_crown_standing_end", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.END_CORROCK_CROWN.get(), PropertyUtils.POT_LIGHT));
	public static final RegistryObject<Block> HANGING_POTTED_TALL_POISE_BUSH     	= RegistryUtils.createBlockNoItem("hanging_potted_poise_grass_tall", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.TALL_POISE_BUSH.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OVERWORLD_CORROCK     	= RegistryUtils.createBlockNoItem("hanging_potted_corrock_overworld", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.OVERWORLD_CORROCK.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_NETHER_CORROCK     	= RegistryUtils.createBlockNoItem("hanging_potted_corrock_nether", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.NETHER_CORROCK.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_END_CORROCK     		= RegistryUtils.createBlockNoItem("hanging_potted_corrock_end", () -> new CompatHangingFlowerPotBlock(() -> CompatBlocks.END_CORROCK.get(), PropertyUtils.POT));
}
