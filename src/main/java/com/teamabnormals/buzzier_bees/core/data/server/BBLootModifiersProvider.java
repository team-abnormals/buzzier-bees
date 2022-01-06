package com.teamabnormals.buzzier_bees.core.data.server;

import com.google.gson.Gson;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.loot.modification.LootModifiers;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier.Config;
import com.teamabnormals.blueprint.core.util.modification.*;
import com.teamabnormals.blueprint.core.util.modification.targeting.ConditionedModifierTargetSelector;
import com.teamabnormals.blueprint.core.util.modification.targeting.ModifierTargetSelectorRegistry;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.PredicateManager;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;

import java.util.*;

public class BBLootModifiersProvider {

	public static ModifierDataProvider<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> createProvider(DataGenerator dataGenerator) {
		return LootModifiers.createDataProvider(dataGenerator, "Loot Modifiers", BuzzierBees.MOD_ID,
				createProviderEntry("desert_pyramid", Arrays.asList(
						createEntriesModifier(0, LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build()),
						createEntriesModifier(1, LootItem.lootTableItem(Items.HONEY_BOTTLE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).build())),
						BuiltInLootTables.DESERT_PYRAMID),
				createProviderEntry("jungle_temple", Collections.singletonList(createEntriesModifier(0, LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build())), BuiltInLootTables.JUNGLE_TEMPLE)
		);
	}

	private static ModifierDataProvider.ProviderEntry<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> createProviderEntry(String name, List<ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?>> modifiers, ResourceLocation... lootTables) {
		return new ModifierDataProvider.ProviderEntry<>(
				new TargetedModifier<>(new ConditionedModifierTargetSelector<>(ModifierTargetSelectorRegistry.NAMES.withConfiguration(Arrays.asList(lootTables))), modifiers),
				new ResourceLocation(BuzzierBees.MOD_ID, name)
		);
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> createEntriesModifier(int index, LootPoolEntryContainer entries) {
		return new ConfiguredModifier<>(LootModifiers.ENTRIES_MODIFIER, new Config(false, index, Collections.singletonList(entries)));
	}

}