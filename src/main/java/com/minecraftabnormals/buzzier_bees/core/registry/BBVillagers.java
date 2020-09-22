package com.minecraftabnormals.buzzier_bees.core.registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.ai.brain.task.GiveHeroGiftsTask;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern.PlacementBehaviour;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
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

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBVillagers {
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, BuzzierBees.MODID);
	public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, BuzzierBees.MODID);

	public static final RegistryObject<PointOfInterestType> HONEY_POT = POI_TYPES.register("honey_pot", () -> new PointOfInterestType("apiarist", PointOfInterestType.getAllStates(BBBlocks.HONEY_POT.get()), 1, 1));
	public static final RegistryObject<VillagerProfession> APIARIST = PROFESSIONS.register("apiarist", () -> new VillagerProfession("apiarist", HONEY_POT.get(), ImmutableSet.of(Items.HONEYCOMB, Items.HONEY_BOTTLE, BBItems.WAX.get()), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LEATHERWORKER));

	public static void init() {
		setupVillagers();
		setupPointOfInterests();
		setupVillagerHouses();
	}
	
	private static void setupVillagers() {
		GiveHeroGiftsTask.GIFTS.put(APIARIST.get(), new ResourceLocation(BuzzierBees.MODID, "gameplay/hero_of_the_village/apiarist_gift"));
	}

	private static void setupPointOfInterests() {
		try {
			ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class).invoke(null, HONEY_POT.get());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	private static void setupVillagerHouses() {
		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(BuzzierBees.MODID, "village/apiarist_bees"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece(BuzzierBees.MODID + ":village/apiarist_bees/bees_1"), 1)), JigsawPattern.PlacementBehaviour.RIGID));

		PlainsVillagePools.init();
		SnowyVillagePools.init();
		SavannaVillagePools.init();
		DesertVillagePools.init();
		TaigaVillagePools.init();

		for (String biome : new String[] { "plains", "snowy", "savanna", "desert", "taiga" })
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation(BuzzierBees.MODID, "village/apiarist_house_" + biome + "_1"), 1);
	}

	@SuppressWarnings("deprecation")
	private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight) {
		JigsawPattern old = JigsawManager.REGISTRY.get(pool);
		List<JigsawPiece> shuffled = old.getShuffledPieces(new Random());
		List<Pair<JigsawPiece, Integer>> newPieces = new ArrayList<>();
		for (JigsawPiece p : shuffled) {
			newPieces.add(new Pair<>(p, 1));
		}
		newPieces.add(new Pair<>(new SingleJigsawPiece(toAdd.toString()), weight));
		ResourceLocation something = old.getFallback();
		JigsawManager.REGISTRY.register(new JigsawPattern(pool, something, newPieces, PlacementBehaviour.RIGID));
	}
}