package com.minecraftabnormals.buzzier_bees.core.registry;

import com.google.common.base.Supplier;
import com.minecraftabnormals.buzzier_bees.common.blocks.ButtercupBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.CandleBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.CrystallizedHoneyBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.EnderCandleBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.HoneyLampBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.HoneyPotBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.HoneycombDoorBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.HoneycombTrapDoorBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.minecraftabnormals.buzzier_bees.common.blocks.SoulCandleBlock;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.other.BBCompat.CompatEffects;
import com.minecraftabnormals.buzzier_bees.core.other.BBCompat.CompatMods;
import com.minecraftabnormals.buzzier_bees.core.other.BBProperties;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsBeehiveBlock;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsStairsBlock;
import com.teamabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBBlocks
{	
	public static final RegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER;
	
	// Beehives //
	
	public static final RegistryObject<Block> SPRUCE_BEEHIVE   	= HELPER.createBlock("spruce_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BIRCH_BEEHIVE    	= HELPER.createBlock("birch_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> JUNGLE_BEEHIVE   	= HELPER.createBlock("jungle_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ACACIA_BEEHIVE   	= HELPER.createBlock("acacia_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DARK_OAK_BEEHIVE	= HELPER.createBlock("dark_oak_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CRIMSON_BEEHIVE	= HELPER.createBlock("crimson_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WARPED_BEEHIVE	= HELPER.createBlock("warped_beehive", () -> new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);

	// Misc //
	
	public static final RegistryObject<Block> WAX_BLOCK 				= HELPER.createBlock("wax_block", () -> new Block(BBProperties.WAX_BLOCK), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CRYSTALLIZED_HONEY_BLOCK  = HELPER.createBlock("crystallized_honey_block", () -> new CrystallizedHoneyBlock(BBProperties.CRYSTALLIZED_HONEY), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HONEY_LAMP 				= HELPER.createBlock("honey_lamp", () -> new HoneyLampBlock(Block.Properties.from(Blocks.END_ROD).sound(SoundType.HONEY)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HONEY_POT 				= HELPER.createBlock("honey_pot", () -> new HoneyPotBlock(Block.Properties.from(Blocks.TERRACOTTA).notSolid()), ItemGroup.DECORATIONS);

	// Honeycomb Blocks //
	
	public static final RegistryObject<Block> HONEYCOMB_DOOR		= HELPER.createBlock("honeycomb_door", () -> new HoneycombDoorBlock(BBProperties.HONEYCOMB_BRICKS), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HONEYCOMB_TRAPDOOR	= HELPER.createBlock("honeycomb_trapdoor", () -> new HoneycombTrapDoorBlock(BBProperties.HONEYCOMB_BRICKS), ItemGroup.REDSTONE);

	public static final RegistryObject<Block> HONEYCOMB_BRICKS              = HELPER.createBlock("honeycomb_bricks", () -> new Block(BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_STAIRS 	    = HELPER.createBlock("honeycomb_brick_stairs", () -> new AbnormalsStairsBlock(HONEYCOMB_BRICKS.get().getDefaultState(), BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_SLAB 	        = HELPER.createBlock("honeycomb_brick_slab", () -> new SlabBlock(BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_WALL 	        = HELPER.createBlock("honeycomb_brick_wall", () -> new WallBlock(BBProperties.HONEYCOMB_BRICKS), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "honeycomb_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(HONEYCOMB_BRICKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_HONEYCOMB_BRICKS     = HELPER.createBlock("chiseled_honeycomb_bricks", () -> new Block(BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> HONEYCOMB_TILES              	= HELPER.createBlock("honeycomb_tiles", () -> new Block(BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_STAIRS 	    = HELPER.createBlock("honeycomb_tile_stairs", () -> new AbnormalsStairsBlock(HONEYCOMB_TILES.get().getDefaultState(), BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_SLAB 	        = HELPER.createBlock("honeycomb_tile_slab", () -> new SlabBlock(BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_WALL 	        = HELPER.createBlock("honeycomb_tile_wall", () -> new WallBlock(BBProperties.HONEYCOMB_BRICKS), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "honeycomb_tile_vertical_slab", () -> new VerticalSlabBlock(BBProperties.HONEYCOMB_BRICKS), ItemGroup.BUILDING_BLOCKS);
	
	// Flowers //
	
	public static final RegistryObject<Block> BUTTERCUP 		= HELPER.createBlock("buttercup", () -> new ButtercupBlock(() -> BBEffects.SUNNY.get(), 12 * 20, BBProperties.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_CLOVER 		= HELPER.createBlock("white_clover", () -> new AbnormalsFlowerBlock(Effects.UNLUCK, 30, BBProperties.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER 		= HELPER.createBlock("pink_clover", () -> new AbnormalsFlowerBlock(Effects.UNLUCK, 60, BBProperties.FLOWER), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> POTTED_WHITE_CLOVER 	= HELPER.createBlockNoItem("potted_white_clover", () -> new FlowerPotBlock(WHITE_CLOVER.get(), BBProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_PINK_CLOVER  	= HELPER.createBlockNoItem("potted_pink_clover", () -> new FlowerPotBlock(PINK_CLOVER.get(), BBProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_BUTTERCUP		= HELPER.createBlockNoItem("potted_buttercup", () -> new FlowerPotBlock(BUTTERCUP.get(), BBProperties.FLOWER_POT));

	// Candles //
	
	public static final RegistryObject<Block> CANDLE 			= HELPER.createBlock("candle", () -> new CandleBlock(null, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SOUL_CANDLE 		= HELPER.createBlock("soul_candle", () -> new SoulCandleBlock(null, BBProperties.SOUL_CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> EMDER_CANDLE 		= HELPER.createCompatBlock("endergetic", "ender_candle", () -> new EnderCandleBlock(null, BBProperties.CANDLE), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> WHITE_CANDLE 		= HELPER.createBlock("white_candle", () -> new CandleBlock(DyeColor.WHITE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_CANDLE 	= HELPER.createBlock("orange_candle", () -> new CandleBlock(DyeColor.ORANGE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAGENTA_CANDLE 	= HELPER.createBlock("magenta_candle", () -> new CandleBlock(DyeColor.MAGENTA, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_BLUE_CANDLE = HELPER.createBlock("light_blue_candle", () -> new CandleBlock(DyeColor.LIGHT_BLUE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_CANDLE 	= HELPER.createBlock("yellow_candle", () -> new CandleBlock(DyeColor.YELLOW, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIME_CANDLE 		= HELPER.createBlock("lime_candle", () -> new CandleBlock(DyeColor.LIME, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CANDLE 		= HELPER.createBlock("pink_candle", () -> new CandleBlock(DyeColor.PINK, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GRAY_CANDLE 		= HELPER.createBlock("gray_candle", () -> new CandleBlock(DyeColor.GRAY, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_GRAY_CANDLE = HELPER.createBlock("light_gray_candle", () -> new CandleBlock(DyeColor.LIGHT_GRAY, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CYAN_CANDLE 		= HELPER.createBlock("cyan_candle", () -> new CandleBlock(DyeColor.CYAN, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_CANDLE 	= HELPER.createBlock("purple_candle", () -> new CandleBlock(DyeColor.PURPLE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_CANDLE 		= HELPER.createBlock("blue_candle", () -> new CandleBlock(DyeColor.BLUE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BROWN_CANDLE 		= HELPER.createBlock("brown_candle", () -> new CandleBlock(DyeColor.BROWN, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GREEN_CANDLE 		= HELPER.createBlock("green_candle", () -> new CandleBlock(DyeColor.GREEN, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_CANDLE 		= HELPER.createBlock("red_candle", () -> new CandleBlock(DyeColor.RED, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLACK_CANDLE 		= HELPER.createBlock("black_candle", () -> new CandleBlock(DyeColor.BLACK, BBProperties.CANDLE), ItemGroup.DECORATIONS);

	// Scented Candles //
	
	public static final RegistryObject<Block> ALLIUM_SCENTED_CANDLE             = HELPER.createBlock("allium_scented_candle", () -> new ScentedCandleBlock(() -> Effects.FIRE_RESISTANCE, DyeColor.MAGENTA, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> AZURE_BLUET_SCENTED_CANDLE        = HELPER.createBlock("azure_bluet_scented_candle", () -> new ScentedCandleBlock(() -> Effects.BLINDNESS, DyeColor.LIGHT_GRAY, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_ORCHID_SCENTED_CANDLE        = HELPER.createBlock("blue_orchid_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SATURATION, DyeColor.LIGHT_BLUE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DANDELION_SCENTED_CANDLE          = HELPER.createBlock("dandelion_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SATURATION, DyeColor.YELLOW, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CORNFLOWER_SCENTED_CANDLE         = HELPER.createBlock("cornflower_scented_candle", () -> new ScentedCandleBlock(() -> Effects.JUMP_BOOST, DyeColor.BLUE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LILY_OF_THE_VALLEY_SCENTED_CANDLE = HELPER.createBlock("lily_of_the_valley_scented_candle", () -> new ScentedCandleBlock(() -> Effects.POISON, DyeColor.WHITE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> OXEYE_DAISY_SCENTED_CANDLE        = HELPER.createBlock("oxeye_daisy_scented_candle", () -> new ScentedCandleBlock(() -> Effects.REGENERATION, DyeColor.LIGHT_GRAY, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POPPY_SCENTED_CANDLE              = HELPER.createBlock("poppy_scented_candle", () -> new ScentedCandleBlock(() -> Effects.NIGHT_VISION, DyeColor.RED, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_TULIP_SCENTED_CANDLE        = HELPER.createBlock("white_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS, DyeColor.LIGHT_GRAY, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_TULIP_SCENTED_CANDLE       = HELPER.createBlock("orange_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS, DyeColor.ORANGE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_TULIP_SCENTED_CANDLE         = HELPER.createBlock("pink_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS, DyeColor.PINK, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_TULIP_SCENTED_CANDLE          = HELPER.createBlock("red_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS, DyeColor.RED, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WITHER_ROSE_SCENTED_CANDLE        = HELPER.createBlock("wither_rose_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WITHER, DyeColor.BLACK, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> WHITE_CLOVER_SCENTED_CANDLE 	= HELPER.createBlock("white_clover_scented_candle", () -> new ScentedCandleBlock(() -> Effects.UNLUCK, DyeColor.WHITE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER_SCENTED_CANDLE  	= HELPER.createBlock("pink_clover_scented_candle", () -> new ScentedCandleBlock(() -> Effects.UNLUCK, DyeColor.PINK, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BUTTERCUP_SCENTED_CANDLE  	= HELPER.createBlock("buttercup_scented_candle", () -> new ScentedCandleBlock(() -> BBEffects.SUNNY.get(), DyeColor.YELLOW, BBProperties.CANDLE), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> CARTWHEEL_SCENTED_CANDLE    			= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "cartwheel_scented_candle", () -> new ScentedCandleBlock(() -> Effects.LEVITATION, DyeColor.PINK, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUEBELL_SCENTED_CANDLE     			= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "bluebell_scented_candle", () -> new ScentedCandleBlock(() -> Effects.HUNGER, DyeColor.BLUE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VIOLET_SCENTED_CANDLE       			= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "violet_scented_candle", () -> new ScentedCandleBlock(() -> Effects.INVISIBILITY, DyeColor.PURPLE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DIANTHUS_SCENTED_CANDLE     			= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "dianthus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.STRENGTH, DyeColor.GREEN, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_LOTUS_FLOWER_SCENTED_CANDLE    	= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "red_lotus_flower_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SLOW_FALLING, DyeColor.RED, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_LOTUS_FLOWER_SCENTED_CANDLE    	= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "white_lotus_flower_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SLOW_FALLING, DyeColor.WHITE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_HIBISCUS_SCENTED_CANDLE		= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "yellow_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING, DyeColor.YELLOW, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_HIBISCUS_SCENTED_CANDLE    	= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "orange_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING, DyeColor.ORANGE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_HIBISCUS_SCENTED_CANDLE     		= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "red_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING, DyeColor.RED, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_HIBISCUS_SCENTED_CANDLE    		= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "pink_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING, DyeColor.PINK, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAGENTA_HIBISCUS_SCENTED_CANDLE   	= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "magenta_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING, DyeColor.MAGENTA, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_HIBISCUS_SCENTED_CANDLE    	= HELPER.createCompatBlock(CompatMods.ENVIRONMENTAL, "purple_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING, DyeColor.PURPLE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WARM_MONKEY_BRUSH_SCENTED_CANDLE    	= HELPER.createCompatBlock(CompatMods.ATMOSPHERIC, "warm_monkey_brush_scented_candle", () -> new ScentedCandleBlock(getCompatEffect(CompatMods.ATMOSPHERIC, CompatEffects.RELIEF), DyeColor.YELLOW, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HOT_MONKEY_BRUSH_SCENTED_CANDLE    	= HELPER.createCompatBlock(CompatMods.ATMOSPHERIC, "hot_monkey_brush_scented_candle", () -> new ScentedCandleBlock(getCompatEffect(CompatMods.ATMOSPHERIC, CompatEffects.RELIEF), DyeColor.ORANGE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SCALDING_MONKEY_BRUSH_SCENTED_CANDLE  = HELPER.createCompatBlock(CompatMods.ATMOSPHERIC, "scalding_monkey_brush_scented_candle", () -> new ScentedCandleBlock(getCompatEffect(CompatMods.ATMOSPHERIC, CompatEffects.RELIEF), DyeColor.RED, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GILIA_SCENTED_CANDLE    				= HELPER.createCompatBlock(CompatMods.ATMOSPHERIC, "gilia_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SPEED, DyeColor.PURPLE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YUCCA_FLOWER_SCENTED_CANDLE    		= HELPER.createCompatBlock(CompatMods.ATMOSPHERIC, "yucca_flower_scented_candle", () -> new ScentedCandleBlock(getCompatEffect(CompatMods.ATMOSPHERIC, CompatEffects.PERSISTENCE), DyeColor.LIGHT_GRAY, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_SEAROCKET_SCENTED_CANDLE    		= HELPER.createCompatBlock(CompatMods.UPGRADE_AQUATIC, "pink_searocket_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WATER_BREATHING, DyeColor.PINK, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_SEAROCKET_SCENTED_CANDLE    	= HELPER.createCompatBlock(CompatMods.UPGRADE_AQUATIC, "white_searocket_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WATER_BREATHING, DyeColor.WHITE, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> AUTUMN_CROCUS_SCENTED_CANDLE    		= HELPER.createCompatBlock(CompatMods.AUTUMNITY, "autumn_crocus_scented_candle", () -> new ScentedCandleBlock(getCompatEffect(CompatMods.AUTUMNITY, CompatEffects.FOUL_TASTE), DyeColor.MAGENTA, BBProperties.CANDLE), ItemGroup.DECORATIONS);
	
	private static Supplier<Effect> getCompatEffect(String modid, ResourceLocation effect) {
		return (ModList.get().isLoaded(modid) ? () -> ForgeRegistries.POTIONS.getValue(effect) : () -> null);
	}
}
