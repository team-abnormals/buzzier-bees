package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.common.block.*;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.block.*;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBBlocks {
	public static final BlockSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> SPRUCE_BEEHIVE = HELPER.createBlock("spruce_beehive", () -> new BlueprintBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BIRCH_BEEHIVE = HELPER.createBlock("birch_beehive", () -> new BlueprintBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> JUNGLE_BEEHIVE = HELPER.createBlock("jungle_beehive", () -> new BlueprintBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ACACIA_BEEHIVE = HELPER.createBlock("acacia_beehive", () -> new BlueprintBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DARK_OAK_BEEHIVE = HELPER.createBlock("dark_oak_beehive", () -> new BlueprintBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CRIMSON_BEEHIVE = HELPER.createBlock("crimson_beehive", () -> new BlueprintBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> WARPED_BEEHIVE = HELPER.createBlock("warped_beehive", () -> new BlueprintBeehiveBlock(Block.Properties.copy(Blocks.BEEHIVE)), CreativeModeTab.TAB_DECORATIONS);

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

	public static final RegistryObject<Block> BUTTERCUP = HELPER.createBlock("buttercup", () -> new ButtercupBlock(BBMobEffects.SUNNY, 12 * 20, BBProperties.FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> WHITE_CLOVER = HELPER.createBlock("white_clover", () -> new BlueprintFlowerBlock(() -> MobEffects.UNLUCK, 30, BBProperties.FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> PINK_CLOVER = HELPER.createBlock("pink_clover", () -> new BlueprintFlowerBlock(() -> MobEffects.UNLUCK, 60, BBProperties.FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_WHITE_CLOVER = HELPER.createBlockNoItem("potted_white_clover", () -> new FlowerPotBlock(WHITE_CLOVER.get(), BBProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_PINK_CLOVER = HELPER.createBlockNoItem("potted_pink_clover", () -> new FlowerPotBlock(PINK_CLOVER.get(), BBProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_BUTTERCUP = HELPER.createBlockNoItem("potted_buttercup", () -> new FlowerPotBlock(BUTTERCUP.get(), BBProperties.FLOWER_POT));

	public static class BBProperties {
		public static final Block.Properties FLOWER = Block.Properties.of(Material.PLANT).noOcclusion().noCollission().instabreak().sound(SoundType.GRASS);
		public static final Block.Properties FLOWER_POT = Block.Properties.of(Material.DECORATION).instabreak().noOcclusion();

		public static final Block.Properties HONEYCOMB_BRICKS = Block.Properties.of(Material.CLAY, MaterialColor.COLOR_ORANGE).strength(2.0F, 6.0F).sound(SoundType.CORAL_BLOCK);
		public static final Block.Properties CRYSTALLIZED_HONEY = Block.Properties.of(Material.CAKE).noOcclusion().friction(0.98F).strength(0.3F).sound(SoundType.GLASS);
	}
}
