package com.teamabnormals.buzzier_bees.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.buzzier_bees.common.blockentity.ScentedCandleBlockEntity;
import com.teamabnormals.buzzier_bees.common.blocks.ScentedCandleBlock;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBBlockEntities {
	public static final BlockEntitySubRegistryHelper HELPER = BuzzierBees.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final RegistryObject<BlockEntityType<ScentedCandleBlockEntity>> SCENTED_CANDLE = HELPER.createBlockEntity("scented_candle", ScentedCandleBlockEntity::new, () -> BlockEntitySubRegistryHelper.collectBlocks(ScentedCandleBlock.class));
}
