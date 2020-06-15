package com.bagel.buzzierbees.core.other;

import java.util.Set;

import com.bagel.buzzierbees.core.BuzzierBees;
import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = BuzzierBees.MODID)
public class BBEvents {
	private static final Set<ResourceLocation> DESERT_LOOT_INJECTIONS = Sets.newHashSet(LootTables.CHESTS_DESERT_PYRAMID);
	private static final Set<ResourceLocation> JUNGLE_LOOT_INJECTIONS = Sets.newHashSet(LootTables.CHESTS_JUNGLE_TEMPLE);
	
	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		if (DESERT_LOOT_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(BuzzierBees.MODID, "injections/desert_pyramid")).weight(1).quality(0)).name("desert_pyramid").build();
			event.getTable().addPool(pool);
		}
		if (JUNGLE_LOOT_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(BuzzierBees.MODID, "injections/jungle_temple")).weight(1).quality(0)).name("jungle_temple").build();
			event.getTable().addPool(pool);
		}
	}
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void placeHangingPot(RightClickBlock event) {
		BlockPos pos = event.getPos().offset(event.getFace());
		ItemStack item = event.getItemStack();
		World world = event.getWorld();
		boolean validPos = Block.hasEnoughSolidSide(world, pos.up(), event.getFace()) || world.getBlockState(pos.up()).getBlock().isIn(BlockTags.LEAVES);
		
		if (((event.getFace() == Direction.DOWN && (validPos)) || (world.getBlockState(pos.down()).isAir() && world.getBlockState(pos).isValidPosition(world, pos) && (validPos))) && world.getBlockState(pos).isAir() && item.getItem() == Blocks.FLOWER_POT.asItem()) {
			event.getWorld().setBlockState(pos, BBBlocks.HANGING_FLOWER_POT.get().getDefaultState());
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
		PlayerEntity player = event.getPlayer();
		ResourceLocation pot = new ResourceLocation(("buzzierbees:potted_" + item.getItem().getRegistryName().getPath()));
		if (world.getBlockState(pos).getBlock() == Blocks.FLOWER_POT && ForgeRegistries.BLOCKS.containsKey(pot) && item.getItem().isIn(BBTags.Items.MODDED_POTTABLES)) {
			world.setBlockState(pos, ForgeRegistries.BLOCKS.getValue(pot).getDefaultState());
			player.swingArm(event.getHand());
			player.addStat(Stats.POT_FLOWER);
			if (!player.abilities.isCreativeMode) item.shrink(1);
		}
	}
	
	@SubscribeEvent
    public static void renewableFlowers(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = world.getBlockState(pos).getBlock();

        ItemStack stack = player.getHeldItem(event.getHand());
        if (stack.getItem() != Items.BONE_MEAL) return;
        
        if (!(block instanceof FlowerBlock) || block.isIn(BBTags.Blocks.FLOWER_BLACKLIST) || (block instanceof IGrowable && ((IGrowable) block).canUseBonemeal(world, world.rand, pos, world.getBlockState(pos)))) return;
        if (!player.isCreative()) stack.shrink(1);
        player.swingArm(event.getHand());
        if (world.isRemote) BoneMealItem.spawnBonemealParticles(world, pos, world.rand.nextInt(12));
        Block.spawnAsEntity(world, pos, new ItemStack(block, 1));
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
        		tag.putBoolean("HasNectar", bee.hasNectar());
        		tag.putBoolean("HasStung", bee.hasStung());
        		tag.putInt("Anger", bee.getAnger());
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
