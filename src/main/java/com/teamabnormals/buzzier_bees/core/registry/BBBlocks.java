package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.block.*;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.other.BBConstants;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class BBBlocks {
	public static final BlockSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getBlockSubHelper();

	public static final DeferredBlock<Block> CRYSTALLIZED_HONEY_BLOCK = HELPER.createBlock("crystallized_honey_block", () -> new CrystallizedHoneyBlock(BBBlockProperties.CRYSTALLIZED_HONEY));
	public static final DeferredBlock<Block> HONEY_LAMP = HELPER.createBlock("honey_lamp", () -> new HoneyLampBlock(Block.Properties.ofFullCopy(Blocks.END_ROD).sound(SoundType.HONEY_BLOCK)));

	public static final DeferredBlock<Block> HONEYCOMB_BRICKS = HELPER.createBlock("honeycomb_bricks", () -> new Block(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> HONEYCOMB_BRICK_STAIRS = HELPER.createBlock("honeycomb_brick_stairs", () -> new StairBlock(HONEYCOMB_BRICKS.get().defaultBlockState(), BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> HONEYCOMB_BRICK_SLAB = HELPER.createBlock("honeycomb_brick_slab", () -> new SlabBlock(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> HONEYCOMB_BRICK_WALL = HELPER.createBlock("honeycomb_brick_wall", () -> new WallBlock(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> CHISELED_HONEYCOMB_BRICKS = HELPER.createBlock("chiseled_honeycomb_bricks", () -> new Block(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> HONEYCOMB_TILES = HELPER.createBlock("honeycomb_tiles", () -> new Block(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> HONEYCOMB_TILE_STAIRS = HELPER.createBlock("honeycomb_tile_stairs", () -> new StairBlock(HONEYCOMB_TILES.get().defaultBlockState(), BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> HONEYCOMB_TILE_SLAB = HELPER.createBlock("honeycomb_tile_slab", () -> new SlabBlock(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final DeferredBlock<Block> HONEYCOMB_TILE_WALL = HELPER.createBlock("honeycomb_tile_wall", () -> new WallBlock(BBBlockProperties.HONEYCOMB_BRICKS));

	public static final DeferredBlock<Block> HONEYCOMB_DOOR = HELPER.createBlock("honeycomb_door", () -> new HoneycombDoorBlock(BBBlockProperties.HONEYCOMB_DOOR));
	public static final DeferredBlock<Block> HONEYCOMB_TRAPDOOR = HELPER.createBlock("honeycomb_trapdoor", () -> new HoneycombTrapDoorBlock(BBBlockProperties.HONEYCOMB_TRAPDOOR));

	public static final DeferredBlock<Block> SOUL_CANDLE = HELPER.createBlock("soul_candle", () -> new SpecialCandleBlock(BBConstants.SMALL_SOUL_FIRE_FLAME, BBBlockProperties.SOUL_CANDLE));
	public static final DeferredBlock<Block> SOUL_CANDLE_CAKE = HELPER.createBlockNoItem("soul_candle_cake", () -> new SpecialCandleCakeBlock(SOUL_CANDLE.get(), BBConstants.SMALL_SOUL_FIRE_FLAME, BBBlockProperties.SOUL_CANDLE_CAKE));

	public static final DeferredBlock<Block> ENDER_CANDLE = HELPER.createBlock("ender_candle", () -> new SpecialCandleBlock(BBConstants.SMALL_ENDER_FIRE_FLAME, BBBlockProperties.ENDER_CANDLE));
	public static final DeferredBlock<Block> ENDER_CANDLE_CAKE = HELPER.createBlockNoItem("ender_candle_cake", () -> new SpecialCandleCakeBlock(ENDER_CANDLE.get(), BBConstants.SMALL_ENDER_FIRE_FLAME, BBBlockProperties.ENDER_CANDLE_CAKE));

	public static final DeferredBlock<Block> CUPRIC_CANDLE = HELPER.createBlock("cupric_candle", () -> new SpecialCandleBlock(BBConstants.SMALL_CUPRIC_FIRE_FLAME, BBBlockProperties.CUPRIC_CANDLE));
	public static final DeferredBlock<Block> CUPRIC_CANDLE_CAKE = HELPER.createBlockNoItem("cupric_candle_cake", () -> new SpecialCandleCakeBlock(CUPRIC_CANDLE.get(), BBConstants.SMALL_CUPRIC_FIRE_FLAME, BBBlockProperties.CUPRIC_CANDLE_CAKE));

	public static final DeferredBlock<Block> BUTTERCUP = HELPER.createBlock("buttercup", () -> new ButtercupBlock(BBMobEffects.SUNNY, 12, PropertyUtil.flower()));
	public static final DeferredBlock<Block> WHITE_CLOVER = HELPER.createBlock("white_clover", () -> new FlowerBlock(MobEffects.UNLUCK, 6, PropertyUtil.flower()));
	public static final DeferredBlock<Block> PINK_CLOVER = HELPER.createBlock("pink_clover", () -> new FlowerBlock(MobEffects.UNLUCK, 12, PropertyUtil.flower()));
	public static final DeferredBlock<Block> POTTED_WHITE_CLOVER = HELPER.createBlockNoItem("potted_white_clover", () -> new FlowerPotBlock(WHITE_CLOVER.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> POTTED_PINK_CLOVER = HELPER.createBlockNoItem("potted_pink_clover", () -> new FlowerPotBlock(PINK_CLOVER.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> POTTED_BUTTERCUP = HELPER.createBlockNoItem("potted_buttercup", () -> new FlowerPotBlock(BUTTERCUP.get(), PropertyUtil.flowerPot()));

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(BuzzierBees.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItems(() -> Blocks.HONEYCOMB_BLOCK,
						HONEYCOMB_BRICKS, HONEYCOMB_BRICK_STAIRS, HONEYCOMB_BRICK_SLAB, HONEYCOMB_BRICK_WALL, CHISELED_HONEYCOMB_BRICKS,
						HONEYCOMB_TILES, HONEYCOMB_TILE_STAIRS, HONEYCOMB_TILE_SLAB, HONEYCOMB_TILE_WALL,
						HONEYCOMB_DOOR, HONEYCOMB_TRAPDOOR)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Blocks.HONEY_BLOCK), CRYSTALLIZED_HONEY_BLOCK)
				.addItemsAfter(of(Blocks.LILY_OF_THE_VALLEY), WHITE_CLOVER, PINK_CLOVER, BUTTERCUP)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsAfter(modLoaded(Blocks.CANDLE, "caverns_and_chasms"), CUPRIC_CANDLE)
				.addItemsAfter(modLoaded(Blocks.CANDLE, "endergetic"), ENDER_CANDLE)
				.addItemsAfter(of(Blocks.CANDLE), SOUL_CANDLE)
				.addItemsAfter(of(Blocks.END_ROD), HONEY_LAMP);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static class BBBlockProperties {
		public static final BlockSetType HONEYCOMB = BlockSetTypeRegistryHelper.register(new BlockSetType(BuzzierBees.MOD_ID + ":honeycomb", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.CORAL_BLOCK, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON));

		public static final BlockBehaviour.Properties CRYSTALLIZED_HONEY = BlockBehaviour.Properties.of().noOcclusion().friction(0.98F).strength(0.3F).sound(SoundType.GLASS).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties HONEYCOMB_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(2.0F, 6.0F).sound(SoundType.CORAL_BLOCK);
		public static final BlockBehaviour.Properties HONEYCOMB_DOOR = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties HONEYCOMB_TRAPDOOR = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY).isValidSpawn(PropertyUtil::never);

		public static final BlockBehaviour.Properties SOUL_CANDLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(SpecialCandleBlock.DIM_LIGHT_EMISSION).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties SOUL_CANDLE_CAKE = BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(getLightValueLit(2));

		public static final BlockBehaviour.Properties ENDER_CANDLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties ENDER_CANDLE_CAKE = BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(getLightValueLit(3));

		public static final BlockBehaviour.Properties CUPRIC_CANDLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(SpecialCandleBlock.DIM_LIGHT_EMISSION).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties CUPRIC_CANDLE_CAKE = BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).lightLevel(getLightValueLit(2));

		private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
			return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
		}
	}
}
