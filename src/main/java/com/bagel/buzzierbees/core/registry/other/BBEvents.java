package com.bagel.buzzierbees.core.registry.other;

import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.bagel.buzzierbees.core.registry.BBTags;
import com.bagel.buzzierbees.core.registry.FloristBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

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
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void placeHangingPot(RightClickBlock event) {
		BlockPos pos = event.getPos().offset(event.getFace());
		ItemStack item = event.getItemStack();
		World world = event.getWorld();
		
		if (
				(event.getFace() == Direction.DOWN ||
				(world.getBlockState(pos.down()).isAir() && world.getBlockState(pos).isValidPosition(world, pos) && !world.getBlockState(pos.up()).isAir())) && world.getBlockState(pos).isAir() &&
				item.getItem() == Blocks.FLOWER_POT.asItem()		
				) {
			event.getWorld().setBlockState(pos, FloristBlocks.HANGING_FLOWER_POT.get().getDefaultState());
			event.getWorld().playSound(event.getPlayer(), pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			if (!event.getPlayer().abilities.isCreativeMode) item.shrink(1);
		}
	}
	
	@SubscribeEvent
	public static void potModdedItem(RightClickBlock event) {
		BlockPos pos = event.getPos();
		ItemStack item = event.getItemStack();
		World world = event.getWorld();
		ResourceLocation pot = new ResourceLocation(("buzzierbees:potted_" + item.getItem().getRegistryName().getPath()));
		if (world.getBlockState(pos).getBlock() == Blocks.FLOWER_POT && ForgeRegistries.BLOCKS.containsKey(pot) && item.getItem().isIn(BBTags.MODDED_POTTABLES)) {
			world.setBlockState(pos, ForgeRegistries.BLOCKS.getValue(pot).getDefaultState());
			event.getPlayer().swingArm(event.getHand());
			if (!event.getPlayer().abilities.isCreativeMode) item.shrink(1);
		}
	}
	    
	@SubscribeEvent
	public static void bottleBug(PlayerInteractEvent.EntityInteractSpecific event) {
		if(event.getTarget() != null && !event.getWorld().isRemote) {
			
			ItemStack itemstack = event.getPlayer().getHeldItem(event.getHand());
			Item item = itemstack.getItem();
			Hand hand = Hand.MAIN_HAND;
			
			Item bottle = null;
			boolean successful = false;
			
			Entity target = event.getTarget();
			EntityType<?> targetType = target.getType();
			PlayerEntity player = event.getPlayer();
			if (player.getHeldItemMainhand().getItem() == Items.GLASS_BOTTLE) {
				hand = Hand.MAIN_HAND;
			} else if (player.getHeldItemOffhand().getItem() == Items.GLASS_BOTTLE) {
				hand = Hand.OFF_HAND;
			}
			
			if (targetType == EntityType.SILVERFISH) { bottle = BBItems.BOTTLE_OF_SILVERFISH.get(); successful = true; }
    		if (targetType == EntityType.ENDERMITE) { bottle = BBItems.BOTTLE_OF_ENDERMITE.get(); successful = true; }
    		if (targetType == EntityType.BEE) {   			
    			bottle = BBItems.BOTTLE_OF_BEE.get(); 
    			successful = true;     			
    		}
    		ItemStack bottleItem = new ItemStack(bottle);
    		
    		if (targetType == EntityType.BEE) {
    			BeeEntity bee = (BeeEntity)target;
    			CompoundNBT tag = bottleItem.getOrCreateTag();
        		tag.putBoolean("HasNectar", bee.func_226411_eD_());
        		tag.putBoolean("HasStung", bee.func_226412_eE_());
        		tag.putInt("Anger", bee.func_226418_eL_());
        		tag.putInt("Age", bee.getGrowingAge());
        		tag.putFloat("Health", bee.getHealth());
        		//tag.putString("Effects", bee.getActivePotionEffects().toString());
    		}
    		
    		
    		if (target.hasCustomName()) {
    			ITextComponent name = target.getCustomName();
    			bottleItem = bottleItem.setDisplayName(name);
    		}
    		
			if(successful && ((MobEntity) target).isAlive()) {
				if(item == Items.GLASS_BOTTLE) {
					itemstack.shrink(1);
					event.getWorld().playSound(player, event.getPos(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
					player.addStat(Stats.ITEM_USED.get(event.getItemStack().getItem()));
					player.swingArm(hand);
					event.getTarget().remove();
					if (itemstack.isEmpty()) {
    	    			player.setHeldItem(hand, bottleItem);
    	    		} else if (!player.inventory.addItemStackToInventory(bottleItem)) {
    	    			player.dropItem(bottleItem, false);
    	    		}
				}
			}
		}
	}
}
