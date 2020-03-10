package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.common.blocks.CandleBlock;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "buzzierbees")
public class BBEvents {
	@SubscribeEvent
	public static void entityJoinWorldEvent(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof ItemEntity && ((ItemEntity) entity).getItem().getItem().isIn(BBTags.CANDLES)) {
			event.getWorld().getEntitiesWithinAABB(FallingBlockEntity.class, entity.getBoundingBox()).stream()
			.filter(falling -> falling.getBlockState().getBlock() instanceof CandleBlock && entity.getPositionVec().equals(falling.getPositionVec()))
			.findAny().ifPresent(falling -> ((ItemEntity) entity).getItem().setCount(falling.getBlockState().get(CandleBlock.CANDLES)));
		}
	}
	    
	@SubscribeEvent
	public static void entityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
		if(event.getTarget() != null && !event.getWorld().isRemote) {
			
			Item bottle = null;
			boolean successful = false;
			
			Entity target = event.getTarget();
			EntityType<?> targetType = target.getType();
			PlayerEntity player = event.getPlayer();
			ItemStack stack = event.getItemStack();
			
			if (targetType == EntityType.SILVERFISH) { bottle = BBItems.BOTTLE_OF_SILVERFISH.get(); successful = true; }
    		if (targetType == EntityType.ENDERMITE) { bottle = BBItems.BOTTLE_OF_ENDERMITE.get(); successful = true; }
    		if (targetType == EntityType.BEE) { bottle = BBItems.BOTTLE_OF_BEE.get(); successful = true; }
    		
    		ItemStack bottleItem = new ItemStack(bottle);
    		
    		if (target.hasCustomName()) {
    			ITextComponent name = target.getCustomName();
    			bottleItem = bottleItem.setDisplayName(name);
    		}
    		
			if(successful && ((MobEntity) target).getHealth() > 0) {
				if(!stack.isEmpty() && stack.getItem() == Items.GLASS_BOTTLE) {
					stack.shrink(1);
    	    		player.addStat(Stats.ITEM_USED.get(event.getItemStack().getItem()));
    	    		if (stack.isEmpty()) {
    	    			player.setHeldItem(event.getHand(), bottleItem);
    	    		} else if (!player.inventory.addItemStackToInventory(bottleItem)) {
    	    			player.dropItem(bottleItem, false);
    	    		}

					player.swingArm(event.getHand());
					event.getTarget().remove();
				}
			}
		}
	}
}
