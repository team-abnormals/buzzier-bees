package com.bagel.buzzierbees.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
	//candles
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
	
	//flamboyant candles
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
	
	//scented candles
	public static Block ALLIUM_CANDLE;
	public static Block AZURE_BLUET_CANDLE;
	public static Block BLUE_ORCHID_CANDLE;
	public static Block DANDELION_CANDLE;
	public static Block CORNFLOWER_CANDLE;
	public static Block LILY_OF_THE_VALLEY_CANDLE;
	public static Block OXEYE_DAISY_CANDLE;
	public static Block POPPY_CANDLE;
	public static Block WHITE_TULIP_CANDLE;
	public static Block ORANGE_TULIP_CANDLE;
	public static Block PINK_TULIP_CANDLE;
	public static Block RED_TULIP_CANDLE;
	public static Block WITHER_ROSE_CANDLE;
	
	public static Block WAX_BLOCK;
	public static Block CRYSTALLIZED_HONEY_BLOCK;

	public static Block CLOVER;
	public static Block CLOVER_HONEY_BLOCK;
	public static Block CRYSTALLIZED_CLOVER_HONEY_BLOCK;
	
	public static Block HIVE_BLOCK;
	public static Block HIVE_STAIRS;
	public static Block HIVE_SLAB;
	public static Block HIVE_FENCE;
	public static Block HIVE_FENCE_GATE;
	public static Block HIVE_PRESSURE_PLATE;
	public static Block HIVE_BUTTON;
	public static Block HIVE_DOOR;
	public static Block HIVE_TRAPDOOR;
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
		
		WAX_BLOCK = registerBlock(new Block(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.3F).sound(SoundType.CORAL)), "wax_block", ItemGroup.DECORATIONS);
		CRYSTALLIZED_HONEY_BLOCK = registerBlock(new Block(Block.Properties.create(Material.GLASS).func_226896_b_().hardnessAndResistance(0.3F).sound(SoundType.GLASS)), "crystallized_honey_block", ItemGroup.DECORATIONS);
		HIVE_BLOCK = registerBlock(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hive_block", ItemGroup.DECORATIONS);
		HIVE_STAIRS = registerBlock(new StairsBlock(HIVE_BLOCK.getDefaultState(), Block.Properties.from(HIVE_BLOCK)), "hive_stairs", ItemGroup.DECORATIONS);
		HIVE_SLAB = registerBlock(new SlabBlock(Block.Properties.from(HIVE_BLOCK)), "hive_slab", ItemGroup.DECORATIONS);
        //HIVE_FENCE = registerBlock(new FenceBlock(Block.Properties.from(HIVE_BLOCK)), "hive_fence", ItemGroup.DECORATIONS);
        //HIVE_FENCE_GATE = registerBlock(new FenceGateBlock(Block.Properties.from(HIVE_BLOCK)), "hive_fence_gate", ItemGroup.REDSTONE);
        //HIVE_BUTTON = registerBlock(new WoodButtonBlock(Block.Properties.from(HIVE_BLOCK)), "hive_button", ItemGroup.REDSTONE);
		//HIVE_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HIVE_BLOCK)), "hive_pressure_plate", ItemGroup.REDSTONE);
		//HIVE_DOOR = registerBlock(new DoorBlock(Block.Properties.from(HIVE_BLOCK)), "hive_door", ItemGroup.REDSTONE);
        //HIVE_TRAPDOOR = registerBlock(new TrapDoorBlock(Block.Properties.from(HIVE_BLOCK)), "hive_trapdoor", ItemGroup.REDSTONE);
		
		CANDLE = registerBlock(new CandleBlock(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).sound(SoundType.WOOD)), "candle", ItemGroup.DECORATIONS);
		WHITE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "white_candle", ItemGroup.DECORATIONS);
		ORANGE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "orange_candle", ItemGroup.DECORATIONS);
		MAGENTA_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "magenta_candle", ItemGroup.DECORATIONS);
		LIGHT_BLUE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "light_blue_candle", ItemGroup.DECORATIONS);
		YELLOW_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "yellow_candle", ItemGroup.DECORATIONS);
		LIME_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "lime_candle", ItemGroup.DECORATIONS);
		PINK_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "pink_candle", ItemGroup.DECORATIONS);
		GRAY_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "gray_candle", ItemGroup.DECORATIONS);
		LIGHT_GRAY_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "light_gray_candle", ItemGroup.DECORATIONS);
		CYAN_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "cyan_candle", ItemGroup.DECORATIONS);
		PURPLE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "purple_candle", ItemGroup.DECORATIONS);
		BLUE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "blue_candle", ItemGroup.DECORATIONS);
		BROWN_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "brown_candle", ItemGroup.DECORATIONS);
		GREEN_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "green_candle", ItemGroup.DECORATIONS);
		RED_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "red_candle", ItemGroup.DECORATIONS);
		BLACK_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "black_candle", ItemGroup.DECORATIONS);
		
		ALLIUM_CANDLE = registerBlock(new ScentedCandleBlock(Effects.FIRE_RESISTANCE, Block.Properties.from(CANDLE)), "allium_candle", ItemGroup.DECORATIONS);
		AZURE_BLUET_CANDLE = registerBlock(new ScentedCandleBlock(Effects.BLINDNESS, Block.Properties.from(CANDLE)), "azure_bluet_candle", ItemGroup.DECORATIONS);
		BLUE_ORCHID_CANDLE = registerBlock(new ScentedCandleBlock(Effects.SATURATION, Block.Properties.from(CANDLE)), "blue_orchid_candle", ItemGroup.DECORATIONS);
		DANDELION_CANDLE = registerBlock(new ScentedCandleBlock(Effects.SATURATION, Block.Properties.from(CANDLE)), "dandelion_candle", ItemGroup.DECORATIONS);
		CORNFLOWER_CANDLE = registerBlock(new ScentedCandleBlock(Effects.JUMP_BOOST, Block.Properties.from(CANDLE)), "cornflower_candle", ItemGroup.DECORATIONS);
		LILY_OF_THE_VALLEY_CANDLE = registerBlock(new ScentedCandleBlock(Effects.POISON, Block.Properties.from(CANDLE)), "lily_of_the_valley_candle", ItemGroup.DECORATIONS);
		OXEYE_DAISY_CANDLE = registerBlock(new ScentedCandleBlock(Effects.REGENERATION, Block.Properties.from(CANDLE)), "oxeye_daisy_candle", ItemGroup.DECORATIONS);
		POPPY_CANDLE = registerBlock(new ScentedCandleBlock(Effects.NIGHT_VISION, Block.Properties.from(CANDLE)), "poppy_candle", ItemGroup.DECORATIONS);
		WHITE_TULIP_CANDLE = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS, Block.Properties.from(CANDLE)), "white_tulip_candle", ItemGroup.DECORATIONS);
		ORANGE_TULIP_CANDLE = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS, Block.Properties.from(CANDLE)), "orange_tulip_candle", ItemGroup.DECORATIONS);
		PINK_TULIP_CANDLE = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS, Block.Properties.from(CANDLE)), "pink_tulip_candle", ItemGroup.DECORATIONS);
		RED_TULIP_CANDLE = registerBlock(new ScentedCandleBlock(Effects.WEAKNESS, Block.Properties.from(CANDLE)), "red_tulip_candle", ItemGroup.DECORATIONS);
		WITHER_ROSE_CANDLE = registerBlock(new ScentedCandleBlock(Effects.WITHER, Block.Properties.from(CANDLE)), "wither_rose_candle", ItemGroup.DECORATIONS);
		
		if (ModList.get().isLoaded("flamboyant")) {
			AMBER_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "amber_candle", ItemGroup.DECORATIONS);
			BEIGE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "beige_candle", ItemGroup.DECORATIONS);
			CREAM_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "cream_candle", ItemGroup.DECORATIONS);
			DARK_GREEN_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "dark_green_candle", ItemGroup.DECORATIONS);
			FOREST_GREEN_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "forest_green_candle", ItemGroup.DECORATIONS);
			HOT_PINK_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "hot_pink_candle", ItemGroup.DECORATIONS);
			INDIGO_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "indigo_candle", ItemGroup.DECORATIONS);
			MAROON_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "maroon_candle", ItemGroup.DECORATIONS);
			NAVY_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "navy_candle", ItemGroup.DECORATIONS);
			OLIVE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "olive_candle", ItemGroup.DECORATIONS);
			PALE_GREEN_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "pale_green_candle", ItemGroup.DECORATIONS);
			PALE_PINK_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "pale_pink_candle", ItemGroup.DECORATIONS);
			PALE_YELLOW_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "pale_yellow_candle", ItemGroup.DECORATIONS);
			SKY_BLUE_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "sky_blue_candle", ItemGroup.DECORATIONS);
			SLATE_GRAY_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "slate_gray_candle", ItemGroup.DECORATIONS);
			VIOLET_CANDLE = registerBlock(new CandleBlock(Block.Properties.from(CANDLE)), "violet_candle", ItemGroup.DECORATIONS);
		}

		CLOVER = registerBlock(new CloverPatchBlock(Effects.LUCK, Block.Properties.from(Blocks.POPPY).func_226896_b_()), "clover", ItemGroup.DECORATIONS);
		CRYSTALLIZED_CLOVER_HONEY_BLOCK = registerBlock(new Block(Block.Properties.from(CRYSTALLIZED_HONEY_BLOCK)),"crystallized_clover_honey_block", ItemGroup.DECORATIONS);
		CLOVER_HONEY_BLOCK = registerBlock(new Block(Block.Properties.from(Blocks.field_226907_mc_)), "clover_honey_block", ItemGroup.DECORATIONS);
    }

    public static Block registerBlock(Block block, String name, ItemGroup group)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(group));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block registerBlockNoGroup(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(null));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
    
    public static Block registerBlock(Block block, BlockItem itemBlock, String name) {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);

        if (itemBlock != null) {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
        }

        return block;
    }
}