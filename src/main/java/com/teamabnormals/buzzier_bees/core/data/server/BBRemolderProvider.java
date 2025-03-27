package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.blueprint.common.remolder.RemolderTypes;
import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.*;

public class BBRemolderProvider extends RemolderProvider {
	public BBRemolderProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(BuzzierBees.MOD_ID, PackOutput.Target.DATA_PACK, packOutput, lookupProvider);
	}

	@Override
	protected void registerEntries(HolderLookup.Provider provider) {
		this.entry("jungle_temple")
				.path("minecraft:loot_table/chests/jungle_temple")
				.remolder(addEntry(0, LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build()));
		this.entry("desert_pyramid")
				.path("minecraft:loot_table/chests/desert_pyramid")
				.remolder(RemolderTypes.sequence(
						addEntry(0, LootItem.lootTableItem(BBItems.FOUR_LEAF_CLOVER.get()).setWeight(5).build()),
						addEntry(1, LootItem.lootTableItem(Items.HONEY_BOTTLE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).build())));
	}
}