package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.buzzier_bees.common.dispenser.BeeBottleDispenseBehavior;
import com.teamabnormals.buzzier_bees.common.dispenser.BugBottleDispenseBehavior;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;

public class BBCompat {

	public static final String ATMOSPHERIC = "atmospheric";
	public static final String AUTUMNITY = "autumnity";
	public static final String ENVIRONMENTAL = "environmental";
	public static final String UPGRADE_AQUATIC = "upgrade_aquatic";
	public static final String ENDERGETIC = "endergetic";
	public static final String CAVERNS_AND_CHASMS = "caverns_and_chasms";

	public static final ResourceLocation RELIEF = new ResourceLocation(ATMOSPHERIC, "relief");
	public static final ResourceLocation WORSENING = new ResourceLocation(ATMOSPHERIC, "worsening");
	public static final ResourceLocation PERSISTENCE = new ResourceLocation(ATMOSPHERIC, "persistence");
	public static final ResourceLocation FOUL_TASTE = new ResourceLocation(AUTUMNITY, "foul_taste");
	public static final ResourceLocation ENDER_FLAME = new ResourceLocation(ENDERGETIC, "ender_flame");
	public static final ResourceLocation CURSED_FLAME = new ResourceLocation(CAVERNS_AND_CHASMS, "cursed_flame");

	public static void registerCompat() {
		registerCompostables();
		registerFlammables();
		registerDispenserBehaviors();
		changeLocalizationKeys();
	}

	public static void changeLocalizationKeys() {
		DataUtil.changeBlockLocalization(Blocks.BEEHIVE, BuzzierBees.MOD_ID, "oak_beehive");
	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(BBBlocks.PINK_CLOVER.get(), 0.65F);
		DataUtil.registerCompostable(BBBlocks.WHITE_CLOVER.get(), 0.65F);;
		DataUtil.registerCompostable(BBBlocks.BUTTERCUP.get(), 0.65F);
		DataUtil.registerCompostable(BBItems.FOUR_LEAF_CLOVER.get(), 0.65F);
		DataUtil.registerCompostable(BBItems.HONEY_BREAD.get(), 0.65F);
		DataUtil.registerCompostable(BBItems.HONEY_APPLE.get(), 0.85F);
	}

	public static void registerFlammables() {
		DataUtil.registerFlammable(BBBlocks.SPRUCE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BIRCH_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.JUNGLE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.DARK_OAK_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.ACACIA_BEEHIVE.get(), 5, 20);
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
		DispenserBlock.registerBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
	}

	public static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.HONEY_LAMP.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.HONEY_POT.get(), RenderType.translucent());

		ItemBlockRenderTypes.setRenderLayer(BBBlocks.WHITE_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.PINK_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.BUTTERCUP.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BBBlocks.POTTED_BUTTERCUP.get(), RenderType.cutout());
	}
}
