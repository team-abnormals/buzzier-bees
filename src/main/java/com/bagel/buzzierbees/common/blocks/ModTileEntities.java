package com.bagel.buzzierbees.common.blocks;

import com.bagel.buzzierbees.common.BuzzierBees;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
	//TODO: We need to decide either we use ForgeRegister or DeferredRegister
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, BuzzierBees.MODID);

	public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = TILE_ENTITY_TYPES.register("scented_candle",
			() -> TileEntityType.Builder.create(ScentedCandleTileEntity::new,
					ModBlocks.ALLIUM_SCENTED_CANDLE, //fire resistance
					ModBlocks.AZURE_BLUET_SCENTED_CANDLE, //blindness
					ModBlocks.BLUE_ORCHID_SCENTED_CANDLE, //saturation
					ModBlocks.DANDELION_SCENTED_CANDLE, //saturation
					ModBlocks.CORNFLOWER_SCENTED_CANDLE, //jump boost
					ModBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE, //poison
					ModBlocks.OXEYE_DAISY_SCENTED_CANDLE, //regeneration
					ModBlocks.POPPY_SCENTED_CANDLE, //night vision
					ModBlocks.WHITE_TULIP_SCENTED_CANDLE, //weakness
					ModBlocks.ORANGE_TULIP_SCENTED_CANDLE, //weakness
					ModBlocks.PINK_TULIP_SCENTED_CANDLE, //weakness
					ModBlocks.RED_TULIP_SCENTED_CANDLE, //weakness
					ModBlocks.WITHER_ROSE_SCENTED_CANDLE, //wither
					ModBlocks.CARTWHEEL_SCENTED_CANDLE,
					ModBlocks.BLUEBELL_SCENTED_CANDLE,
					ModBlocks.COLUMBINE_SCENTED_CANDLE,
					ModBlocks.JOLYCE_SCENTED_CANDLE,
					ModBlocks.PINK_CLOVER_SCENTED_CANDLE,
					ModBlocks.WHITE_CLOVER_SCENTED_CANDLE,
					ModBlocks.DAYBLOOM_SCENTED_CANDLE,
					ModBlocks.VIOLET_SCENTED_CANDLE)
					.build(null));


    public static final RegistryObject<TileEntityType<SignTileEntity>> SIGNS = TILE_ENTITY_TYPES.register("waxed_wood_sign",
			() -> TileEntityType.Builder.create(SignTileEntity::new,
					ModBlocks.WAXED_WOOD_SIGN,
					ModBlocks.WAXED_WOOD_WALL_SIGN)
					.build(null));
}