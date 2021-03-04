package com.minecraftabnormals.buzzier_bees.core.other;

import com.minecraftabnormals.abnormals_core.core.registry.LootInjectionRegistry;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.buzzier_bees.common.dispenser.BeeBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.common.dispenser.BugBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;

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
		registerLootInjectors();
		registerCompostables();
		registerFlammables();
		registerDispenserBehaviors();
	}

	public static void registerLootInjectors() {
		LootInjectionRegistry.LootInjector injector = new LootInjectionRegistry.LootInjector(BuzzierBees.MOD_ID);
		injector.addLootInjection(injector.buildLootPool("desert_pyramid", 1, 0), LootTables.CHESTS_DESERT_PYRAMID);
		injector.addLootInjection(injector.buildLootPool("jungle_temple", 1, 0), LootTables.CHESTS_JUNGLE_TEMPLE);
	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(BBBlocks.PINK_CLOVER.get(), 0.65F);
		DataUtil.registerCompostable(BBBlocks.WHITE_CLOVER.get(), 0.65F);
		DataUtil.registerCompostable(BBItems.FOUR_LEAF_CLOVER.get(), 0.65F);
	}

	public static void registerFlammables() {
		DataUtil.registerFlammable(BBBlocks.SPRUCE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.BIRCH_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.JUNGLE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.DARK_OAK_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(BBBlocks.ACACIA_BEEHIVE.get(), 5, 20);
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
	}

	public static void setupRenderLayer() {
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_LAMP.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BBBlocks.HONEY_POT.get(), RenderType.getTranslucent());

		RenderTypeLookup.setRenderLayer(BBBlocks.WHITE_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.PINK_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.BUTTERCUP.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_WHITE_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_PINK_CLOVER.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BBBlocks.POTTED_BUTTERCUP.get(), RenderType.getCutout());
	}
}
