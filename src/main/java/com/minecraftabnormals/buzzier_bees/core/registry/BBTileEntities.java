package com.minecraftabnormals.buzzier_bees.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.TileEntitySubRegistryHelper;
import com.minecraftabnormals.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.minecraftabnormals.buzzier_bees.common.tileentity.ScentedCandleTileEntity;
import com.minecraftabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBTileEntities {
	public static final TileEntitySubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getTileEntitySubHelper();

	public static final RegistryObject<TileEntityType<ScentedCandleTileEntity>> SCENTED_CANDLE = HELPER.createTileEntity("scented_candle", ScentedCandleTileEntity::new, () -> TileEntitySubRegistryHelper.collectBlocks(ScentedCandleBlock.class));
}
