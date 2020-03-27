package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.blocks.BookshelfBlock;
import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.bagel.buzzierbees.common.blocks.CartwheelBlock;
import com.bagel.buzzierbees.common.blocks.CompatFlowerPotBlock;
import com.bagel.buzzierbees.common.blocks.CompatHangingFlowerPotBlock;
import com.bagel.buzzierbees.common.blocks.CrystallizedHoneyBlock;
import com.bagel.buzzierbees.common.blocks.HangingFlowerPotBlock;
import com.bagel.buzzierbees.common.blocks.HoneyLamp;
import com.bagel.buzzierbees.common.blocks.PottedCartwheelBlock;
import com.bagel.buzzierbees.common.blocks.ScentedCandleBlock;
import com.bagel.buzzierbees.common.blocks.VerticalSlabBlock;
import com.bagel.buzzierbees.core.BuzzierBees;
import com.bagel.buzzierbees.core.util.CompatBlocks;
import com.bagel.buzzierbees.core.util.PropertyUtils;
import com.bagel.buzzierbees.core.util.RegistryUtils;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBBlocks
{	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, BuzzierBees.MODID);
	
	// Beehives //
	
	public static final RegistryObject<Block> SPRUCE_BEEHIVE   	= RegistryUtils.createBlock("spruce_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BIRCH_BEEHIVE    	= RegistryUtils.createBlock("birch_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> JUNGLE_BEEHIVE   	= RegistryUtils.createBlock("jungle_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ACACIA_BEEHIVE   	= RegistryUtils.createBlock("acacia_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DARK_OAK_BEEHIVE	= RegistryUtils.createBlock("dark_oak_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CRIMSON_BEEHIVE	= RegistryUtils.createBlock("crimson_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), null);
	public static final RegistryObject<Block> WARPED_BEEHIVE	= RegistryUtils.createBlock("warped_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), null);
	
	// Compatability Beehives //
	
	public static final RegistryObject<Block> ROSEWOOD_BEEHIVE 	= RegistryUtils.createBlockCompat("atmospheric", "rosewood_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YUCCA_BEEHIVE    	= RegistryUtils.createBlockCompat("atmospheric", "yucca_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> KOUSA_BEEHIVE    	= RegistryUtils.createBlockCompat("atmospheric", "kousa_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ASPEN_BEEHIVE    	= RegistryUtils.createBlockCompat("atmospheric", "aspen_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WILLOW_BEEHIVE   	= RegistryUtils.createBlockCompat("swampexpansion", "willow_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WISTERIA_BEEHIVE 	= RegistryUtils.createBlockCompat("bloomful", "wisteria_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BAMBOO_BEEHIVE	= RegistryUtils.createBlockCompat("bambooblocks", "bamboo_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAPLE_BEEHIVE    	= RegistryUtils.createBlockCompat("autumnity", "maple_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DRIFTWOOD_BEEHIVE	= RegistryUtils.createBlockCompat("upgrade_aquatic", "driftwood_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RIVER_BEEHIVE		= RegistryUtils.createBlockCompat("upgrade_aquatic", "river_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POISE_BEEHIVE		= RegistryUtils.createBlockCompat("endergetic", "poise_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SNAKE_BLOCK_BEEHIVE = RegistryUtils.createBlock("snake_block_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.DARK_PRISMARINE)), null);
	public static final RegistryObject<Block> BOP_FIR_BEEHIVE 		= RegistryUtils.createBlockCompat("biomesoplenty", "bop_fir_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_DEAD_BEEHIVE 		= RegistryUtils.createBlockCompat("biomesoplenty", "bop_dead_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_PALM_BEEHIVE 		= RegistryUtils.createBlockCompat("biomesoplenty", "bop_palm_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_MAGIC_BEEHIVE 	= RegistryUtils.createBlockCompat("biomesoplenty", "bop_magic_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_CHERRY_BEEHIVE 	= RegistryUtils.createBlockCompat("biomesoplenty", "bop_cherry_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_UMBRAN_BEEHIVE 	= RegistryUtils.createBlockCompat("biomesoplenty", "bop_umbran_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_WILLOW_BEEHIVE 	= RegistryUtils.createBlockCompat("biomesoplenty", "bop_willow_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_REDWOOD_BEEHIVE 	= RegistryUtils.createBlockCompat("biomesoplenty", "bop_redwood_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_HELLBARK_BEEHIVE 	= RegistryUtils.createBlockCompat("biomesoplenty", "bop_hellbark_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_MAHOGANY_BEEHIVE 	= RegistryUtils.createBlockCompat("biomesoplenty", "bop_mahogany_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BOP_JACARANDA_BEEHIVE = RegistryUtils.createBlockCompat("biomesoplenty", "bop_jacaranda_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);

	// Misc //
	
	public static final RegistryObject<Block> WAX_BLOCK 				= RegistryUtils.createBlock("wax_block", () -> new Block(Block.Properties.create(Material.CORAL).slipperiness(0.95F).hardnessAndResistance(0.3F).sound(SoundType.CORAL)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CRYSTALLIZED_HONEY_BLOCK  = RegistryUtils.createBlock("crystallized_honey_block", () -> new CrystallizedHoneyBlock(Block.Properties.create(Material.CAKE).notSolid().slipperiness(0.98F).hardnessAndResistance(0.3F).sound(SoundType.GLASS)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HONEY_LAMP 				= RegistryUtils.createBlock("honey_lamp", () -> new HoneyLamp(Block.Properties.from(Blocks.END_ROD).sound(SoundType.field_226947_m_)), ItemGroup.DECORATIONS);

	// Hive Planks //
	
	public static final RegistryObject<Block> HIVE_PLANKS          = RegistryUtils.createBlock("hive_planks", () -> new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), ItemGroup.BUILDING_BLOCKS); 
	public static final RegistryObject<Block> HIVE_STAIRS          = RegistryUtils.createBlock("hive_stairs", () -> new StairsBlock(HIVE_PLANKS.get().getDefaultState(), Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_SLAB            = RegistryUtils.createBlock("hive_slab", () -> new SlabBlock(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_FENCE           = RegistryUtils.createBlock("hive_fence", () -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HIVE_PRESSURE_PLATE  = RegistryUtils.createBlock("hive_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_TRAPDOOR        = RegistryUtils.createBlock("hive_trapdoor", () -> new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR).notSolid()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_FENCE_GATE      = RegistryUtils.createBlock("hive_fence_gate", () -> new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_BUTTON          = RegistryUtils.createBlock("hive_button", () -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_DOOR            = RegistryUtils.createBlock("hive_door", () -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR).notSolid()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> VERTICAL_HIVE_PLANKS = RegistryUtils.createBlockCompat("quark", "vertical_hive_planks", () -> new Block(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_VERTICAL_SLAB   = RegistryUtils.createBlockCompat("quark", "hive_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_LADDER          = RegistryUtils.createBlockCompat("quark", "hive_ladder", () -> new LadderBlock(Block.Properties.from(Blocks.LADDER).notSolid()), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HIVE_BOOKSHELF       = RegistryUtils.createBlockCompat("quark", "hive_bookshelf", () -> new BookshelfBlock(Block.Properties.from(Blocks.BOOKSHELF).notSolid()), ItemGroup.DECORATIONS);
	
	// Honey Bricks //
	
	public static final RegistryObject<Block> HONEY_BRICKS              = RegistryUtils.createBlock("honey_bricks", () -> new Block(Block.Properties.from(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEY_BRICK_STAIRS 	    = RegistryUtils.createBlock("honey_brick_stairs", () -> new StairsBlock(HONEY_BRICKS.get().getDefaultState(), Block.Properties.from(Blocks.BRICK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEY_BRICK_SLAB 	        = RegistryUtils.createBlock("honey_brick_slab", () -> new SlabBlock(Block.Properties.from(Blocks.BRICK_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEY_BRICK_WALL 	        = RegistryUtils.createBlock("honey_brick_wall", () -> new WallBlock(Block.Properties.from(Blocks.BRICK_WALL)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HONEY_BRICK_VERTICAL_SLAB = RegistryUtils.createBlockCompat("quark", "honey_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(HONEY_BRICKS.get())), ItemGroup.BUILDING_BLOCKS);
		
	// Flowers //
	
	public static final RegistryObject<Block> CARTWHEEL 		= RegistryUtils.createBlock("cartwheel", () -> new CartwheelBlock(Effects.SPEED, 11, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUEBELL 			= RegistryUtils.createBlock("bluebell", () -> new FlowerBlock(Effects.WATER_BREATHING, 6, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VIOLET 			= RegistryUtils.createBlock("violet", () -> new FlowerBlock(Effects.INVISIBILITY, 6, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DIANTHUS 			= RegistryUtils.createBlock("jolyce", () -> new FlowerBlock(Effects.STRENGTH, 8, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> COLUMBINE 		= RegistryUtils.createBlock("columbine", () -> new FlowerBlock(Effects.MINING_FATIGUE, 6, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_CLOVER 		= RegistryUtils.createBlock("white_clover", () -> new FlowerBlock(Effects.UNLUCK, 30, PropertyUtils.FLOWER.notSolid()), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER 		= RegistryUtils.createBlock("pink_clover", () -> new FlowerBlock(Effects.UNLUCK, 60, PropertyUtils.FLOWER.notSolid()), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_HIBISCUS	= RegistryUtils.createBlock("daybloom", () -> new FlowerBlock(Effects.GLOWING, 8, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_HIBISCUS 	= RegistryUtils.createBlock("orange_hibiscus", () -> new FlowerBlock(Effects.GLOWING, 8, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_HIBISCUS 		= RegistryUtils.createBlock("red_hibiscus", () -> new FlowerBlock(Effects.GLOWING, 8, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_HIBISCUS 	= RegistryUtils.createBlock("pink_hibiscus", () -> new FlowerBlock(Effects.GLOWING, 8, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAGENTA_HIBISCUS 	= RegistryUtils.createBlock("magenta_hibiscus", () -> new FlowerBlock(Effects.GLOWING, 8, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_HIBISCUS 	= RegistryUtils.createBlock("purple_hibiscus", () -> new FlowerBlock(Effects.GLOWING, 8, PropertyUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BIRD_OF_PARADISE 	= RegistryUtils.createBlock("bird_of_paradise", () -> new TallFlowerBlock(PropertyUtils.FLOWER), ItemGroup.DECORATIONS);

	// Candles //
	
	public static final RegistryObject<Block> CANDLE 			= RegistryUtils.createBlock("candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_CANDLE 		= RegistryUtils.createBlock("white_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_CANDLE 	= RegistryUtils.createBlock("orange_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAGENTA_CANDLE 	= RegistryUtils.createBlock("magenta_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_BLUE_CANDLE = RegistryUtils.createBlock("light_blue_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_CANDLE 	= RegistryUtils.createBlock("yellow_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIME_CANDLE 		= RegistryUtils.createBlock("lime_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CANDLE 		= RegistryUtils.createBlock("pink_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GRAY_CANDLE 		= RegistryUtils.createBlock("gray_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_GRAY_CANDLE = RegistryUtils.createBlock("light_gray_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CYAN_CANDLE 		= RegistryUtils.createBlock("cyan_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_CANDLE 	= RegistryUtils.createBlock("purple_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_CANDLE 		= RegistryUtils.createBlock("blue_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BROWN_CANDLE 		= RegistryUtils.createBlock("brown_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GREEN_CANDLE 		= RegistryUtils.createBlock("green_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_CANDLE 		= RegistryUtils.createBlock("red_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLACK_CANDLE 		= RegistryUtils.createBlock("black_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> AMBER_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "amber_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BEIGE_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "beige_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CREAM_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "cream_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DARK_GREEN_CANDLE   = RegistryUtils.createBlockCompat("flamboyant", "dark_green_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> FOREST_GREEN_CANDLE = RegistryUtils.createBlockCompat("flamboyant", "forest_green_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HOT_PINK_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "hot_pink_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> INDIGO_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "indigo_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAROON_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "maroon_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> NAVY_CANDLE 	      = RegistryUtils.createBlockCompat("flamboyant", "navy_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> OLIVE_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "olive_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PALE_GREEN_CANDLE   = RegistryUtils.createBlockCompat("flamboyant", "pale_green_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PALE_PINK_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "pale_pink_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PALE_YELLOW_CANDLE  = RegistryUtils.createBlockCompat("flamboyant", "pale_yellow_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SKY_BLUE_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "sky_blue_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SLATE_GRAY_CANDLE   = RegistryUtils.createBlockCompat("flamboyant", "slate_gray_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VIOLET_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "violet_candle", () -> new CandleBlock(PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	
	// Scented Candles //
	
	public static final RegistryObject<Block> ALLIUM_SCENTED_CANDLE             = RegistryUtils.createBlock("allium_scented_candle", () -> new ScentedCandleBlock(() -> Effects.FIRE_RESISTANCE, 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> AZURE_BLUET_SCENTED_CANDLE        = RegistryUtils.createBlock("azure_bluet_scented_candle", () -> new ScentedCandleBlock(() -> Effects.BLINDNESS,       70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_ORCHID_SCENTED_CANDLE        = RegistryUtils.createBlock("blue_orchid_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SATURATION,      70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DANDELION_SCENTED_CANDLE          = RegistryUtils.createBlock("dandelion_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SATURATION,      70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CORNFLOWER_SCENTED_CANDLE         = RegistryUtils.createBlock("cornflower_scented_candle", () -> new ScentedCandleBlock(() -> Effects.JUMP_BOOST,      70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LILY_OF_THE_VALLEY_SCENTED_CANDLE = RegistryUtils.createBlock("lily_of_the_valley_scented_candle", () -> new ScentedCandleBlock(() -> Effects.POISON,          70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> OXEYE_DAISY_SCENTED_CANDLE        = RegistryUtils.createBlock("oxeye_daisy_scented_candle", () -> new ScentedCandleBlock(() -> Effects.REGENERATION,    70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POPPY_SCENTED_CANDLE              = RegistryUtils.createBlock("poppy_scented_candle", () -> new ScentedCandleBlock(() -> Effects.NIGHT_VISION,    70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_TULIP_SCENTED_CANDLE        = RegistryUtils.createBlock("white_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS,        70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_TULIP_SCENTED_CANDLE       = RegistryUtils.createBlock("orange_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS,        70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_TULIP_SCENTED_CANDLE         = RegistryUtils.createBlock("pink_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS,        70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_TULIP_SCENTED_CANDLE          = RegistryUtils.createBlock("red_tulip_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WEAKNESS,        70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WITHER_ROSE_SCENTED_CANDLE        = RegistryUtils.createBlock("wither_rose_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WITHER,          70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> CARTWHEEL_SCENTED_CANDLE    = RegistryUtils.createBlock("cartwheel_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SPEED, 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUEBELL_SCENTED_CANDLE     = RegistryUtils.createBlock("bluebell_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WATER_BREATHING, 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VIOLET_SCENTED_CANDLE       = RegistryUtils.createBlock("violet_scented_candle", () -> new ScentedCandleBlock(() -> Effects.INVISIBILITY,    70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DIANTHUS_SCENTED_CANDLE     = RegistryUtils.createBlock("jolyce_scented_candle", () -> new ScentedCandleBlock(() -> Effects.STRENGTH,        70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> COLUMBINE_SCENTED_CANDLE    = RegistryUtils.createBlock("columbine_scented_candle", () -> new ScentedCandleBlock(() -> Effects.MINING_FATIGUE,  70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_CLOVER_SCENTED_CANDLE = RegistryUtils.createBlock("white_clover_scented_candle", () -> new ScentedCandleBlock(() -> Effects.UNLUCK,          70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER_SCENTED_CANDLE  = RegistryUtils.createBlock("pink_clover_scented_candle", () -> new ScentedCandleBlock(() -> Effects.UNLUCK,          70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_HIBISCUS_SCENTED_CANDLE	= RegistryUtils.createBlock("daybloom_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING,         70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_HIBISCUS_SCENTED_CANDLE    = RegistryUtils.createBlock("orange_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING,         70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_HIBISCUS_SCENTED_CANDLE     	= RegistryUtils.createBlock("red_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING,         70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_HIBISCUS_SCENTED_CANDLE    	= RegistryUtils.createBlock("pink_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING,         70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAGENTA_HIBISCUS_SCENTED_CANDLE   = RegistryUtils.createBlock("magenta_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING,         70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_HIBISCUS_SCENTED_CANDLE    = RegistryUtils.createBlock("purple_hibiscus_scented_candle", () -> new ScentedCandleBlock(() -> Effects.GLOWING,         70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> WARM_MONKEY_BRUSH_SCENTED_CANDLE    	= RegistryUtils.createBlockCompat("atmospheric", "warm_monkey_brush_scented_candle", () -> new ScentedCandleBlock(() -> (ModList.get().isLoaded("atmospheric") ? ForgeRegistries.POTIONS.getValue(new ResourceLocation("atmospheric:relief")) : null), 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HOT_MONKEY_BRUSH_SCENTED_CANDLE    	= RegistryUtils.createBlockCompat("atmospheric", "hot_monkey_brush_scented_candle", () -> new ScentedCandleBlock(() -> (ModList.get().isLoaded("atmospheric") ? ForgeRegistries.POTIONS.getValue(new ResourceLocation("atmospheric:relief")): null), 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SCALDING_MONKEY_BRUSH_SCENTED_CANDLE  = RegistryUtils.createBlockCompat("atmospheric", "scalding_monkey_brush_scented_candle", () -> new ScentedCandleBlock(() -> (ModList.get().isLoaded("atmospheric") ? ForgeRegistries.POTIONS.getValue(new ResourceLocation("atmospheric:relief")) : null), 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GILIA_SCENTED_CANDLE    				= RegistryUtils.createBlockCompat("atmospheric", "gilia_scented_candle", () -> new ScentedCandleBlock(() -> Effects.SPEED, 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YUCCA_FLOWER_SCENTED_CANDLE    		= RegistryUtils.createBlockCompat("atmospheric", "yucca_flower_scented_candle", () -> new ScentedCandleBlock(() -> Effects.BAD_OMEN, 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	
	// Flower Pots //
		
	public static final RegistryObject<Block> POTTED_CARTWHEEL 	  		= RegistryUtils.createBlockNoItem("potted_cartwheel", () -> new PottedCartwheelBlock(CARTWHEEL.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_BLUEBELL     		= RegistryUtils.createBlockNoItem("potted_bluebell", () -> new FlowerPotBlock(BLUEBELL.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_VIOLET 	  		= RegistryUtils.createBlockNoItem("potted_violet", () -> new FlowerPotBlock(VIOLET.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_DIANTHUS 	  		= RegistryUtils.createBlockNoItem("potted_jolyce", () -> new FlowerPotBlock(DIANTHUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_COLUMBINE 	  		= RegistryUtils.createBlockNoItem("potted_columbine", () -> new FlowerPotBlock(COLUMBINE.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_WHITE_CLOVER 		= RegistryUtils.createBlockNoItem("potted_white_clover", () -> new FlowerPotBlock(WHITE_CLOVER.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PINK_CLOVER  		= RegistryUtils.createBlockNoItem("potted_pink_clover", () -> new FlowerPotBlock(PINK_CLOVER.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_YELLOW_HIBISCUS	= RegistryUtils.createBlockNoItem("potted_daybloom", () -> new FlowerPotBlock(YELLOW_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_ORANGE_HIBISCUS	= RegistryUtils.createBlockNoItem("potted_orange_hibiscus", () -> new FlowerPotBlock(ORANGE_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_RED_HIBISCUS 		= RegistryUtils.createBlockNoItem("potted_red_hibiscus", () -> new FlowerPotBlock(RED_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PINK_HIBISCUS  	= RegistryUtils.createBlockNoItem("potted_pink_hibiscus", () -> new FlowerPotBlock(PINK_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_MAGENTA_HIBISCUS	= RegistryUtils.createBlockNoItem("potted_magenta_hibiscus", () -> new FlowerPotBlock(MAGENTA_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PURPLE_HIBISCUS	= RegistryUtils.createBlockNoItem("potted_purple_hibiscus", () -> new FlowerPotBlock(PURPLE_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_BIRD_OF_PARADISE	= RegistryUtils.createBlockNoItem("potted_bird_of_paradise", () -> new FlowerPotBlock(BIRD_OF_PARADISE.get(), PropertyUtils.POT));
	
	public static final RegistryObject<Block> POTTED_WHEAT     		= RegistryUtils.createBlockNoItem("potted_wheat_seeds", () -> new FlowerPotBlock(Blocks.WHEAT, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_CARROT     	= RegistryUtils.createBlockNoItem("potted_carrot", () -> new FlowerPotBlock(Blocks.CARROTS, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_POTATO     	= RegistryUtils.createBlockNoItem("potted_potato", () -> new FlowerPotBlock(Blocks.POTATOES, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_BEETROOT     	= RegistryUtils.createBlockNoItem("potted_beetroot_seeds", () -> new FlowerPotBlock(Blocks.BEETROOTS, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_SWEET_BERRIES	= RegistryUtils.createBlockNoItem("potted_sweet_berries", () -> new FlowerPotBlock(Blocks.SWEET_BERRY_BUSH, PropertyUtils.POT));
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
	public static final RegistryObject<Block> POTTED_JACK_O_LANTERN = RegistryUtils.createBlockNoItem("potted_jack_o_lantern", () -> new FlowerPotBlock(Blocks.JACK_O_LANTERN, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_COCOA_BEANS 	= RegistryUtils.createBlockNoItem("potted_cocoa_beans", () -> new FlowerPotBlock(Blocks.COCOA, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_SEA_PICKLE 	= RegistryUtils.createBlockNoItem("potted_sea_pickle", () -> new FlowerPotBlock(Blocks.SEA_PICKLE, PropertyUtils.POT));

	public static final RegistryObject<Block> POTTED_BAMBOO_TORCH     		= RegistryUtils.createBlockNoItem("potted_bamboo_torch", () -> new CompatFlowerPotBlock(CompatBlocks.BAMBOO_TORCH, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_WHITE_DELPHINIUM     	= RegistryUtils.createBlockNoItem("potted_white_delphinium", () -> new CompatFlowerPotBlock(CompatBlocks.WHITE_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_BLUE_DELPHINIUM     	= RegistryUtils.createBlockNoItem("potted_blue_delphinium", () -> new CompatFlowerPotBlock(CompatBlocks.BLUE_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PINK_DELPHINIUM     	= RegistryUtils.createBlockNoItem("potted_pink_delphinium", () -> new CompatFlowerPotBlock(CompatBlocks.PINK_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_PURPLE_DELPHINIUM		= RegistryUtils.createBlockNoItem("potted_purple_delphinium", () -> new CompatFlowerPotBlock(CompatBlocks.PURPLE_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_OVERWORLD_CORROCK_CROWN= RegistryUtils.createBlockNoItem("potted_corrock_crown_standing_overworld", () -> new CompatFlowerPotBlock(CompatBlocks.OVERWORLD_CORROCK_CROWN, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_NETHER_CORROCK_CROWN   = RegistryUtils.createBlockNoItem("potted_corrock_crown_standing_nether", () -> new CompatFlowerPotBlock(CompatBlocks.NETHER_CORROCK_CROWN, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_END_CORROCK_CROWN		= RegistryUtils.createBlockNoItem("potted_corrock_crown_standing_end", () -> new CompatFlowerPotBlock(CompatBlocks.END_CORROCK_CROWN, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_TALL_POISE_BUSH     	= RegistryUtils.createBlockNoItem("potted_poise_grass_tall", () -> new CompatFlowerPotBlock(CompatBlocks.TALL_POISE_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_OVERWORLD_CORROCK     	= RegistryUtils.createBlockNoItem("potted_corrock_overworld", () -> new CompatFlowerPotBlock(CompatBlocks.OVERWORLD_CORROCK, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_NETHER_CORROCK     	= RegistryUtils.createBlockNoItem("potted_corrock_nether", () -> new CompatFlowerPotBlock(CompatBlocks.NETHER_CORROCK, PropertyUtils.POT));
	public static final RegistryObject<Block> POTTED_END_CORROCK     		= RegistryUtils.createBlockNoItem("potted_corrock_end", () -> new CompatFlowerPotBlock(CompatBlocks.END_CORROCK, PropertyUtils.POT));
	
	// Hanging Flower Pots //
	
	public static final RegistryObject<Block> HANGING_FLOWER_POT  				= RegistryUtils.createBlockNoItem("hanging_flower_pot", () -> new HangingFlowerPotBlock(Blocks.AIR, PropertyUtils.POT));
	
	public static final RegistryObject<Block> HANGING_POTTED_CARTWHEEL 	  		= RegistryUtils.createBlockNoItem("hanging_potted_cartwheel", () -> new HangingFlowerPotBlock(CARTWHEEL.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUEBELL     		= RegistryUtils.createBlockNoItem("hanging_potted_bluebell", () -> new HangingFlowerPotBlock(BLUEBELL.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_VIOLET 	  		= RegistryUtils.createBlockNoItem("hanging_potted_violet", () -> new HangingFlowerPotBlock(VIOLET.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DIANTHUS 	  		= RegistryUtils.createBlockNoItem("hanging_potted_jolyce", () -> new HangingFlowerPotBlock(DIANTHUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_COLUMBINE 	  		= RegistryUtils.createBlockNoItem("hanging_potted_columbine", () -> new HangingFlowerPotBlock(COLUMBINE.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_CLOVER 		= RegistryUtils.createBlockNoItem("hanging_potted_white_clover", () -> new HangingFlowerPotBlock(WHITE_CLOVER.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_CLOVER  		= RegistryUtils.createBlockNoItem("hanging_potted_pink_clover", () -> new HangingFlowerPotBlock(PINK_CLOVER.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YELLOW_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_daybloom", () -> new HangingFlowerPotBlock(YELLOW_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ORANGE_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_orange_hibiscus", () -> new HangingFlowerPotBlock(ORANGE_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_RED_HIBISCUS 		= RegistryUtils.createBlockNoItem("hanging_potted_red_hibiscus", () -> new HangingFlowerPotBlock(RED_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_HIBISCUS  	= RegistryUtils.createBlockNoItem("hanging_potted_pink_hibiscus", () -> new HangingFlowerPotBlock(PINK_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_MAGENTA_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_magenta_hibiscus", () -> new HangingFlowerPotBlock(MAGENTA_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_HIBISCUS	= RegistryUtils.createBlockNoItem("hanging_potted_purple_hibiscus", () -> new HangingFlowerPotBlock(PURPLE_HIBISCUS.get(), PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BIRD_OF_PARADISE	= RegistryUtils.createBlockNoItem("hanging_potted_bird_of_paradise", () -> new HangingFlowerPotBlock(BIRD_OF_PARADISE.get(), PropertyUtils.POT));
	
	public static final RegistryObject<Block> HANGING_POTTED_BAMBOO     		= RegistryUtils.createBlockNoItem("hanging_potted_bamboo", () -> new HangingFlowerPotBlock(Blocks.BAMBOO, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OAK_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_oak_sapling", () -> new HangingFlowerPotBlock(Blocks.OAK_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SPRUCE_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_spruce_sapling", () -> new HangingFlowerPotBlock(Blocks.SPRUCE_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BIRCH_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_birch_sapling", () -> new HangingFlowerPotBlock(Blocks.BIRCH_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_JUNGLE_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_jungle_sapling", () -> new HangingFlowerPotBlock(Blocks.JUNGLE_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ACACIA_SAPLING    	= RegistryUtils.createBlockNoItem("hanging_potted_acacia_sapling", () -> new HangingFlowerPotBlock(Blocks.ACACIA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DARK_OAK_SAPLING   = RegistryUtils.createBlockNoItem("hanging_potted_dark_oak_sapling", () -> new HangingFlowerPotBlock(Blocks.DARK_OAK_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_FERN     			= RegistryUtils.createBlockNoItem("hanging_potted_fern", () -> new HangingFlowerPotBlock(Blocks.FERN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DANDELION     		= RegistryUtils.createBlockNoItem("hanging_potted_dandelion", () -> new HangingFlowerPotBlock(Blocks.DANDELION, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_POPPY     			= RegistryUtils.createBlockNoItem("hanging_potted_poppy", () -> new HangingFlowerPotBlock(Blocks.POPPY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_ORCHID 		= RegistryUtils.createBlockNoItem("hanging_potted_blue_orchid", () -> new HangingFlowerPotBlock(Blocks.BLUE_ORCHID, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ALLIUM     		= RegistryUtils.createBlockNoItem("hanging_potted_allium", () -> new HangingFlowerPotBlock(Blocks.ALLIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_AZURE_BLUET     	= RegistryUtils.createBlockNoItem("hanging_potted_azure_bluet", () -> new HangingFlowerPotBlock(Blocks.AZURE_BLUET, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_RED_TULIP     		= RegistryUtils.createBlockNoItem("hanging_potted_red_tulip", () -> new HangingFlowerPotBlock(Blocks.RED_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ORANGE_TULIP     	= RegistryUtils.createBlockNoItem("hanging_potted_orange_tulip", () -> new HangingFlowerPotBlock(Blocks.ORANGE_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_TULIP     	= RegistryUtils.createBlockNoItem("hanging_potted_white_tulip", () -> new HangingFlowerPotBlock(Blocks.WHITE_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_TULIP     	= RegistryUtils.createBlockNoItem("hanging_potted_pink_tulip", () -> new HangingFlowerPotBlock(Blocks.PINK_TULIP, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OXEYE_DAISY     	= RegistryUtils.createBlockNoItem("hanging_potted_oxeye_daisy", () -> new HangingFlowerPotBlock(Blocks.OXEYE_DAISY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CORNFLOWER     	= RegistryUtils.createBlockNoItem("hanging_potted_cornflower", () -> new HangingFlowerPotBlock(Blocks.CORNFLOWER, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LILY_OF_THE_VALLEY	= RegistryUtils.createBlockNoItem("hanging_potted_lily_of_the_valley", () -> new HangingFlowerPotBlock(Blocks.LILY_OF_THE_VALLEY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WITHER_ROSE     	= RegistryUtils.createBlockNoItem("hanging_potted_wither_rose", () -> new HangingFlowerPotBlock(Blocks.WITHER_ROSE, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_RED_MUSHROOM     	= RegistryUtils.createBlockNoItem("hanging_potted_red_mushroom", () -> new HangingFlowerPotBlock(Blocks.RED_MUSHROOM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BROWN_MUSHROOM     = RegistryUtils.createBlockNoItem("hanging_potted_brown_mushroom", () -> new HangingFlowerPotBlock(Blocks.BROWN_MUSHROOM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_DEAD_BUSH     		= RegistryUtils.createBlockNoItem("hanging_potted_dead_bush", () -> new HangingFlowerPotBlock(Blocks.DEAD_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CACTUS     		= RegistryUtils.createBlockNoItem("hanging_potted_cactus", () -> new HangingFlowerPotBlock(Blocks.CACTUS, PropertyUtils.POT));
	
	public static final RegistryObject<Block> HANGING_POTTED_WHEAT     		= RegistryUtils.createBlockNoItem("hanging_potted_wheat_seeds", () -> new HangingFlowerPotBlock(Blocks.WHEAT, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CARROT     	= RegistryUtils.createBlockNoItem("hanging_potted_carrot", () -> new HangingFlowerPotBlock(Blocks.CARROTS, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_POTATO     	= RegistryUtils.createBlockNoItem("hanging_potted_potato", () -> new HangingFlowerPotBlock(Blocks.POTATOES, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BEETROOT     	= RegistryUtils.createBlockNoItem("hanging_potted_beetroot_seeds", () -> new HangingFlowerPotBlock(Blocks.BEETROOTS, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SWEET_BERRIES	= RegistryUtils.createBlockNoItem("hanging_potted_sweet_berries", () -> new HangingFlowerPotBlock(Blocks.SWEET_BERRY_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SUGAR_CANE     = RegistryUtils.createBlockNoItem("hanging_potted_sugar_cane", () -> new HangingFlowerPotBlock(Blocks.SUGAR_CANE, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_NETHER_WART    = RegistryUtils.createBlockNoItem("hanging_potted_nether_wart", () -> new HangingFlowerPotBlock(Blocks.NETHER_WART, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CHORUS_PLANT   = RegistryUtils.createBlockNoItem("hanging_potted_chorus_plant", () -> new HangingFlowerPotBlock(Blocks.CHORUS_PLANT, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PUMPKIN     	= RegistryUtils.createBlockNoItem("hanging_potted_pumpkin", () -> new HangingFlowerPotBlock(Blocks.PUMPKIN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_MELON     		= RegistryUtils.createBlockNoItem("hanging_potted_melon", () -> new HangingFlowerPotBlock(Blocks.MELON, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ROSE_BUSH     	= RegistryUtils.createBlockNoItem("hanging_potted_rose_bush", () -> new HangingFlowerPotBlock(Blocks.ROSE_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SUNFLOWER     	= RegistryUtils.createBlockNoItem("hanging_potted_sunflower", () -> new HangingFlowerPotBlock(Blocks.SUNFLOWER, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LILAC     		= RegistryUtils.createBlockNoItem("hanging_potted_lilac", () -> new HangingFlowerPotBlock(Blocks.LILAC, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PEONY     		= RegistryUtils.createBlockNoItem("hanging_potted_peony", () -> new HangingFlowerPotBlock(Blocks.PEONY, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_GRASS     		= RegistryUtils.createBlockNoItem("hanging_potted_grass", () -> new HangingFlowerPotBlock(Blocks.GRASS, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_TALL_GRASS     = RegistryUtils.createBlockNoItem("hanging_potted_tall_grass", () -> new HangingFlowerPotBlock(Blocks.TALL_GRASS, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LARGE_FERN    	= RegistryUtils.createBlockNoItem("hanging_potted_large_fern", () -> new HangingFlowerPotBlock(Blocks.LARGE_FERN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_CARVED_PUMPKIN = RegistryUtils.createBlockNoItem("hanging_potted_carved_pumpkin", () -> new HangingFlowerPotBlock(Blocks.CARVED_PUMPKIN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_JACK_O_LANTERN = RegistryUtils.createBlockNoItem("hanging_potted_jack_o_lantern", () -> new HangingFlowerPotBlock(Blocks.JACK_O_LANTERN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_COCOA_BEANS 	= RegistryUtils.createBlockNoItem("hanging_potted_cocoa_beans", () -> new HangingFlowerPotBlock(Blocks.COCOA, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SEA_PICKLE 	= RegistryUtils.createBlockNoItem("hanging_potted_sea_pickle", () -> new HangingFlowerPotBlock(Blocks.SEA_PICKLE, PropertyUtils.POT));

	public static final RegistryObject<Block> HANGING_POTTED_WHITE_WISTERIA_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_white_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.WHITE_WISTERIA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_WISTERIA_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_blue_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.BLUE_WISTERIA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_WISTERIA_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_pink_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.PINK_WISTERIA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_WISTERIA_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_purple_wisteria_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.PURPLE_WISTERIA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_LAVENDER_BLOSSOM_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_lavender_blossom_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.LAVENDER_BLOSSOM_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ORANGE_BLOSSOM_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_orange_blossom_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.ORANGE_BLOSSOM_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_BLOSSOM_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_pink_blossom_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.PINK_BLOSSOM_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_BLOSSOM_SAPLING 		= RegistryUtils.createBlockNoItem("hanging_potted_blue_blossom_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.BLUE_BLOSSOM_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YELLOW_BLOSSOM_SAPLING 	= RegistryUtils.createBlockNoItem("hanging_potted_yellow_blossom_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.YELLOW_BLOSSOM_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ROSEWOOD_SAPLING 			= RegistryUtils.createBlockNoItem("hanging_potted_rosewood_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.ROSEWOOD_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YUCCA_SAPLING 				= RegistryUtils.createBlockNoItem("hanging_potted_yucca_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.YUCCA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_KOUSA_SAPLING 				= RegistryUtils.createBlockNoItem("hanging_potted_kousa_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.KOUSA_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_ASPEN_SAPLING 				= RegistryUtils.createBlockNoItem("hanging_potted_aspen_sapling", () -> new CompatHangingFlowerPotBlock(CompatBlocks.ASPEN_SAPLING, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_POISE_BUSH 				= RegistryUtils.createBlockNoItem("hanging_potted_poise_grass", () -> new CompatHangingFlowerPotBlock(CompatBlocks.POISE_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_PICKERELWEED 			= RegistryUtils.createBlockNoItem("hanging_potted_pickerel_weed_blue", () -> new CompatHangingFlowerPotBlock(CompatBlocks.BLUE_PICKERELWEED, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_PICKERELWEED 		= RegistryUtils.createBlockNoItem("hanging_potted_pickerel_weed_purple", () -> new CompatHangingFlowerPotBlock(CompatBlocks.PURPLE_PICKERELWEED, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_SEAROCKET 			= RegistryUtils.createBlockNoItem("hanging_potted_searocket_white", () -> new CompatHangingFlowerPotBlock(CompatBlocks.WHITE_SEAROCKET, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_SEAROCKET 			= RegistryUtils.createBlockNoItem("hanging_potted_searocket_pink", () -> new CompatHangingFlowerPotBlock(CompatBlocks.PINK_SEAROCKET, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_GLOWSHROOM 				= RegistryUtils.createBlockNoItem("hanging_potted_glowshroom", () -> new CompatHangingFlowerPotBlock(CompatBlocks.GLOWSHROOM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WARM_MONKEY_BRUSH 			= RegistryUtils.createBlockNoItem("hanging_potted_warm_monkey_brush", () -> new CompatHangingFlowerPotBlock(CompatBlocks.WARM_MONKEY_BRUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_HOT_MONKEY_BRUSH 			= RegistryUtils.createBlockNoItem("hanging_potted_monkey_brush", () -> new CompatHangingFlowerPotBlock(CompatBlocks.HOT_MONKEY_BRUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_SCALDING_MONKEY_BRUSH 		= RegistryUtils.createBlockNoItem("hanging_potted_scalding_monkey_brush", () -> new CompatHangingFlowerPotBlock(CompatBlocks.SCALDING_MONKEY_BRUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_GILIA 						= RegistryUtils.createBlockNoItem("hanging_potted_gilia", () -> new CompatHangingFlowerPotBlock(CompatBlocks.GILIA, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_YUCCA_FLOWER 				= RegistryUtils.createBlockNoItem("hanging_potted_yucca_flower", () -> new CompatHangingFlowerPotBlock(CompatBlocks.YUCCA_FLOWER, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BARREL_CACTUS 				= RegistryUtils.createBlockNoItem("hanging_potted_barrel_cactus", () -> new CompatHangingFlowerPotBlock(CompatBlocks.BARREL_CACTUS, PropertyUtils.POT));

	public static final RegistryObject<Block> HANGING_POTTED_BAMBOO_TORCH     		= RegistryUtils.createBlockNoItem("hanging_potted_bamboo_torch", () -> new CompatHangingFlowerPotBlock(CompatBlocks.BAMBOO_TORCH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_WHITE_DELPHINIUM     	= RegistryUtils.createBlockNoItem("hanging_potted_white_delphinium", () -> new CompatHangingFlowerPotBlock(CompatBlocks.WHITE_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_BLUE_DELPHINIUM     	= RegistryUtils.createBlockNoItem("hanging_potted_blue_delphinium", () -> new CompatHangingFlowerPotBlock(CompatBlocks.BLUE_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PINK_DELPHINIUM     	= RegistryUtils.createBlockNoItem("hanging_potted_pink_delphinium", () -> new CompatHangingFlowerPotBlock(CompatBlocks.PINK_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_PURPLE_DELPHINIUM		= RegistryUtils.createBlockNoItem("hanging_potted_purple_delphinium", () -> new CompatHangingFlowerPotBlock(CompatBlocks.PURPLE_DELPHINIUM, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OVERWORLD_CORROCK_CROWN= RegistryUtils.createBlockNoItem("hanging_potted_corrock_crown_standing_overworld", () -> new CompatHangingFlowerPotBlock(CompatBlocks.OVERWORLD_CORROCK_CROWN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_NETHER_CORROCK_CROWN   = RegistryUtils.createBlockNoItem("hanging_potted_corrock_crown_standing_nether", () -> new CompatHangingFlowerPotBlock(CompatBlocks.NETHER_CORROCK_CROWN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_END_CORROCK_CROWN		= RegistryUtils.createBlockNoItem("hanging_potted_corrock_crown_standing_end", () -> new CompatHangingFlowerPotBlock(CompatBlocks.END_CORROCK_CROWN, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_TALL_POISE_BUSH     	= RegistryUtils.createBlockNoItem("hanging_potted_poise_grass_tall", () -> new CompatHangingFlowerPotBlock(CompatBlocks.TALL_POISE_BUSH, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_OVERWORLD_CORROCK     	= RegistryUtils.createBlockNoItem("hanging_potted_corrock_overworld", () -> new CompatHangingFlowerPotBlock(CompatBlocks.OVERWORLD_CORROCK, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_NETHER_CORROCK     	= RegistryUtils.createBlockNoItem("hanging_potted_corrock_nether", () -> new CompatHangingFlowerPotBlock(CompatBlocks.NETHER_CORROCK, PropertyUtils.POT));
	public static final RegistryObject<Block> HANGING_POTTED_END_CORROCK     		= RegistryUtils.createBlockNoItem("hanging_potted_corrock_end", () -> new CompatHangingFlowerPotBlock(CompatBlocks.END_CORROCK, PropertyUtils.POT));
}