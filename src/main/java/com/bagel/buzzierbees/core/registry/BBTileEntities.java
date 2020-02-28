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
					BBBlocks.ALLIUM_SCENTED_CANDLE.get(), //fire resistance
					BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get(), //blindness
					BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get(), //saturation
					BBBlocks.DANDELION_SCENTED_CANDLE.get(), //saturation
					BBBlocks.CORNFLOWER_SCENTED_CANDLE.get(), //jump boost
					BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get(), //poison
					BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get(), //regeneration
					BBBlocks.POPPY_SCENTED_CANDLE.get(), //night vision
					BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get(), //weakness
					BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get(), //weakness
					BBBlocks.PINK_TULIP_SCENTED_CANDLE.get(), //weakness
					BBBlocks.RED_TULIP_SCENTED_CANDLE.get(), //weakness
					BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get(), //wither
					BBBlocks.CARTWHEEL_SCENTED_CANDLE.get(),
					BBBlocks.BLUEBELL_SCENTED_CANDLE.get(),
					BBBlocks.COLUMBINE_SCENTED_CANDLE.get(),
					BBBlocks.JOLYCE_SCENTED_CANDLE.get(),
					BBBlocks.PINK_CLOVER_SCENTED_CANDLE.get(),
					BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.get(),
					BBBlocks.DAYBLOOM_SCENTED_CANDLE.get(),
					BBBlocks.VIOLET_SCENTED_CANDLE.get())
					.build(null));


    /*public static final RegistryObject<TileEntityType<SignTileEntity>> SIGNS = TILE_ENTITY_TYPES.register("hive_sign",
			() -> TileEntityType.Builder.create(SignTileEntity::new,
					ModBlocks.HIVE_SIGN,
					ModBlocks.HIVE_WALL_SIGN)
					.build(null));*/

}