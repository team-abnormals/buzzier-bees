package com.bagel.buzzier_bees.common.dispenser;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;

public class BeeBottleDispenseBehavior extends OptionalDispenseBehavior {
	
	public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().get(DispenserBlock.FACING);
        CompoundNBT tag = stack.getOrCreateTag();
        Entity entity = EntityType.BEE.spawn(source.getWorld(), stack, (PlayerEntity)null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
        
        if(entity instanceof BeeEntity && tag != null) {
        	BeeEntity bee = (BeeEntity)entity;
        	
            int anger = tag.contains("AngerTime") ? tag.getInt("AngerTime") : 0;
            int age = tag.contains("Age") ? tag.getInt("Age") : 0;
            boolean nectar = tag.contains("HasNectar") ? tag.getBoolean("HasNectar") : false;
            boolean stung = tag.contains("HasStung") ? tag.getBoolean("HasStung") : false;
            
            bee.setGrowingAge(age);
            bee.setHasNectar(nectar);
            bee.setHasStung(stung);
            bee.func_230260_a__(anger);
        }
        return new ItemStack(Items.GLASS_BOTTLE);
     }
}
