package com.bagel.buzzierbees.common.blocks;

import com.bagel.buzzierbees.common.BuzzierBees;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, BuzzierBees.MODID);
    
    public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = TILE_ENTITY_TYPES.register("scented_candle",
        () -> TileEntityType.Builder.create(ScentedCandleTileEntity::new, 
        		ModBlocks.ALLIUM_CANDLE, //fire resistance
        		ModBlocks.AZURE_BLUET_CANDLE, //blindness
        		ModBlocks.BLUE_ORCHID_CANDLE, //saturation
        		ModBlocks.DANDELION_CANDLE, //saturation
        		ModBlocks.CORNFLOWER_CANDLE, //jump boost
        		ModBlocks.LILY_OF_THE_VALLEY_CANDLE, //poison
        		ModBlocks.OXEYE_DAISY_CANDLE, //regeneration
        		ModBlocks.POPPY_CANDLE, //night vision
        		ModBlocks.WHITE_TULIP_CANDLE, //weakness
        		ModBlocks.ORANGE_TULIP_CANDLE, //weakness 
        		ModBlocks.PINK_TULIP_CANDLE, //weakness
        		ModBlocks.RED_TULIP_CANDLE, //weakness
        		ModBlocks.WITHER_ROSE_CANDLE) //wither
        		.build(null));
}