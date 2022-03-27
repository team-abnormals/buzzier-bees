package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.block.*;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBBlocks {
	public static final BlockSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> CRYSTALLIZED_HONEY_BLOCK = HELPER.createBlock("crystallized_honey_block", () -> new CrystallizedHoneyBlock(BBProperties.CRYSTALLIZED_HONEY), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> HONEY_LAMP = HELPER.createBlock("honey_lamp", () -> new HoneyLampBlock(Block.Properties.copy(Blocks.END_ROD).sound(SoundType.HONEY_BLOCK)), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> HONEYCOMB_DOOR = HELPER.createBlock("honeycomb_door", () -> new HoneycombDoorBlock(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> HONEYCOMB_TRAPDOOR = HELPER.createBlock("honeycomb_trapdoor", () -> new HoneycombTrapDoorBlock(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Block> HONEYCOMB_BRICKS = HELPER.createBlock("honeycomb_bricks", () -> new Block(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_STAIRS = HELPER.createBlock("honeycomb_brick_stairs", () -> new StairBlock(() -> HONEYCOMB_BRICKS.get().defaultBlockState(), BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_SLAB = HELPER.createBlock("honeycomb_brick_slab", () -> new SlabBlock(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_WALL = HELPER.createBlock("honeycomb_brick_wall", () -> new WallBlock(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> HONEYCOMB_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "honeycomb_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(HONEYCOMB_BRICKS.get())), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_HONEYCOMB_BRICKS = HELPER.createBlock("chiseled_honeycomb_bricks", () -> new Block(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_TILES = HELPER.createBlock("honeycomb_tiles", () -> new Block(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_STAIRS = HELPER.createBlock("honeycomb_tile_stairs", () -> new StairBlock(() -> HONEYCOMB_TILES.get().defaultBlockState(), BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_SLAB = HELPER.createBlock("honeycomb_tile_slab", () -> new SlabBlock(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_WALL = HELPER.createBlock("honeycomb_tile_wall", () -> new WallBlock(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> HONEYCOMB_TILE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "honeycomb_tile_vertical_slab", () -> new VerticalSlabBlock(BBProperties.HONEYCOMB_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> SOUL_CANDLE = HELPER.createBlock("soul_candle", () -> new SoulCandleBlock(BBProperties.SOUL_CANDLE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> SOUL_CANDLE_CAKE = HELPER.createBlockNoItem("soul_candle_cake", () -> new SoulCandleCakeBlock(SOUL_CANDLE.get(), BBProperties.SOUL_CANDLE_CAKE));

	public static final RegistryObject<Block> BUTTERCUP = HELPER.createBlock("buttercup", () -> new ButtercupBlock(BBMobEffects.SUNNY, 12 * 20, BBProperties.FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> WHITE_CLOVER = HELPER.createBlock("white_clover", () -> new BlueprintFlowerBlock(() -> MobEffects.UNLUCK, 30, BBProperties.FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER = HELPER.createBlock("pink_clover", () -> new BlueprintFlowerBlock(() -> MobEffects.UNLUCK, 60, BBProperties.FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_WHITE_CLOVER = HELPER.createBlockNoItem("potted_white_clover", () -> new FlowerPotBlock(WHITE_CLOVER.get(), BBProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_PINK_CLOVER = HELPER.createBlockNoItem("potted_pink_clover", () -> new FlowerPotBlock(PINK_CLOVER.get(), BBProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_BUTTERCUP = HELPER.createBlockNoItem("potted_buttercup", () -> new FlowerPotBlock(BUTTERCUP.get(), BBProperties.FLOWER_POT));

	public static class BBProperties {
		public static final BlockBehaviour.Properties FLOWER = Block.Properties.of(Material.PLANT).noOcclusion().noCollission().instabreak().sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties FLOWER_POT = Block.Properties.of(Material.DECORATION).instabreak().noOcclusion();
		public static final BlockBehaviour.Properties SOUL_CANDLE = BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_BROWN).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(SoulCandleBlock.DIM_LIGHT_EMISSION);
		public static final BlockBehaviour.Properties SOUL_CANDLE_CAKE = BlockBehaviour.Properties.copy(Blocks.CAKE).lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 2 : 0);
		public static final BlockBehaviour.Properties HONEYCOMB_BRICKS = Block.Properties.of(Material.CLAY, MaterialColor.COLOR_ORANGE).strength(2.0F, 6.0F).sound(SoundType.CORAL_BLOCK);
		public static final BlockBehaviour.Properties CRYSTALLIZED_HONEY = Block.Properties.of(Material.CAKE).noOcclusion().friction(0.98F).strength(0.3F).sound(SoundType.GLASS);
	}
}
