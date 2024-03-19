package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.block.*;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BBBlocks {
	public static final BlockSubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> CRYSTALLIZED_HONEY_BLOCK = HELPER.createBlock("crystallized_honey_block", () -> new CrystallizedHoneyBlock(BBBlockProperties.CRYSTALLIZED_HONEY));
	public static final RegistryObject<Block> HONEY_LAMP = HELPER.createBlock("honey_lamp", () -> new HoneyLampBlock(Block.Properties.copy(Blocks.END_ROD).sound(SoundType.HONEY_BLOCK)));

	public static final RegistryObject<Block> HONEYCOMB_BRICKS = HELPER.createBlock("honeycomb_bricks", () -> new Block(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> HONEYCOMB_BRICK_STAIRS = HELPER.createBlock("honeycomb_brick_stairs", () -> new StairBlock(() -> HONEYCOMB_BRICKS.get().defaultBlockState(), BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> HONEYCOMB_BRICK_SLAB = HELPER.createBlock("honeycomb_brick_slab", () -> new SlabBlock(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> HONEYCOMB_BRICK_WALL = HELPER.createBlock("honeycomb_brick_wall", () -> new WallBlock(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> CHISELED_HONEYCOMB_BRICKS = HELPER.createBlock("chiseled_honeycomb_bricks", () -> new Block(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> HONEYCOMB_TILES = HELPER.createBlock("honeycomb_tiles", () -> new Block(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> HONEYCOMB_TILE_STAIRS = HELPER.createBlock("honeycomb_tile_stairs", () -> new StairBlock(() -> HONEYCOMB_TILES.get().defaultBlockState(), BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> HONEYCOMB_TILE_SLAB = HELPER.createBlock("honeycomb_tile_slab", () -> new SlabBlock(BBBlockProperties.HONEYCOMB_BRICKS));
	public static final RegistryObject<Block> HONEYCOMB_TILE_WALL = HELPER.createBlock("honeycomb_tile_wall", () -> new WallBlock(BBBlockProperties.HONEYCOMB_BRICKS));

	public static final RegistryObject<Block> HONEYCOMB_DOOR = HELPER.createBlock("honeycomb_door", () -> new HoneycombDoorBlock(BBBlockProperties.HONEYCOMB_DOOR));
	public static final RegistryObject<Block> HONEYCOMB_TRAPDOOR = HELPER.createBlock("honeycomb_trapdoor", () -> new HoneycombTrapDoorBlock(BBBlockProperties.HONEYCOMB_TRAPDOOR));

	public static final RegistryObject<Block> SOUL_CANDLE = HELPER.createBlock("soul_candle", () -> new SoulCandleBlock(BBBlockProperties.SOUL_CANDLE));
	public static final RegistryObject<Block> SOUL_CANDLE_CAKE = HELPER.createBlockNoItem("soul_candle_cake", () -> new SoulCandleCakeBlock(SOUL_CANDLE.get(), BBBlockProperties.SOUL_CANDLE_CAKE));

	public static final RegistryObject<Block> BUTTERCUP = HELPER.createBlock("buttercup", () -> new ButtercupBlock(BBMobEffects.SUNNY, 12, BBBlockProperties.FLOWER));
	public static final RegistryObject<Block> WHITE_CLOVER = HELPER.createBlock("white_clover", () -> new FlowerBlock(() -> MobEffects.UNLUCK, 6, BBBlockProperties.FLOWER));
	public static final RegistryObject<Block> PINK_CLOVER = HELPER.createBlock("pink_clover", () -> new FlowerBlock(() -> MobEffects.UNLUCK, 12, BBBlockProperties.FLOWER));
	public static final RegistryObject<Block> POTTED_WHITE_CLOVER = HELPER.createBlockNoItem("potted_white_clover", () -> new FlowerPotBlock(WHITE_CLOVER.get(), BBBlockProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_PINK_CLOVER = HELPER.createBlockNoItem("potted_pink_clover", () -> new FlowerPotBlock(PINK_CLOVER.get(), BBBlockProperties.FLOWER_POT));
	public static final RegistryObject<Block> POTTED_BUTTERCUP = HELPER.createBlockNoItem("potted_buttercup", () -> new FlowerPotBlock(BUTTERCUP.get(), BBBlockProperties.FLOWER_POT));

	public static class BBBlockProperties {
		public static final BlockSetType HONEYCOMB = BlockSetType.register(new BlockSetType(BuzzierBees.MOD_ID + ":honeycomb", true, SoundType.CORAL_BLOCK, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON));

		public static final BlockBehaviour.Properties FLOWER = BlockBehaviour.Properties.of().noOcclusion().noCollission().instabreak().sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties FLOWER_POT = BlockBehaviour.Properties.of().instabreak().noOcclusion();
		public static final BlockBehaviour.Properties SOUL_CANDLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(SoulCandleBlock.DIM_LIGHT_EMISSION);
		public static final BlockBehaviour.Properties SOUL_CANDLE_CAKE = BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 2 : 0);
		public static final BlockBehaviour.Properties CRYSTALLIZED_HONEY = BlockBehaviour.Properties.of().noOcclusion().friction(0.98F).strength(0.3F).sound(SoundType.GLASS);
		public static final BlockBehaviour.Properties HONEYCOMB_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(2.0F, 6.0F).sound(SoundType.CORAL_BLOCK);
		public static final BlockBehaviour.Properties HONEYCOMB_DOOR = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties HONEYCOMB_TRAPDOOR = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY).isValidSpawn(PropertyUtil::never);
	}
}
