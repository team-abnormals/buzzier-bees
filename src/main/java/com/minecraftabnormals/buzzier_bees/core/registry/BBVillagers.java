package com.minecraftabnormals.buzzier_bees.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.LegacySingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		DataUtil.registerVillagerGift(APIARIST.get());
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
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation(BuzzierBees.MOD_ID, "village/apiarist_house_" + biome + "_1"), 1);
	}

	private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight) {
		JigsawPattern old = WorldGenRegistries.JIGSAW_POOL.getOrDefault(pool);
		List<JigsawPiece> shuffled = old.getShuffledPieces(new Random());
		List<Pair<JigsawPiece, Integer>> newPieces = new ArrayList<>();
		for (JigsawPiece p : shuffled) newPieces.add(new Pair<>(p, 1));
		newPieces.add(Pair.of(new LegacySingleJigsawPiece(Either.left(toAdd), () -> ProcessorLists.field_244101_a, JigsawPattern.PlacementBehaviour.RIGID), weight));
		ResourceLocation name = old.getName();
		Registry.register(WorldGenRegistries.JIGSAW_POOL, pool, new JigsawPattern(pool, name, newPieces));
	}
}