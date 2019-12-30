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
        		ModBlocks.WITHER_ROSE_SCENTED_CANDLE) //wither
        		.build(null));
}