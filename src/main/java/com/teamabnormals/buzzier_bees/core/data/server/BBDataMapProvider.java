package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import com.teamabnormals.buzzier_bees.core.registry.BBItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class BBDataMapProvider extends DataMapProvider {

	public BBDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void gather(HolderLookup.Provider provider) {
		this.builder(NeoForgeDataMaps.COMPOSTABLES)
				.add(BBBlocks.PINK_CLOVER.getId(), new Compostable(0.65F), false)
				.add(BBBlocks.WHITE_CLOVER.getId(), new Compostable(0.65F), false)
				.add(BBBlocks.BUTTERCUP.getId(), new Compostable(0.65F), false)
				.add(BBItems.FOUR_LEAF_CLOVER, new Compostable(0.65F), false)
				.add(BBItems.HONEY_BREAD, new Compostable(0.65F), false)
				.add(BBItems.HONEY_APPLE, new Compostable(0.85F), false);
	}
}