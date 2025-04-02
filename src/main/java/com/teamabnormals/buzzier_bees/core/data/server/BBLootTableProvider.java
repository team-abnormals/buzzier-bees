package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamabnormals.buzzier_bees.core.registry.BBBlocks.*;

public class BBLootTableProvider extends LootTableProvider {

	public BBLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, BuiltInLootTables.all(), List.of(
				new LootTableProvider.SubProviderEntry(BBBlockLoot::new, LootContextParamSets.BLOCK),
				new SubProviderEntry(BBEntityLoot::new, LootContextParamSets.ENTITY)
		), provider);
	}

	@Override
	protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {
	}

	private static class BBBlockLoot extends BlockLootSubProvider {
		private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

		protected BBBlockLoot(HolderLookup.Provider provider) {
			super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.dropSelf(CHISELED_HONEYCOMB_BRICKS.get());
			this.dropSelf(CRYSTALLIZED_HONEY_BLOCK.get());
			this.dropSelf(HONEY_LAMP.get());
			this.add(HONEYCOMB_BRICK_SLAB.get(), createSlabItemTable(HONEYCOMB_BRICK_SLAB.get()));
			this.dropSelf(HONEYCOMB_BRICK_STAIRS.get());
			this.dropSelf(HONEYCOMB_BRICK_WALL.get());
			this.dropSelf(HONEYCOMB_BRICKS.get());
			this.add(HONEYCOMB_DOOR.get(), createDoorTable(HONEYCOMB_DOOR.get()));
			this.add(HONEYCOMB_TILE_SLAB.get(), createSlabItemTable(HONEYCOMB_TILE_SLAB.get()));
			this.dropSelf(HONEYCOMB_TILE_STAIRS.get());
			this.dropSelf(HONEYCOMB_TILE_WALL.get());
			this.dropSelf(HONEYCOMB_TILES.get());
			this.dropSelf(HONEYCOMB_TRAPDOOR.get());
			this.dropSelf(BUTTERCUP.get());
			this.dropSelf(PINK_CLOVER.get());
			this.dropSelf(WHITE_CLOVER.get());
			this.dropPottedContents(POTTED_BUTTERCUP.get());
			this.dropPottedContents(POTTED_PINK_CLOVER.get());
			this.dropPottedContents(POTTED_WHITE_CLOVER.get());
			this.add(SOUL_CANDLE.get(), createCandleDrops(SOUL_CANDLE.get()));
			this.add(ENDER_CANDLE.get(), createCandleDrops(ENDER_CANDLE.get()));
			this.add(CUPRIC_CANDLE.get(), createCandleDrops(CUPRIC_CANDLE.get()));
			this.add(SOUL_CANDLE_CAKE.get(), createCandleCakeDrops(SOUL_CANDLE.get()));
			this.add(ENDER_CANDLE_CAKE.get(), createCandleCakeDrops(ENDER_CANDLE.get()));
			this.add(CUPRIC_CANDLE_CAKE.get(), createCandleCakeDrops(CUPRIC_CANDLE.get()));
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return BuiltInRegistries.BLOCK.stream().filter(block -> BuzzierBees.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace())).collect(Collectors.toSet());
		}
	}

	private static class BBEntityLoot extends EntityLootSubProvider {

		protected BBEntityLoot(HolderLookup.Provider provider) {
			super(FeatureFlags.DEFAULT_FLAGS, provider);
		}

		@Override
		public void generate() {
			this.add(BBEntityTypes.MOOBLOOM.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.LEATHER).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BEEF).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));
			this.add(BBEntityTypes.GRIZZLY_BEAR.get(), LootTable.lootTable());
		}

		@Override
		protected Stream<EntityType<?>> getKnownEntityTypes() {
			return BuiltInRegistries.ENTITY_TYPE.stream().filter(entity -> BuzzierBees.MOD_ID.equals(BuiltInRegistries.ENTITY_TYPE.getKey(entity).getNamespace()));
		}
	}
}