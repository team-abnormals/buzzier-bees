package com.minecraftabnormals.buzzier_bees.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.ai.brain.task.GiveHeroGiftsTask;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.DesertVillagePools;
import net.minecraft.world.gen.feature.structure.PlainsVillagePools;
import net.minecraft.world.gen.feature.structure.SavannaVillagePools;
import net.minecraft.world.gen.feature.structure.SnowyVillagePools;
import net.minecraft.world.gen.feature.structure.TaigaVillagePools;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBVillagers {
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, BuzzierBees.MOD_ID);
	public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, BuzzierBees.MOD_ID);

	public static final RegistryObject<PointOfInterestType> HONEY_POT = POI_TYPES.register("honey_pot", () -> new PointOfInterestType("apiarist", PointOfInterestType.getAllStates(BBBlocks.HONEY_POT.get()), 1, 1));
	public static final RegistryObject<VillagerProfession> APIARIST = PROFESSIONS.register("apiarist", () -> new VillagerProfession("apiarist", HONEY_POT.get(), ImmutableSet.of(Items.HONEYCOMB, Items.HONEY_BOTTLE), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LEATHERWORKER));

	public static void registerVillagers() {
		registerGifts();
		registerPointOfInterests();
		registerVillagerHouses();
	}

	private static void registerGifts() {
		GiveHeroGiftsTask.GIFTS.put(APIARIST.get(), new ResourceLocation(BuzzierBees.MOD_ID, "gameplay/hero_of_the_village/apiarist_gift"));
	}

	private static void registerPointOfInterests() {
		try {
			ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class).invoke(null, HONEY_POT.get());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerVillagerHouses() {
		JigsawPatternRegistry.func_244094_a(new JigsawPattern(new ResourceLocation(BuzzierBees.MOD_ID, "village/apiarist_bees"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(JigsawPiece.func_242849_a(BuzzierBees.MOD_ID + ":village/apiarist_bees/bees_1"), 1)), JigsawPattern.PlacementBehaviour.RIGID));

		PlainsVillagePools.init();
		SnowyVillagePools.init();
		SavannaVillagePools.init();
		DesertVillagePools.init();
		TaigaVillagePools.init();

		for (String biome : new String[]{"plains", "snowy", "savanna", "desert", "taiga"})
			DataUtil.addToJigsawPattern(new ResourceLocation("village/" + biome + "/houses"), JigsawPiece.func_242849_a(BuzzierBees.MOD_ID + ":village/apiarist_house_" + biome + "_1").apply(JigsawPattern.PlacementBehaviour.RIGID), 1);
	}
}