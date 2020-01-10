package com.bagel.buzzierbees.common.blocks;

import com.bagel.buzzierbees.common.blocks.stickyblocks.NewCloverHoneyBlock;
import com.bagel.buzzierbees.common.blocks.stickyblocks.NewHoneyBlock;
import com.bagel.buzzierbees.common.blocks.stickyblocks.NewSlimeBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
	
	//Properties
	public static final Block.Properties FLOWER_PROPERTIES 		= Block.Properties.create(Material.PLANTS).func_226896_b_().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties CANDLE_PROPERTIES 		= Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).sound(SoundType.WOOD);
	public static final Block.Properties POT_PROPERTIES    		= Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().func_226896_b_();

	public static BlockItem CARTWHEEL_ITEM;
	
	public static Block WAX_BLOCK;

	@ObjectHolder("minecraft:slime_block")
	public static Block SLIME_BLOCK;
	@ObjectHolder("minecraft:honey_block")
	public static Block HONEY_BLOCK;
	public static Block CLOVER_HONEY_BLOCK;

	public static Block CRYSTALLIZED_HONEY_BLOCK;
	public static Block CRYSTALLIZED_CLOVER_HONEY_BLOCK;

	public static Block SPRUCE_BEEHIVE;
	public static Block BIRCH_BEEHIVE;
	public static Block JUNGLE_BEEHIVE;
	public static Block ACACIA_BEEHIVE;
	public static Block DARK_OAK_BEEHIVE;

	public static Block HONEY_LAMP;

	//Hive Planks
	public static Block HIVE_PLANKS;
	public static Block HIVE_STAIRS;
	public static Block HIVE_SLAB;
	public static Block HIVE_FENCE;
	public static Block HIVE_FENCE_GATE;
	public static Block HIVE_PRESSURE_PLATE;
	public static Block HIVE_BUTTON;
	public static Block HIVE_DOOR;
	public static Block HIVE_TRAPDOOR;
	public static Block HIVE_SIGN;
	public static Block HIVE_WALL_SIGN;

	//Honey Bricks
	public static Block HONEY_BRICKS;
	public static Block HONEY_BRICK_STAIRS;
	public static Block HONEY_BRICK_SLAB;
	public static Block HONEY_BRICK_WALL;

	//Candles
	public static Block CANDLE;
	public static Block WHITE_CANDLE;
	public static Block ORANGE_CANDLE;
	public static Block MAGENTA_CANDLE;
	public static Block LIGHT_BLUE_CANDLE;
	public static Block YELLOW_CANDLE;
	public static Block LIME_CANDLE;
	public static Block PINK_CANDLE;
	public static Block GRAY_CANDLE;
	public static Block LIGHT_GRAY_CANDLE;
	public static Block CYAN_CANDLE;
	public static Block PURPLE_CANDLE;
	public static Block BLUE_CANDLE;
	public static Block BROWN_CANDLE;
	public static Block GREEN_CANDLE;
	public static Block RED_CANDLE;
	public static Block BLACK_CANDLE;
	
	//Scented Candles
	public static Block ALLIUM_SCENTED_CANDLE;
	public static Block AZURE_BLUET_SCENTED_CANDLE;
	public static Block BLUE_ORCHID_SCENTED_CANDLE;
	public static Block DANDELION_SCENTED_CANDLE;
	public static Block CORNFLOWER_SCENTED_CANDLE;
	public static Block LILY_OF_THE_VALLEY_SCENTED_CANDLE;
	public static Block OXEYE_DAISY_SCENTED_CANDLE;
	public static Block POPPY_SCENTED_CANDLE;
	public static Block WHITE_TULIP_SCENTED_CANDLE;
	public static Block ORANGE_TULIP_SCENTED_CANDLE;
	public static Block PINK_TULIP_SCENTED_CANDLE;
	public static Block RED_TULIP_SCENTED_CANDLE;
	public static Block WITHER_ROSE_SCENTED_CANDLE;

	//Mod Scented Candles
	public static Block CARTWHEEL_SCENTED_CANDLE;
	public static Block BLUEBELL_SCENTED_CANDLE;
	public static Block VIOLET_SCENTED_CANDLE;
	public static Block COLUMBINE_SCENTED_CANDLE;
	public static Block JOLYCE_SCENTED_CANDLE;
	public static Block DAYBLOOM_SCENTED_CANDLE;
	public static Block WHITE_CLOVER_SCENTED_CANDLE;
	public static Block PINK_CLOVER_SCENTED_CANDLE;

	//Flowers
	public static Block CARTWHEEL;
	public static Block BLUEBELL;
	public static Block VIOLET;
	public static Block COLUMBINE;
	public static Block JOLYCE;
	public static Block DAYBLOOM;
	public static Block BIRD_OF_PARADISE;
	public static Block WHITE_CLOVER;
	public static Block PINK_CLOVER;

	//Potted Flowers
	public static Block POTTED_CARTWHEEL;
	public static Block POTTED_BLUEBELL;
	public static Block POTTED_VIOLET;
	public static Block POTTED_COLUMBINE;
	public static Block POTTED_JOLYCE;
	public static Block POTTED_DAYBLOOM;
	public static Block POTTED_WHITE_CLOVER;
	public static Block POTTED_PINK_CLOVER;

	//Flamboyant Compat
	public static Block AMBER_CANDLE;
	public static Block BEIGE_CANDLE;
	public static Block CREAM_CANDLE;
	public static Block DARK_GREEN_CANDLE;
	public static Block FOREST_GREEN_CANDLE;
	public static Block HOT_PINK_CANDLE;
	public static Block INDIGO_CANDLE;
	public static Block MAROON_CANDLE;
	public static Block NAVY_CANDLE;
	public static Block OLIVE_CANDLE;
	public static Block PALE_GREEN_CANDLE;
	public static Block PALE_PINK_CANDLE;
	public static Block PALE_YELLOW_CANDLE;
	public static Block SKY_BLUE_CANDLE;
	public static Block SLATE_GRAY_CANDLE;
	public static Block VIOLET_CANDLE;

	//Quark Compat
	public static Block HIVE_BOOKSHELF;
	public static Block HIVE_CHEST;
	public static Block HIVE_LADDER;
	public static Block VERTICAL_HIVE_PLANKS;
	public static Block HIVE_VERTICAL_SLAB;
	public static Block HONEY_BRICK_VERTICAL_SLAB;

	//TODO: 1.2
	public static Block BEEHIVE_BLOCK;
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
		SPRUCE_BEEHIVE = registerBlock(new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), "spruce_beehive", ItemGroup.DECORATIONS);
		BIRCH_BEEHIVE = registerBlock(new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), "birch_beehive", ItemGroup.DECORATIONS);
		JUNGLE_BEEHIVE = registerBlock(new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), "jungle_beehive", ItemGroup.DECORATIONS);
		ACACIA_BEEHIVE = registerBlock(new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), "acacia_beehive", ItemGroup.DECORATIONS);
		DARK_OAK_BEEHIVE = registerBlock(new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), "dark_oak_beehive", ItemGroup.DECORATIONS);
		
		WAX_BLOCK          = registerBlock(new Block(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.3F).sound(SoundType.CORAL)), "wax_block", ItemGroup.DECORATIONS);
		HONEY_LAMP         = registerBlock(new HoneyLamp(Block.Properties.from(Blocks.END_ROD).sound(SoundType.field_226947_m_)), "honey_lamp", ItemGroup.DECORATIONS);

		SLIME_BLOCK 		= registerBlock(new NewSlimeBlock(Block.Properties.create(Material.CLAY, MaterialColor.GRASS).slipperiness(0.8F).sound(SoundType.SLIME).func_226896_b_()), "minecraft:slime_block", ItemGroup.DECORATIONS);
		HONEY_BLOCK 		= registerBlock(new NewHoneyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).func_226897_b_(0.4F).func_226898_c_(0.5F).func_226896_b_().sound(SoundType.field_226947_m_)), "minecraft:honey_block", ItemGroup.DECORATIONS);
		CLOVER_HONEY_BLOCK 	= registerBlock(new NewCloverHoneyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).slipperiness(0.75F).func_226897_b_(0.0F).slipperiness(0.75F).func_226898_c_(0.25F).func_226896_b_().sound(SoundType.field_226947_m_)), "clover_honey_block", ItemGroup.DECORATIONS);

		//TODO: Decide either it going to stay or not
		//Cut Content Section
		//CRYSTALLIZED_HONEY_BLOCK        = registerBlock(new Block(Block.Properties.create(Material.GLASS).func_226896_b_().hardnessAndResistance(0.3F).sound(SoundType.GLASS)), "crystallized_honey_block", ItemGroup.DECORATIONS);
		//CRYSTALLIZED_CLOVER_HONEY_BLOCK = registerBlock(new Block(Block.Properties.from(CRYSTALLIZED_HONEY_BLOCK)),"crystallized_clover_honey_block", ItemGroup.DECORATIONS);

		//Hive Planks Section
		HIVE_PLANKS 			= registerBlock(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hive_planks", ItemGroup.BUILDING_BLOCKS);
		HIVE_STAIRS 			= registerBlock(new StairsBlock(HIVE_PLANKS.getDefaultState(), Block.Properties.from(HIVE_PLANKS)), "hive_stairs", ItemGroup.BUILDING_BLOCKS);
		HIVE_SLAB 			= registerBlock(new SlabBlock(Block.Properties.from(HIVE_PLANKS)), "hive_slab", ItemGroup.BUILDING_BLOCKS);
        HIVE_FENCE 			= registerBlock(new FenceBlock(Block.Properties.from(HIVE_PLANKS)), "hive_fence", ItemGroup.DECORATIONS);
        HIVE_FENCE_GATE 		= registerBlock(new FenceGateBlock(Block.Properties.from(HIVE_PLANKS)), "hive_fence_gate", ItemGroup.REDSTONE);
        HIVE_BUTTON 			= registerBlock(new WoodButtonBlock(Block.Properties.from(HIVE_PLANKS).doesNotBlockMovement()), "hive_button", ItemGroup.REDSTONE);
		HIVE_PRESSURE_PLATE 	= registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HIVE_PLANKS).doesNotBlockMovement()), "hive_pressure_plate", ItemGroup.REDSTONE);
		HIVE_DOOR 			= registerBlock(new DoorBlock(Block.Properties.from(HIVE_PLANKS).func_226896_b_()), "hive_door", ItemGroup.REDSTONE);
		HIVE_TRAPDOOR 		= registerBlock(new TrapDoorBlock(Block.Properties.from(HIVE_PLANKS).func_226896_b_()), "hive_trapdoor", ItemGroup.REDSTONE);
		//HIVE_SIGN 			= registerBlockNoGroupNoItem(new StandingSignBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), ModWoodType.HIVE_TYPE), "hive_sign");
		//HIVE_WALL_SIGN 		= registerBlockNoGroupNoItem(new WallSignBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(HIVE_SIGN), ModWoodType.HIVE_TYPE),"hive_wall_sign");
		
		//Honey Bricks Section
		HONEY_BRICKS        = registerBlock(new Block(Block.Properties.from(Blocks.BRICKS)),"honey_bricks", ItemGroup.BUILDING_BLOCKS);
		HONEY_BRICK_STAIRS 	= registerBlock(new StairsBlock(HONEY_BRICKS.getDefaultState(), Block.Properties.from(Blocks.BRICK_STAIRS)),"honey_brick_stairs", ItemGroup.BUILDING_BLOCKS);
		HONEY_BRICK_SLAB 	= registerBlock(new SlabBlock(Block.Properties.from(Blocks.BRICK_SLAB)),"honey_brick_slab", ItemGroup.BUILDING_BLOCKS);
		HONEY_BRICK_WALL 	= registerBlock(new WallBlock(Block.Properties.from(Blocks.BRICK_WALL)),"honey_brick_wall", ItemGroup.DECORATIONS);

		//Flowers Section
		CARTWHEEL 			= registerBlock(new FlowerBlock(Effects.SPEED, 11, FLOWER_PROPERTIES), "cartwheel", ItemGroup.DECORATIONS);
		BLUEBELL 			= registerBlock(new FlowerBlock(Effects.WATER_BREATHING, 6, FLOWER_PROPERTIES),	"bluebell", ItemGroup.DECORATIONS);
		DAYBLOOM 			= registerBlock(new FlowerBlock(Effects.GLOWING, 8, FLOWER_PROPERTIES), "daybloom", ItemGroup.DECORATIONS);
		VIOLET 				= registerBlock(new FlowerBlock(Effects.INVISIBILITY, 6, FLOWER_PROPERTIES), "violet", ItemGroup.DECORATIONS);
		JOLYCE 				= registerBlock(new FlowerBlock(Effects.STRENGTH, 8, FLOWER_PROPERTIES), "jolyce", ItemGroup.DECORATIONS);
		COLUMBINE 			= registerBlock(new FlowerBlock(Effects.MINING_FATIGUE, 6, FLOWER_PROPERTIES), "columbine", ItemGroup.DECORATIONS);
		WHITE_CLOVER 		= registerBlock(new CloverBlock(Effects.UNLUCK, FLOWER_PROPERTIES.func_226896_b_()), "white_clover", ItemGroup.DECORATIONS);
		PINK_CLOVER 		= registerBlock(new CloverBlock(Effects.UNLUCK, FLOWER_PROPERTIES.func_226896_b_()), "pink_clover", ItemGroup.DECORATIONS);
		BIRD_OF_PARADISE 	= registerBlock(new TallFlowerBlock(FLOWER_PROPERTIES), "bird_of_paradise", ItemGroup.DECORATIONS);
		
		//Potted Flowers Section
	    POTTED_CARTWHEEL 	= registerBlockNoItem(new PottedCartwheelBlock(POT_PROPERTIES), "potted_cartwheel");
	    POTTED_BLUEBELL 	= registerBlockNoItem(new FlowerPotBlock(BLUEBELL, POT_PROPERTIES), "potted_bluebell");
	    POTTED_DAYBLOOM 	= registerBlockNoItem(new FlowerPotBlock(DAYBLOOM, POT_PROPERTIES), "potted_daybloom");
	    POTTED_VIOLET 		= registerBlockNoItem(new FlowerPotBlock(VIOLET, POT_PROPERTIES), "potted_violet");
	    POTTED_JOLYCE 		= registerBlockNoItem(new FlowerPotBlock(JOLYCE, POT_PROPERTIES), "potted_jolyce");
	    POTTED_COLUMBINE 	= registerBlockNoItem(new FlowerPotBlock(COLUMBINE, POT_PROPERTIES), "potted_columbine");
	    POTTED_WHITE_CLOVER = registerBlockNoItem(new FlowerPotBlock(WHITE_CLOVER, POT_PROPERTIES), "potted_white_clover");
	    POTTED_PINK_CLOVER 	= registerBlockNoItem(new FlowerPotBlock(PINK_CLOVER, POT_PROPERTIES), "potted_pink_clover");

		//Vanilla Candles Section
		CANDLE 				= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "candle", ItemGroup.DECORATIONS);
		WHITE_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "white_candle", ItemGroup.DECORATIONS);
		ORANGE_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "orange_candle", ItemGroup.DECORATIONS);
		MAGENTA_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "magenta_candle", ItemGroup.DECORATIONS);
		LIGHT_BLUE_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "light_blue_candle", ItemGroup.DECORATIONS);
		YELLOW_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "yellow_candle", ItemGroup.DECORATIONS);
		LIME_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "lime_candle", ItemGroup.DECORATIONS);
		PINK_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "pink_candle", ItemGroup.DECORATIONS);
		GRAY_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "gray_candle", ItemGroup.DECORATIONS);
		LIGHT_GRAY_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "light_gray_candle", ItemGroup.DECORATIONS);
		CYAN_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "cyan_candle", ItemGroup.DECORATIONS);
		PURPLE_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "purple_candle", ItemGroup.DECORATIONS);
		BLUE_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "blue_candle", ItemGroup.DECORATIONS);
		BROWN_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "brown_candle", ItemGroup.DECORATIONS);
		GREEN_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "green_candle", ItemGroup.DECORATIONS);
		RED_CANDLE 			= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "red_candle", ItemGroup.DECORATIONS);
		BLACK_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "black_candle", ItemGroup.DECORATIONS);
		
		//Vanilla Scented Candles Section
		ALLIUM_SCENTED_CANDLE             = registerBlock(new ScentedCandleBlock(Effects.FIRE_RESISTANCE, 70, 0, CANDLE_PROPERTIES), "allium_scented_candle",             ItemGroup.DECORATIONS);
		AZURE_BLUET_SCENTED_CANDLE        = registerBlock(new ScentedCandleBlock(Effects.BLINDNESS,       70, 0, CANDLE_PROPERTIES), "azure_bluet_scented_candle",        ItemGroup.DECORATIONS);
		BLUE_ORCHID_SCENTED_CANDLE        = registerBlock(new ScentedCandleBlock(Effects.SATURATION,      70, 0, CANDLE_PROPERTIES), "blue_orchid_scented_candle",        ItemGroup.DECORATIONS);
		DANDELION_SCENTED_CANDLE          = registerBlock(new ScentedCandleBlock(Effects.SATURATION,      70, 0, CANDLE_PROPERTIES), "dandelion_scented_candle",          ItemGroup.DECORATIONS);
		CORNFLOWER_SCENTED_CANDLE         = registerBlock(new ScentedCandleBlock(Effects.JUMP_BOOST,      70, 0, CANDLE_PROPERTIES), "cornflower_scented_candle",         ItemGroup.DECORATIONS);
		LILY_OF_THE_VALLEY_SCENTED_CANDLE = registerBlock(new ScentedCandleBlock(Effects.POISON,          70, 0, CANDLE_PROPERTIES), "lily_of_the_valley_scented_candle", ItemGroup.DECORATIONS);
		OXEYE_DAISY_SCENTED_CANDLE        = registerBlock(new ScentedCandleBlock(Effects.REGENERATION,    70, 0, CANDLE_PROPERTIES), "oxeye_daisy_scented_candle",        ItemGroup.DECORATIONS);
		POPPY_SCENTED_CANDLE              = registerBlock(new ScentedCandleBlock(Effects.NIGHT_VISION,    70, 0, CANDLE_PROPERTIES), "poppy_scented_candle",              ItemGroup.DECORATIONS);
		WHITE_TULIP_SCENTED_CANDLE        = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, CANDLE_PROPERTIES), "white_tulip_scented_candle",        ItemGroup.DECORATIONS);
		ORANGE_TULIP_SCENTED_CANDLE       = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, CANDLE_PROPERTIES), "orange_tulip_scented_candle",       ItemGroup.DECORATIONS);
		PINK_TULIP_SCENTED_CANDLE         = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, CANDLE_PROPERTIES), "pink_tulip_scented_candle",         ItemGroup.DECORATIONS);
		RED_TULIP_SCENTED_CANDLE          = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, CANDLE_PROPERTIES), "red_tulip_scented_candle",          ItemGroup.DECORATIONS);
		WITHER_ROSE_SCENTED_CANDLE        = registerBlock(new ScentedCandleBlock(Effects.WITHER,          70, 0, CANDLE_PROPERTIES), "wither_rose_scented_candle",        ItemGroup.DECORATIONS);
		
		//Mod Scented Candles Section
		CARTWHEEL_SCENTED_CANDLE    = registerBlock(new ScentedCandleBlock(Effects.SPEED,           70, 0, CANDLE_PROPERTIES), "cartwheel_scented_candle",    ItemGroup.DECORATIONS);
		BLUEBELL_SCENTED_CANDLE     = registerBlock(new ScentedCandleBlock(Effects.WATER_BREATHING, 70, 0, CANDLE_PROPERTIES), "bluebell_scented_candle",     ItemGroup.DECORATIONS);
		DAYBLOOM_SCENTED_CANDLE     = registerBlock(new ScentedCandleBlock(Effects.GLOWING,         70, 0, CANDLE_PROPERTIES), "daybloom_scented_candle",     ItemGroup.DECORATIONS);
		VIOLET_SCENTED_CANDLE       = registerBlock(new ScentedCandleBlock(Effects.INVISIBILITY,    70, 0, CANDLE_PROPERTIES), "violet_scented_candle",       ItemGroup.DECORATIONS);
		JOLYCE_SCENTED_CANDLE       = registerBlock(new ScentedCandleBlock(Effects.STRENGTH,        70, 0, CANDLE_PROPERTIES), "jolyce_scented_candle",       ItemGroup.DECORATIONS);
		COLUMBINE_SCENTED_CANDLE    = registerBlock(new ScentedCandleBlock(Effects.MINING_FATIGUE,  70, 0, CANDLE_PROPERTIES), "columbine_scented_candle",    ItemGroup.DECORATIONS);
		WHITE_CLOVER_SCENTED_CANDLE = registerBlock(new ScentedCandleBlock(Effects.UNLUCK,          70, 0, CANDLE_PROPERTIES), "white_clover_scented_candle", ItemGroup.DECORATIONS);
		PINK_CLOVER_SCENTED_CANDLE  = registerBlock(new ScentedCandleBlock(Effects.UNLUCK,          70, 0, CANDLE_PROPERTIES), "pink_clover_scented_candle",  ItemGroup.DECORATIONS);

		//Quark Compat Section
		if (ModList.get().isLoaded("quark")) {
			VERTICAL_HIVE_PLANKS 		= registerBlock(new Block(Block.Properties.from(HIVE_PLANKS)),              "vertical_hive_planks",      ItemGroup.BUILDING_BLOCKS);
			HIVE_VERTICAL_SLAB 			= registerBlock(new VerticalSlabBlock(Block.Properties.from(HIVE_PLANKS)),  "hive_vertical_slab",        ItemGroup.BUILDING_BLOCKS);
			HONEY_BRICK_VERTICAL_SLAB 	= registerBlock(new VerticalSlabBlock(Block.Properties.from(HONEY_BRICKS)), "honey_brick_vertical_slab", ItemGroup.BUILDING_BLOCKS);
		}
		
		//Flamboyant Compat Section
		if (ModList.get().isLoaded("flamboyant")) {
			AMBER_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "amber_candle", 		ItemGroup.DECORATIONS);
			BEIGE_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "beige_candle", 		ItemGroup.DECORATIONS);
			CREAM_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "cream_candle", 		ItemGroup.DECORATIONS);
			DARK_GREEN_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "dark_green_candle", 	ItemGroup.DECORATIONS);
			FOREST_GREEN_CANDLE	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "forest_green_candle", 	ItemGroup.DECORATIONS);
			HOT_PINK_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "hot_pink_candle", 		ItemGroup.DECORATIONS);
			INDIGO_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "indigo_candle", 		ItemGroup.DECORATIONS);
			MAROON_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "maroon_candle", 		ItemGroup.DECORATIONS);
			NAVY_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "navy_candle", 			ItemGroup.DECORATIONS);
			OLIVE_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "olive_candle", 		ItemGroup.DECORATIONS);
			PALE_GREEN_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "pale_green_candle", 	ItemGroup.DECORATIONS);
			PALE_PINK_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "pale_pink_candle", 	ItemGroup.DECORATIONS);
			PALE_YELLOW_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "pale_yellow_candle", 	ItemGroup.DECORATIONS);
			SKY_BLUE_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "sky_blue_candle", 		ItemGroup.DECORATIONS);
			SLATE_GRAY_CANDLE 	= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "slate_gray_candle", 	ItemGroup.DECORATIONS);
			VIOLET_CANDLE 		= registerBlock(new CandleBlock(CANDLE_PROPERTIES), "violet_candle", 		ItemGroup.DECORATIONS);
		}
    }

	/*@SubscribeEvent
	public static void registerBlocksReplacements(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				new NewSlimeBlock(Block.Properties.create(Material.CLAY, MaterialColor.GRASS).slipperiness(0.8F).sound(SoundType.SLIME).func_226896_b_()).setRegistryName("minecraft:slime_block"),
				new NewHoneyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).func_226897_b_(0.4F).func_226898_c_(0.5F).func_226896_b_().sound(SoundType.field_226947_m_)).setRegistryName("minecraft:honey_block")
		);
	}

	@SubscribeEvent
	public static void registerItemsReplacements(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new BlockItem(ModBlocks.SLIME_BLOCK, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("minecraft:slime_block"),
				new BlockItem(ModBlocks.HONEY_BLOCK, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("minecraft:honey_block")
		);
	}*/

    public static Block registerBlock(Block block, String name, ItemGroup group)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(group));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
    
    public static Block registerBlockNoItem(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    public static Block registerBlockNoGroupNoItem(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
    
    /*public static Block registerBlock(Block block, BlockItem itemBlock, String name) {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);

        if (itemBlock != null) {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
        }

        return block;
    }*/
}