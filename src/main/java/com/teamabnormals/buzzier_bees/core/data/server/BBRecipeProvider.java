package com.teamabnormals.buzzier_bees.core.data.server;

import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.tags.BBItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.buzzier_bees.core.registry.BBBlocks.*;
import static com.teamabnormals.buzzier_bees.core.registry.BBItems.*;
import static net.minecraft.world.item.Items.*;

public class BBRecipeProvider extends BlueprintRecipeProvider {
	public BBRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(BuzzierBees.MOD_ID, output, provider);
	}

	@Override
	public void buildRecipes(RecipeOutput output) {
		candleRecipe(output, SOUL_CANDLE, ItemTags.SOUL_FIRE_BASE_BLOCKS);
		candleRecipe(output.withConditions(new ModLoadedCondition("endergetic")), ENDER_CANDLE, BBItemTags.ENDER_FIRE_BASE_BLOCKS);
		candleRecipe(output.withConditions(new ModLoadedCondition("caverns_and_chasms")), CUPRIC_CANDLE, BBItemTags.CUPRIC_FIRE_BASE_BLOCKS);
		dyeRecipe(output, PINK_DYE, PINK_CLOVER);
		dyeRecipe(output, WHITE_DYE, WHITE_CLOVER);
		dyeRecipe(output, YELLOW_DYE, BUTTERCUP);

		smeltingRecipe(output, List.of(HONEY_BLOCK), RecipeCategory.BUILDING_BLOCKS, CRYSTALLIZED_HONEY_BLOCK, 0.5F, 200, "crysallized_honey_block");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GLAZED_PORKCHOP).requires(HONEY_BOTTLE).requires(COOKED_PORKCHOP).unlockedBy("has_honey", has(HONEY_BOTTLE)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HONEY_APPLE).requires(HONEY_BOTTLE).requires(APPLE).unlockedBy("has_honey", has(HONEY_BOTTLE)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HONEY_BREAD).requires(HONEY_BOTTLE).requires(BREAD).unlockedBy("has_honey", has(HONEY_BOTTLE)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, HONEY_LAMP).requires(END_ROD).requires(HONEY_BOTTLE).requires(Tags.Items.NUGGETS_GOLD).unlockedBy("has_end_rod", has(END_ROD)).save(output);

		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_BRICKS, HONEYCOMB_BLOCK);
		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_BRICK_WALL, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS);
		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_BRICK_STAIRS, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS);
		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, 2, HONEYCOMB_BRICK_SLAB, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS);
		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, CHISELED_HONEYCOMB_BRICKS, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS);

		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_TILES, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS);
		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_TILE_WALL, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS, HONEYCOMB_TILES);
		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_TILE_STAIRS, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS, HONEYCOMB_TILES);
		stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, 2, HONEYCOMB_TILE_SLAB, HONEYCOMB_BLOCK, HONEYCOMB_BRICKS, HONEYCOMB_TILES);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HONEYCOMB_BANNER_PATTERN).requires(PAPER).requires(HONEYCOMB).unlockedBy(getHasName(HONEYCOMB), has(HONEYCOMB)).save(output);
		doorBuilder(HONEYCOMB_DOOR, Ingredient.of(HONEYCOMB)).unlockedBy(getHasName(HONEYCOMB), has(HONEYCOMB)).save(output);
		trapdoorBuilder(HONEYCOMB_TRAPDOOR, Ingredient.of(HONEYCOMB)).unlockedBy(getHasName(HONEYCOMB), has(HONEYCOMB)).save(output);

		infestedBlockRecipe(output, STONE, INFESTED_STONE);
		infestedBlockRecipe(output, CHISELED_STONE_BRICKS, INFESTED_CHISELED_STONE_BRICKS);
		infestedBlockRecipe(output, COBBLESTONE, INFESTED_COBBLESTONE);
		infestedBlockRecipe(output, CRACKED_STONE_BRICKS, INFESTED_CRACKED_STONE_BRICKS);
		infestedBlockRecipe(output, DEEPSLATE, INFESTED_DEEPSLATE);
		infestedBlockRecipe(output, MOSSY_STONE_BRICKS, INFESTED_MOSSY_STONE_BRICKS);
		infestedBlockRecipe(output, STONE_BRICKS, INFESTED_STONE_BRICKS);

		chiseled(output, RecipeCategory.BUILDING_BLOCKS, CHISELED_HONEYCOMB_BRICKS, HONEYCOMB_BRICK_SLAB);
		slab(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_BRICK_SLAB, HONEYCOMB_BRICKS);
		stairBuilder(HONEYCOMB_BRICK_STAIRS, Ingredient.of(HONEYCOMB_BRICKS)).unlockedBy(getHasName(HONEYCOMB_BRICKS), has(HONEYCOMB_BRICKS)).save(output);
		wall(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_BRICK_WALL, HONEYCOMB_BRICKS);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_BRICKS, 4).define('#', HONEYCOMB_BLOCK).pattern("##").pattern("##").unlockedBy(getHasName(HONEYCOMB_BLOCK), has(HONEYCOMB_BLOCK)).save(output);

		slab(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_TILE_SLAB, HONEYCOMB_TILES);
		stairBuilder(HONEYCOMB_TILE_STAIRS, Ingredient.of(HONEYCOMB_TILES)).unlockedBy(getHasName(HONEYCOMB_TILES), has(HONEYCOMB_TILES)).save(output);
		wall(output, RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_TILE_WALL, HONEYCOMB_TILES);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HONEYCOMB_TILES, 4).define('#', HONEYCOMB_BRICKS).pattern("##").pattern("##").unlockedBy(getHasName(HONEYCOMB_BRICKS), has(HONEYCOMB_BRICKS)).save(output);
	}

	public static void candleRecipe(RecipeOutput output, ItemLike candle, TagKey<Item> base) {
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, candle)
				.pattern("S")
				.pattern("H")
				.pattern("B")
				.define('S', STRING)
				.define('H', HONEYCOMB)
				.define('B', base)
				.unlockedBy(getHasName(HONEYCOMB), has(HONEYCOMB))
				.save(output);
	}

	public static void dyeRecipe(RecipeOutput output, ItemLike dye, ItemLike flower) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye)
				.group(getItemName(dye))
				.requires(flower)
				.unlockedBy(getHasName(flower), has(flower))
				.save(output, BuzzierBees.location(getItemName(dye) + "_from_" + getItemName(flower)));
	}

	public static void infestedBlockRecipe(RecipeOutput output, ItemLike block, ItemLike infestedBlock) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, infestedBlock).requires(block).requires(BOTTLE_OF_SILVERFISH).unlockedBy(getHasName(BOTTLE_OF_SILVERFISH), has(BOTTLE_OF_SILVERFISH)).save(output);
	}

	public static void stonecutterRecipe(RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike... inputs) {
		stonecutterRecipe(output, category, 1, result, inputs);
	}

	public static void stonecutterRecipe(RecipeOutput output, RecipeCategory category, int count, ItemLike result, ItemLike... inputs) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(inputs), category, result, count).unlockedBy(getHasName(Arrays.stream(inputs).findFirst().get()), has(Arrays.stream(inputs).findFirst().get())).save(output, BuzzierBees.location(getItemName(result) + "_from_stonecutting"));
	}
}