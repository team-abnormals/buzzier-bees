package com.teamabnormals.buzzier_bees.core.data.server.modifiers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.loot.modification.LootModificationManager;
import com.teamabnormals.blueprint.common.loot.modification.LootModifierSerializers;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import com.teamabnormals.blueprint.core.util.modification.ObjectModifierGroup;
import com.teamabnormals.blueprint.core.util.modification.ObjectModifierProvider;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.Deserializers;
import net.minecraft.world.level.storage.loot.PredicateManager;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;

import java.util.Arrays;
import java.util.Collections;

public class BBLootModifiersProvider extends ObjectModifierProvider<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> {

	public BBLootModifiersProvider(DataGenerator dataGenerator) {
		super(dataGenerator, BuzzierBees.MOD_ID, true, LootModificationManager.TARGET_DIRECTORY, new GsonBuilder().setPrettyPrinting().create(), LootModifierSerializers.REGISTRY, (group) -> Deserializers.createLootTableSerializer().create());
	}

	@Override
	protected void registerEntries() {
		this.registerEntry("jungle_temple", new LootPoolEntriesModifier(false, 0, Collections.singletonList(LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build())), BuiltInLootTables.JUNGLE_TEMPLE);
		this.registerEntry("desert_pyramid", new ObjectModifierGroup<>(new NamesResourceSelector(BuiltInLootTables.DESERT_PYRAMID), Arrays.asList(
				new LootPoolEntriesModifier(false, 0, Collections.singletonList(LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build())),
				new LootPoolEntriesModifier(false, 1, Collections.singletonList(LootItem.lootTableItem(Items.HONEY_BOTTLE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).build()))
		)));
	}
}