package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.util.DataUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BBBlockData {
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.65F, BBBlocks.CARTWHEEL.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.VIOLET.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.DIANTHUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.COLUMBINE.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.PINK_CLOVER.get());		
		DataUtils.registerCompostable(0.65F, BBBlocks.WHITE_CLOVER.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.BLUEBELL.get());		
		
		DataUtils.registerCompostable(0.65F, BBBlocks.YELLOW_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.ORANGE_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.RED_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.PINK_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.MAGENTA_HIBISCUS.get());
		DataUtils.registerCompostable(0.65F, BBBlocks.PURPLE_HIBISCUS.get());

		DataUtils.registerCompostable(0.65F, BBBlocks.BIRD_OF_PARADISE.get());
	}
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(BBBlocks.HIVE_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.VERTICAL_HIVE_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(BBBlocks.HIVE_BOOKSHELF.get(), 5, 20);
	}
	
	public static void setupRenderLayer()
	{
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_LAMP.get(),RenderType.getTranslucent());

		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_DOOR.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_TRAPDOOR.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HIVE_LADDER.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.WHITE_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.CARTWHEEL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.VIOLET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.COLUMBINE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.DIANTHUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BLUEBELL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.YELLOW_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.ORANGE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.RED_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.MAGENTA_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PURPLE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BIRD_OF_PARADISE.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_CARTWHEEL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_VIOLET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_COLUMBINE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_DIANTHUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BLUEBELL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_YELLOW_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_ORANGE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_RED_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_MAGENTA_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PURPLE_HIBISCUS.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BIRD_OF_PARADISE.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHEAT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_CARROT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_POTATO.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BEETROOT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_SWEET_BERRIES.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_SUGAR_CANE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_NETHER_WART.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_CHORUS_PLANT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PUMPKIN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_MELON.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_ROSE_BUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_SUNFLOWER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_LILAC.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PEONY.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_GRASS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_TALL_GRASS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_LARGE_FERN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_CARVED_PUMPKIN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_JACK_O_LANTERN.get(),RenderType.getCutout());
		//RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_COCOA_BEANS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_SEA_PICKLE.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_SWEET_BERRY_PIPS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BLUE_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PURPLE_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_OVERWORLD_CORROCK_CROWN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_NETHER_CORROCK_CROWN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_END_CORROCK_CROWN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_TALL_POISE_BUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_OVERWORLD_CORROCK.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_NETHER_CORROCK.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_END_CORROCK.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_FLOWER_POT.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WHITE_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CARTWHEEL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_VIOLET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_COLUMBINE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_DIANTHUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BLUEBELL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_YELLOW_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ORANGE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_RED_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_MAGENTA_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PURPLE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BIRD_OF_PARADISE.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WHITE_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_CLOVER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CARTWHEEL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_VIOLET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_COLUMBINE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_DIANTHUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BLUEBELL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_YELLOW_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ORANGE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_RED_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_MAGENTA_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PURPLE_HIBISCUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BIRD_OF_PARADISE.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ACACIA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ALLIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_AZURE_BLUET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BAMBOO.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BIRCH_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BROWN_MUSHROOM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BLUE_ORCHID.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CACTUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CORNFLOWER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_DANDELION.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_DARK_OAK_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_DEAD_BUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_FERN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_JUNGLE_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_LILY_OF_THE_VALLEY.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_OAK_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ORANGE_TULIP.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_OXEYE_DAISY.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_TULIP.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_POPPY.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_RED_MUSHROOM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_RED_TULIP.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_SPRUCE_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WHITE_TULIP.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WITHER_ROSE.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WHEAT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CARROT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_POTATO.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BEETROOT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_SWEET_BERRIES.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_SUGAR_CANE.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_NETHER_WART.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CHORUS_PLANT.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PUMPKIN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_MELON.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ROSE_BUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_SUNFLOWER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_LILAC.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PEONY.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_GRASS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_TALL_GRASS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_LARGE_FERN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CARVED_PUMPKIN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_JACK_O_LANTERN.get(),RenderType.getCutout());
		//RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_COCOA_BEANS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_SEA_PICKLE.get(),RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WHITE_WISTERIA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BLUE_WISTERIA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_WISTERIA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PURPLE_WISTERIA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_LAVENDER_BLOSSOM_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ORANGE_BLOSSOM_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_BLOSSOM_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BLUE_BLOSSOM_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_YELLOW_BLOSSOM_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ROSEWOOD_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_YUCCA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_KOUSA_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ASPEN_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_POISE_BUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BLUE_PICKERELWEED.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PURPLE_PICKERELWEED.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WHITE_SEAROCKET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_SEAROCKET.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_GLOWSHROOM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WARM_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_HOT_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_SCALDING_MONKEY_BRUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_GILIA.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_YUCCA_FLOWER.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BARREL_CACTUS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_CATTAIL.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_MAPLE_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_YELLOW_MAPLE_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_ORANGE_MAPLE_SAPLING.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_RED_MAPLE_SAPLING.get(),RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_SWEET_BERRY_PIPS.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BAMBOO_TORCH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_WHITE_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_BLUE_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PINK_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_PURPLE_DELPHINIUM.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_OVERWORLD_CORROCK_CROWN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_NETHER_CORROCK_CROWN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_END_CORROCK_CROWN.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_TALL_POISE_BUSH.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_OVERWORLD_CORROCK.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_NETHER_CORROCK.get(),RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.HANGING_POTTED_END_CORROCK.get(),RenderType.getCutout());
	}
}

