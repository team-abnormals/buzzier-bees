package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.blocks.BookshelfBlock;
import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.bagel.buzzierbees.common.blocks.CartwheelBlock;
import com.bagel.buzzierbees.common.blocks.CrystallizedHoneyBlock;
import com.bagel.buzzierbees.common.blocks.HoneyLamp;
import com.bagel.buzzierbees.common.blocks.ScentedCandleBlock;
import com.bagel.buzzierbees.common.blocks.VerticalSlabBlock;
import com.bagel.buzzierbees.core.BuzzierBees;
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
	
	// Flower Pots //
	
	public static final RegistryObject<Block> POTTED_CARTWHEEL 	  		= RegistryUtils.createBlockNoItem("potted_cartwheel", () -> new FlowerPotBlock(CARTWHEEL.get(), PropertyUtils.POT));
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
	public static final RegistryObject<Block> PINK_SEAROCKET_SCENTED_CANDLE    		= RegistryUtils.createBlockCompat("upgrade_aquatic", "pink_searocket_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WATER_BREATHING, 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_SEAROCKET_SCENTED_CANDLE    	= RegistryUtils.createBlockCompat("upgrade_aquatic", "white_searocket_scented_candle", () -> new ScentedCandleBlock(() -> Effects.WATER_BREATHING, 70, 0, PropertyUtils.CANDLE), ItemGroup.DECORATIONS);
}