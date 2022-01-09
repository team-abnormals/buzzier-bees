package com.teamabnormals.buzzier_bees.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.feature.structures.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBVillagers {
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, BuzzierBees.MOD_ID);
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, BuzzierBees.MOD_ID);

	public static final RegistryObject<PoiType> HONEY_POT = POI_TYPES.register("honey_pot", () -> new PoiType("apiarist", PoiType.getBlockStates(BBBlocks.HONEY_POT.get()), 1, 1));
	public static final RegistryObject<VillagerProfession> APIARIST = PROFESSIONS.register("apiarist", () -> new VillagerProfession("apiarist", HONEY_POT.get(), ImmutableSet.of(Items.HONEYCOMB, Items.HONEY_BOTTLE), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LEATHERWORKER));

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
			ObfuscationReflectionHelper.findMethod(PoiType.class, "m_27367_", PoiType.class).invoke(null, HONEY_POT.get());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerVillagerHouses() {
		Pools.register(new StructureTemplatePool(new ResourceLocation(BuzzierBees.MOD_ID, "village/apiarist_bees"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy(BuzzierBees.MOD_ID + ":village/apiarist_bees/bees_1"), 1)), StructureTemplatePool.Projection.RIGID));

		PlainVillagePools.bootstrap();
		SnowyVillagePools.bootstrap();
		SavannaVillagePools.bootstrap();
		DesertVillagePools.bootstrap();
		TaigaVillagePools.bootstrap();

		for (String biome : new String[]{"plains", "snowy", "savanna", "desert", "taiga"})
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation(BuzzierBees.MOD_ID, "village/apiarist_house_" + biome + "_1"), 1);
	}

	private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight) {
		StructureTemplatePool old = BuiltinRegistries.TEMPLATE_POOL.get(pool);
		List<StructurePoolElement> shuffled = old.getShuffledTemplates(new Random());
		List<Pair<StructurePoolElement, Integer>> newPieces = new ArrayList<>();
		for (StructurePoolElement p : shuffled) newPieces.add(new Pair<>(p, 1));
		newPieces.add(Pair.of(new LegacySinglePoolElement(Either.left(toAdd), () -> ProcessorLists.EMPTY, StructureTemplatePool.Projection.RIGID), weight));
		ResourceLocation name = old.getName();
		Registry.register(BuiltinRegistries.TEMPLATE_POOL, pool, new StructureTemplatePool(pool, name, newPieces));
	}
}