package com.teamabnormals.buzzier_bees.core.other;

import com.teamabnormals.blueprint.core.util.TradeUtil;
import com.teamabnormals.blueprint.core.util.TradeUtil.BlueprintTrade;
import com.teamabnormals.buzzier_bees.core.BuzzierBees;
import com.teamabnormals.buzzier_bees.core.registry.BBBlocks;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MOD_ID)
public class BBTrades {

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		TradeUtil.addWandererTrades(event,
				new BlueprintTrade(1, BBBlocks.PINK_CLOVER.get().asItem(), 1, 6, 1),
				new BlueprintTrade(1, BBBlocks.WHITE_CLOVER.get().asItem(), 1, 6, 1),
				new BlueprintTrade(1, BBBlocks.BUTTERCUP.get().asItem(), 1, 6, 1)
		);
	}
}
