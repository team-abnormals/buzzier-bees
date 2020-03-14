package com.bagel.buzzierbees.common.items;

import java.util.Objects;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BeeBottleItem extends  Item {	
	
	public BeeBottleItem(EntityType<?> typeIn, Item.Properties properties) {
		super(properties);
	}

	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			ItemStack itemstack = context.getItem();
			BlockPos blockpos = context.getPos();
			Direction direction = context.getFace();
			BlockState blockstate = world.getBlockState(blockpos);

			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.offset(direction);
			}

            world.playSound(context.getPlayer(), context.getPos(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            CompoundNBT tag = itemstack.getOrCreateTag();
            Entity entity = EntityType.BEE.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.BUCKET, true,!Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            
            if(entity instanceof BeeEntity && tag != null) {
            	BeeEntity bee = (BeeEntity)entity;
            	
                int anger = tag.contains("Anger") ? tag.getInt("Anger") : 0;
                int age = tag.contains("Age") ? tag.getInt("Age") : 0;
                boolean nectar = tag.contains("HasNectar") ? tag.getBoolean("HasNectar") : false;
                boolean stung = tag.contains("HasStung") ? tag.getBoolean("HasStung") : false;
                
                bee.setGrowingAge(age);
                bee.func_226447_r_(nectar);
                bee.func_226449_s_(stung);
                bee.func_226453_u_(anger);
            }
            
            if (!context.getPlayer().abilities.isCreativeMode) {
            	context.getPlayer().setHeldItem(context.getHand(), new ItemStack(Items.GLASS_BOTTLE));
            }

			return ActionResultType.SUCCESS;
		}
	}
}
