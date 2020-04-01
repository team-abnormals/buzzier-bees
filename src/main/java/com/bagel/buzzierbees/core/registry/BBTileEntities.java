package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.tileentity.ScentedCandleTileEntity;
import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, BuzzierBees.MODID);

	public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = TILE_ENTITY_TYPES.register("scented_candle",
			() -> TileEntityType.Builder.create(ScentedCandleTileEntity::new,
					BBBlocks.ALLIUM_SCENTED_CANDLE.get(),
					BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get(),
					BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get(),
					BBBlocks.DANDELION_SCENTED_CANDLE.get(),
					BBBlocks.CORNFLOWER_SCENTED_CANDLE.get(),
					BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get(),
					BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get(),
					BBBlocks.POPPY_SCENTED_CANDLE.get(),
					BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get(),
					BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get(),
					BBBlocks.PINK_TULIP_SCENTED_CANDLE.get(),
					BBBlocks.RED_TULIP_SCENTED_CANDLE.get(),
					BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get(),
					
					BBBlocks.CARTWHEEL_SCENTED_CANDLE.get(),
					BBBlocks.BLUEBELL_SCENTED_CANDLE.get(),
					BBBlocks.COLUMBINE_SCENTED_CANDLE.get(),
					BBBlocks.DIANTHUS_SCENTED_CANDLE.get(),
					BBBlocks.PINK_CLOVER_SCENTED_CANDLE.get(),
					BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.get(),
					BBBlocks.VIOLET_SCENTED_CANDLE.get(),
					
					BBBlocks.YELLOW_HIBISCUS_SCENTED_CANDLE.get(),
					BBBlocks.ORANGE_HIBISCUS_SCENTED_CANDLE.get(),
					BBBlocks.RED_HIBISCUS_SCENTED_CANDLE.get(),
					BBBlocks.PINK_HIBISCUS_SCENTED_CANDLE.get(),
					BBBlocks.MAGENTA_HIBISCUS_SCENTED_CANDLE.get(),
					BBBlocks.PURPLE_HIBISCUS_SCENTED_CANDLE.get(),
					
					BBBlocks.WARM_MONKEY_BRUSH_SCENTED_CANDLE.get(),
					BBBlocks.HOT_MONKEY_BRUSH_SCENTED_CANDLE.get(),
					BBBlocks.SCALDING_MONKEY_BRUSH_SCENTED_CANDLE.get(),
					BBBlocks.GILIA_SCENTED_CANDLE.get(),
					BBBlocks.YUCCA_FLOWER_SCENTED_CANDLE.get(),
					
					BBBlocks.PINK_SEAROCKET_SCENTED_CANDLE.get(),
					BBBlocks.WHITE_SEAROCKET_SCENTED_CANDLE.get()
					
					).build(null));
}