package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.tileentity.ScentedCandleTileEntity;
import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, BuzzierBees.MODID);

	public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = TILE_ENTITY_TYPES.register("scented_candle",
			() -> TileEntityType.Builder.create(ScentedCandleTileEntity::new,
					ModBlocks.ALLIUM_SCENTED_CANDLE.get(), //fire resistance
					ModBlocks.AZURE_BLUET_SCENTED_CANDLE.get(), //blindness
					ModBlocks.BLUE_ORCHID_SCENTED_CANDLE.get(), //saturation
					ModBlocks.DANDELION_SCENTED_CANDLE.get(), //saturation
					ModBlocks.CORNFLOWER_SCENTED_CANDLE.get(), //jump boost
					ModBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get(), //poison
					ModBlocks.OXEYE_DAISY_SCENTED_CANDLE.get(), //regeneration
					ModBlocks.POPPY_SCENTED_CANDLE.get(), //night vision
					ModBlocks.WHITE_TULIP_SCENTED_CANDLE.get(), //weakness
					ModBlocks.ORANGE_TULIP_SCENTED_CANDLE.get(), //weakness
					ModBlocks.PINK_TULIP_SCENTED_CANDLE.get(), //weakness
					ModBlocks.RED_TULIP_SCENTED_CANDLE.get(), //weakness
					ModBlocks.WITHER_ROSE_SCENTED_CANDLE.get(), //wither
					ModBlocks.CARTWHEEL_SCENTED_CANDLE.get(),
					ModBlocks.BLUEBELL_SCENTED_CANDLE.get(),
					ModBlocks.COLUMBINE_SCENTED_CANDLE.get(),
					ModBlocks.JOLYCE_SCENTED_CANDLE.get(),
					ModBlocks.PINK_CLOVER_SCENTED_CANDLE.get(),
					ModBlocks.WHITE_CLOVER_SCENTED_CANDLE.get(),
					ModBlocks.DAYBLOOM_SCENTED_CANDLE.get(),
					ModBlocks.VIOLET_SCENTED_CANDLE.get())
					.build(null));


    /*public static final RegistryObject<TileEntityType<SignTileEntity>> SIGNS = TILE_ENTITY_TYPES.register("hive_sign",
			() -> TileEntityType.Builder.create(SignTileEntity::new,
					ModBlocks.HIVE_SIGN,
					ModBlocks.HIVE_WALL_SIGN)
					.build(null));*/

}