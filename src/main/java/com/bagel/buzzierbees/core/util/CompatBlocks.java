package com.bagel.buzzierbees.core.util;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CompatBlocks {
	public static Supplier<Block> WHITE_WISTERIA_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:white_wisteria_sapling"));
    public static Supplier<Block> BLUE_WISTERIA_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:blue_wisteria_sapling"));
    public static Supplier<Block> PINK_WISTERIA_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:pink_wisteria_sapling"));
    public static Supplier<Block> PURPLE_WISTERIA_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:purple_wisteria_sapling"));
    public static Supplier<Block> LAVENDER_BLOSSOM_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:lavender_blossom_sapling"));
    public static Supplier<Block> ORANGE_BLOSSOM_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:orange_blossom_sapling"));
    public static Supplier<Block> PINK_BLOSSOM_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:pink_blossom_sapling"));
    public static Supplier<Block> BLUE_BLOSSOM_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:blue_blossom_sapling"));
    public static Supplier<Block> YELLOW_BLOSSOM_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:yellow_blossom_sapling"));
    public static Supplier<Block> ROSEWOOD_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:rosewood_sapling"));
    public static Supplier<Block> YUCCA_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:yucca_sapling"));
    public static Supplier<Block> KOUSA_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:kousa_sapling"));
    public static Supplier<Block> ASPEN_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:aspen_sapling"));
    public static Supplier<Block> MAPLE_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity:maple_sapling"));
    public static Supplier<Block> YELLOW_MAPLE_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity:yellow_maple_sapling"));
    public static Supplier<Block> ORANGE_MAPLE_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity:orange_maple_sapling"));
    public static Supplier<Block> RED_MAPLE_SAPLING = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity:red_maple_sapling"));
    
    public static Supplier<Block> POISE_BUSH = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:poise_grass"));
    public static Supplier<Block> BLUE_PICKERELWEED = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("upgrade_aquatic:pickerel_weed_blue"));
    public static Supplier<Block> PURPLE_PICKERELWEED = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("upgrade_aquatic:pickerel_weed_purple"));
    public static Supplier<Block> WHITE_SEAROCKET = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("upgrade_aquatic:searocket_white"));
    public static Supplier<Block> PINK_SEAROCKET = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("upgrade_aquatic:searocket_pink"));
    public static Supplier<Block> GLOWSHROOM = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:glowshroom"));
    public static Supplier<Block> WARM_MONKEY_BRUSH = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:warm_monkey_brush"));
    public static Supplier<Block> HOT_MONKEY_BRUSH = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:monkey_brush"));
    public static Supplier<Block> SCALDING_MONKEY_BRUSH = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:scalding_monkey_brush"));
    public static Supplier<Block> GILIA = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:gilia"));
    public static Supplier<Block> YUCCA_FLOWER = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:yucca_flower"));
    public static Supplier<Block> BARREL_CACTUS = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:barrel_cactus"));
    public static Supplier<Block> CATTAIL = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("swampexpansion:cattail"));

	public static Supplier<Block> BAMBOO_TORCH = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bambooblocks:bamboo_torch"));
    public static Supplier<Block> WHITE_DELPHINIUM = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:white_delphinium"));
    public static Supplier<Block> BLUE_DELPHINIUM = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:blue_delphinium"));
    public static Supplier<Block> PINK_DELPHINIUM = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:pink_delphinium"));
    public static Supplier<Block> PURPLE_DELPHINIUM = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bloomful:purple_delphinium"));
    public static Supplier<Block> OVERWORLD_CORROCK_CROWN = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:corrock_crown_standing_overworld"));
    public static Supplier<Block> NETHER_CORROCK_CROWN = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:corrock_crown_standing_nether"));
    public static Supplier<Block> END_CORROCK_CROWN = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:corrock_crown_standing_end"));
    public static Supplier<Block> TALL_POISE_BUSH = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:poise_grass_tall"));
    public static Supplier<Block> OVERWORLD_CORROCK = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:corrock_overworld"));
    public static Supplier<Block> NETHER_CORROCK = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:corrock_nether"));
    public static Supplier<Block> END_CORROCK = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic:corrock_end"));
    //public static Supplier<Block> CAVE_ROOT = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:root"));
    public static Supplier<Block> FLOWERING_RUSH = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("upgrade_aquatic:flowering_rush"));
}
