package com.teamabnormals.buzzier_bees.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.loot.modification.LootModifierProvider;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Collections;

public class BBLootModifiersProvider extends LootModifierProvider {

	public BBLootModifiersProvider(DataGenerator dataGenerator) {
		super(dataGenerator, BuzzierBees.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		this.entry("jungle_temple").selects(BuiltInLootTables.JUNGLE_TEMPLE).addModifier(new LootPoolEntriesModifier(false, 0, Collections.singletonList(LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build())));
		this.entry("desert_pyramid").selects(BuiltInLootTables.DESERT_PYRAMID)
				.addModifier(new LootPoolEntriesModifier(false, 0, Collections.singletonList(LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build())))
				.addModifier(new LootPoolEntriesModifier(false, 1, Collections.singletonList(LootItem.lootTableItem(Items.HONEY_BOTTLE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).build()))
		);
	}
}