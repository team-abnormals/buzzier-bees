package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.core.BuzzierBees;
import com.bagel.buzzierbees.core.registry.util.TradeUtils;

import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BuzzierBees.MODID)
public class ModTrades {
	
	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.CARTWHEEL.get(), 2, 1, 5, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.DAYBLOOM.get(), 1, 1, 12, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.PINK_CLOVER.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.WHITE_CLOVER.get(), 1, 1, 6, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.VIOLET.get(), 1, 1, 12, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.BLUEBELL.get(), 1, 1, 8, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.COLUMBINE.get(), 1, 1, 7, 1));
		event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(ModBlocks.JOLYCE.get(), 1, 1, 8, 1));

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
