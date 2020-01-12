package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.bagel.buzzierbees.common.blocks.CartwheelBlock;
import com.bagel.buzzierbees.common.blocks.HoneyLamp;
import com.bagel.buzzierbees.common.blocks.PottedCartwheelBlock;
import com.bagel.buzzierbees.common.blocks.ScentedCandleBlock;
import com.bagel.buzzierbees.common.blocks.VerticalSlabBlock;
import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
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
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, BuzzierBees.MODID);
	
	public static final RegistryObject<Block> WAX_BLOCK = RegistryUtils.createBlock("wax_block", () -> new Block(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.3F).sound(SoundType.CORAL)), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> SPRUCE_BEEHIVE   = RegistryUtils.createBlock("spruce_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BIRCH_BEEHIVE    = RegistryUtils.createBlock("birch_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> JUNGLE_BEEHIVE   = RegistryUtils.createBlock("jungle_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ACACIA_BEEHIVE   = RegistryUtils.createBlock("acacia_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DARK_OAK_BEEHIVE = RegistryUtils.createBlock("dark_oak_beehive", () -> new BeehiveBlock(Block.Properties.from(Blocks.field_226906_mb_)), ItemGroup.DECORATIONS);
	
	//public static final RegistryObject<Block> SLIME_BLOCK         = ModUtils.createBlock("minecraft:slime_block", () -> new NewSlimeBlock(Block.Properties.create(Material.CLAY, MaterialColor.GRASS).slipperiness(0.8F).sound(SoundType.SLIME).func_226896_b_()), null);
	//public static final RegistryObject<Block> HONEY_BLOCK         = ModUtils.createBlock("minecraft:honey_block", () -> new NewHoneyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).func_226897_b_(0.4F).func_226898_c_(0.5F).func_226896_b_().sound(SoundType.field_226947_m_)), ItemGroup.DECORATIONS);
	//public static final RegistryObject<Block> CLOVER_HONEY_BLOCK 	= ModUtils.createBlock("clover_honey_block", () -> new NewCloverHoneyBlock(Block.Properties.create(Material.CLAY, MaterialColor.ADOBE).slipperiness(0.75F).func_226897_b_(0.0F).slipperiness(0.75F).func_226898_c_(0.25F).func_226896_b_().sound(SoundType.field_226947_m_), ItemGroup.DECORATIONS);
	
	//public static final RegistryObject<Block> CRYSTALLIZED_HONEY_BLOCK        = ModUtils.createBlock("crystallized_honey_block", () -> new Block(Block.Properties.create(Material.GLASS).func_226896_b_().hardnessAndResistance(0.3F).sound(SoundType.GLASS)), null);
	//public static final RegistryObject<Block> CRYSTALLIZED_CLOVER_HONEY_BLOCK = ModUtils.createBlock("crystallized_clover_honey_block", () -> new Block(Block.Properties.from(CRYSTALLIZED_HONEY_BLOCK.get())), null);
	
	public static final RegistryObject<Block> HIVE_PLANKS          = RegistryUtils.createBlock("hive_planks", () -> new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), ItemGroup.BUILDING_BLOCKS); 
	public static final RegistryObject<Block> VERTICAL_HIVE_PLANKS = RegistryUtils.createBlockCompat("quark", "vertical_hive_planks", () -> new Block(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_STAIRS          = RegistryUtils.createBlock("hive_stairs", () -> new StairsBlock(HIVE_PLANKS.get().getDefaultState(), Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_SLAB            = RegistryUtils.createBlock("hive_slab", () -> new SlabBlock(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_VERTICAL_SLAB   = RegistryUtils.createBlockCompat("quark", "hive_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HIVE_FENCE           = RegistryUtils.createBlock("hive_fence", () -> new FenceBlock(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HIVE_PRESSURE_PLATE  = RegistryUtils.createBlock("hive_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HIVE_PLANKS.get()).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_TRAPDOOR        = RegistryUtils.createBlock("hive_trapdoor", () -> new TrapDoorBlock(Block.Properties.from(HIVE_PLANKS.get()).func_226896_b_()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_FENCE_GATE      = RegistryUtils.createBlock("hive_fence_gate", () -> new FenceGateBlock(Block.Properties.from(HIVE_PLANKS.get())), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_BUTTON          = RegistryUtils.createBlock("hive_button", () -> new WoodButtonBlock(Block.Properties.from(HIVE_PLANKS.get()).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> HIVE_DOOR            = RegistryUtils.createBlock("hive_door", () -> new DoorBlock(Block.Properties.from(HIVE_PLANKS.get()).func_226896_b_()), ItemGroup.REDSTONE);
	//public static final RegistryObject<Block> HIVE_SIGN            = ModUtils.createBlock("hive_sign", () -> new StandingSignBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), ModWoodType.HIVE_TYPE), null);
	//public static final RegistryObject<Block> HIVE_WALL_SIGN       = ModUtils.createBlock("hive_wall_sign", () -> new WallSignBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(HIVE_SIGN.get()), ModWoodType.HIVE_TYPE), null);
	
	public static final RegistryObject<Block> HONEY_BRICKS              = RegistryUtils.createBlock("honey_bricks", () -> new Block(Block.Properties.from(Blocks.BRICKS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEY_BRICK_STAIRS 	    = RegistryUtils.createBlock("honey_brick_stairs", () -> new StairsBlock(HONEY_BRICKS.get().getDefaultState(), Block.Properties.from(Blocks.BRICK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEY_BRICK_SLAB 	        = RegistryUtils.createBlock("honey_brick_slab", () -> new SlabBlock(Block.Properties.from(Blocks.BRICK_SLAB)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEY_BRICK_VERTICAL_SLAB = RegistryUtils.createBlockCompat("quark", "honey_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(HONEY_BRICKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEY_BRICK_WALL 	        = RegistryUtils.createBlock("honey_brick_wall", () -> new WallBlock(Block.Properties.from(Blocks.BRICK_WALL)), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> HONEY_LAMP = RegistryUtils.createBlock("honey_lamp", () -> new HoneyLamp(Block.Properties.from(Blocks.END_ROD).sound(SoundType.field_226947_m_)), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> CARTWHEEL 		= RegistryUtils.createBlock("cartwheel", () -> new CartwheelBlock(Effects.SPEED, 11, RegistryUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUEBELL 			= RegistryUtils.createBlock("bluebell", () -> new FlowerBlock(Effects.WATER_BREATHING, 6, RegistryUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DAYBLOOM 			= RegistryUtils.createBlock("daybloom", () -> new FlowerBlock(Effects.GLOWING, 8, RegistryUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VIOLET 			= RegistryUtils.createBlock("violet", () -> new FlowerBlock(Effects.INVISIBILITY, 6, RegistryUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> JOLYCE 			= RegistryUtils.createBlock("jolyce", () -> new FlowerBlock(Effects.STRENGTH, 8, RegistryUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> COLUMBINE 		= RegistryUtils.createBlock("columbine", () -> new FlowerBlock(Effects.MINING_FATIGUE, 6, RegistryUtils.FLOWER), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_CLOVER 		= RegistryUtils.createBlock("white_clover", () -> new FlowerBlock(Effects.UNLUCK, 30, RegistryUtils.FLOWER.func_226896_b_()), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER 		= RegistryUtils.createBlock("pink_clover", () -> new FlowerBlock(Effects.UNLUCK, 60, RegistryUtils.FLOWER.func_226896_b_()), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BIRD_OF_PARADISE 	= RegistryUtils.createBlock("bird_of_paradise", () -> new TallFlowerBlock(RegistryUtils.FLOWER), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> POTTED_CARTWHEEL 	  = RegistryUtils.createBlockNoItem("potted_cartwheel", () -> new PottedCartwheelBlock(CARTWHEEL.get(), RegistryUtils.POT));
	public static final RegistryObject<Block> POTTED_BLUEBELL     = RegistryUtils.createBlockNoItem("potted_bluebell", () -> new FlowerPotBlock(BLUEBELL.get(), RegistryUtils.POT));
	public static final RegistryObject<Block> POTTED_DAYBLOOM     = RegistryUtils.createBlockNoItem("potted_daybloom", () -> new FlowerPotBlock(DAYBLOOM.get(), RegistryUtils.POT));
	public static final RegistryObject<Block> POTTED_VIOLET 	  = RegistryUtils.createBlockNoItem("potted_violet", () -> new FlowerPotBlock(VIOLET.get(), RegistryUtils.POT));
	public static final RegistryObject<Block> POTTED_JOLYCE 	  = RegistryUtils.createBlockNoItem("potted_jolyce", () -> new FlowerPotBlock(JOLYCE.get(), RegistryUtils.POT));
	public static final RegistryObject<Block> POTTED_COLUMBINE 	  = RegistryUtils.createBlockNoItem("potted_columbine", () -> new FlowerPotBlock(COLUMBINE.get(), RegistryUtils.POT));
	public static final RegistryObject<Block> POTTED_WHITE_CLOVER = RegistryUtils.createBlockNoItem("potted_white_clover", () -> new FlowerPotBlock(WHITE_CLOVER.get(), RegistryUtils.POT));
	public static final RegistryObject<Block> POTTED_PINK_CLOVER  = RegistryUtils.createBlockNoItem("potted_pink_clover", () -> new FlowerPotBlock(PINK_CLOVER.get(), RegistryUtils.POT));
	
	public static final RegistryObject<Block> CANDLE 			= RegistryUtils.createBlock("candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_CANDLE 		= RegistryUtils.createBlock("white_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_CANDLE 	= RegistryUtils.createBlock("orange_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAGENTA_CANDLE 	= RegistryUtils.createBlock("magenta_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_BLUE_CANDLE = RegistryUtils.createBlock("light_blue_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_CANDLE 	= RegistryUtils.createBlock("yellow_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIME_CANDLE 		= RegistryUtils.createBlock("lime_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CANDLE 		= RegistryUtils.createBlock("pink_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GRAY_CANDLE 		= RegistryUtils.createBlock("gray_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_GRAY_CANDLE = RegistryUtils.createBlock("light_gray_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CYAN_CANDLE 		= RegistryUtils.createBlock("cyan_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_CANDLE 	= RegistryUtils.createBlock("purple_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_CANDLE 		= RegistryUtils.createBlock("blue_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BROWN_CANDLE 		= RegistryUtils.createBlock("brown_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GREEN_CANDLE 		= RegistryUtils.createBlock("green_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_CANDLE 		= RegistryUtils.createBlock("red_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLACK_CANDLE 		= RegistryUtils.createBlock("black_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> AMBER_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "amber_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BEIGE_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "beige_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CREAM_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "cream_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DARK_GREEN_CANDLE   = RegistryUtils.createBlockCompat("flamboyant", "dark_green_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> FOREST_GREEN_CANDLE = RegistryUtils.createBlockCompat("flamboyant", "forest_green_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HOT_PINK_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "hot_pink_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> INDIGO_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "indigo_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAROON_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "maroon_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> NAVY_CANDLE 	      = RegistryUtils.createBlockCompat("flamboyant", "navy_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> OLIVE_CANDLE 		  = RegistryUtils.createBlockCompat("flamboyant", "olive_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PALE_GREEN_CANDLE   = RegistryUtils.createBlockCompat("flamboyant", "pale_green_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PALE_PINK_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "pale_pink_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PALE_YELLOW_CANDLE  = RegistryUtils.createBlockCompat("flamboyant", "pale_yellow_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SKY_BLUE_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "sky_blue_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SLATE_GRAY_CANDLE   = RegistryUtils.createBlockCompat("flamboyant", "slate_gray_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VIOLET_CANDLE 	  = RegistryUtils.createBlockCompat("flamboyant", "violet_candle", () -> new CandleBlock(RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> ALLIUM_SCENTED_CANDLE             = RegistryUtils.createBlock("allium_scented_candle", () -> new ScentedCandleBlock(Effects.FIRE_RESISTANCE, 70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> AZURE_BLUET_SCENTED_CANDLE        = RegistryUtils.createBlock("azure_bluet_scented_candle", () -> new ScentedCandleBlock(Effects.BLINDNESS,       70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_ORCHID_SCENTED_CANDLE        = RegistryUtils.createBlock("blue_orchid_scented_candle", () -> new ScentedCandleBlock(Effects.SATURATION,      70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DANDELION_SCENTED_CANDLE          = RegistryUtils.createBlock("dandelion_scented_candle", () -> new ScentedCandleBlock(Effects.SATURATION,      70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CORNFLOWER_SCENTED_CANDLE         = RegistryUtils.createBlock("cornflower_scented_candle", () -> new ScentedCandleBlock(Effects.JUMP_BOOST,      70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LILY_OF_THE_VALLEY_SCENTED_CANDLE = RegistryUtils.createBlock("lily_of_the_valley_scented_candle", () -> new ScentedCandleBlock(Effects.POISON,          70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> OXEYE_DAISY_SCENTED_CANDLE        = RegistryUtils.createBlock("oxeye_daisy_scented_candle", () -> new ScentedCandleBlock(Effects.REGENERATION,    70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POPPY_SCENTED_CANDLE              = RegistryUtils.createBlock("poppy_scented_candle", () -> new ScentedCandleBlock(Effects.NIGHT_VISION,    70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_TULIP_SCENTED_CANDLE        = RegistryUtils.createBlock("white_tulip_scented_candle", () -> new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_TULIP_SCENTED_CANDLE       = RegistryUtils.createBlock("orange_tulip_scented_candle", () -> new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_TULIP_SCENTED_CANDLE         = RegistryUtils.createBlock("pink_tulip_scented_candle", () -> new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_TULIP_SCENTED_CANDLE          = RegistryUtils.createBlock("red_tulip_scented_candle", () -> new ScentedCandleBlock(Effects.WEAKNESS,        70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WITHER_ROSE_SCENTED_CANDLE        = RegistryUtils.createBlock("wither_rose_scented_candle", () -> new ScentedCandleBlock(Effects.WITHER,          70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> CARTWHEEL_SCENTED_CANDLE    = RegistryUtils.createBlock("cartwheel_scented_candle", () -> new ScentedCandleBlock(Effects.SPEED,           70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUEBELL_SCENTED_CANDLE     = RegistryUtils.createBlock("bluebell_scented_candle", () -> new ScentedCandleBlock(Effects.WATER_BREATHING, 70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DAYBLOOM_SCENTED_CANDLE     = RegistryUtils.createBlock("daybloom_scented_candle", () -> new ScentedCandleBlock(Effects.GLOWING,         70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VIOLET_SCENTED_CANDLE       = RegistryUtils.createBlock("violet_scented_candle", () -> new ScentedCandleBlock(Effects.INVISIBILITY,    70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> JOLYCE_SCENTED_CANDLE       = RegistryUtils.createBlock("jolyce_scented_candle", () -> new ScentedCandleBlock(Effects.STRENGTH,        70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> COLUMBINE_SCENTED_CANDLE    = RegistryUtils.createBlock("columbine_scented_candle", () -> new ScentedCandleBlock(Effects.MINING_FATIGUE,  70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_CLOVER_SCENTED_CANDLE = RegistryUtils.createBlock("white_clover_scented_candle", () -> new ScentedCandleBlock(Effects.UNLUCK,          70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER_SCENTED_CANDLE  = RegistryUtils.createBlock("pink_clover_scented_candle", () -> new ScentedCandleBlock(Effects.UNLUCK,          70, 0, RegistryUtils.CANDLE), ItemGroup.DECORATIONS);

}