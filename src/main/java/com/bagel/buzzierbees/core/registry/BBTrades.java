package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.BuzzierBees;
import com.bagel.buzzierbees.core.util.TradeUtils;

import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MODID)
public class BBTrades {
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.CARTWHEEL.get(), 2, 1, 5, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.DAYBLOOM.get(), 1, 1, 12, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.PINK_CLOVER.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.WHITE_CLOVER.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.VIOLET.get(), 1, 1, 12, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.BLUEBELL.get(), 1, 1, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.COLUMBINE.get(), 1, 1, 7, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(BBBlocks.JOLYCE.get(), 1, 1, 8, 1));

	}
	
	/*@SubscribeEvent
	public static void onVillagerTradesEvent(VillagerTradesEvent event) {
		if(event.getType() == VillagerProfession.FARMER) {
			event.getTrades().get(3).add(new TradeUtils.EmeraldsForItemsTrade(ModItems.PASSIONFRUIT.get(), 8, 1, 7, 20));
			event.getTrades().get(3).add(new TradeUtils.ItemsForEmeraldsTrade(ModItems.PASSIONFRUIT_TART.get(), 4, 16, 4, 15));	
			event.getTrades().get(5).add(new TradeUtils.ItemsForEmeraldsTrade(ModItems.SHIMMERING_PASSIONFRUIT.get(), 3, 3, 5, 30));	
		}
    }*/
}
