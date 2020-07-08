package com.bagel.buzzier_bees.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class CloverHoneyBottleItem extends Item {
   public CloverHoneyBottleItem(Item.Properties p_i225737_1_) {
      super(p_i225737_1_);
   }

   /**
    * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
    * the Item before the action is complete.
    */
   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
      super.onItemUseFinish(stack, worldIn, entityLiving);
      if (entityLiving instanceof ServerPlayerEntity) {
         ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
         CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
         serverplayerentity.addStat(Stats.ITEM_USED.get(this));
      }

      if (!worldIn.isRemote) {
         entityLiving.removePotionEffect(Effects.WITHER);
      }

      if (stack.isEmpty()) {
         return new ItemStack(Items.GLASS_BOTTLE);
      } else {
         if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            if (!playerentity.inventory.addItemStackToInventory(itemstack)) {
               playerentity.dropItem(itemstack, false);
            }
         }

         return stack;
      }
   }

   /**
    * How long it takes to use or consume an item
    */
   public int getUseDuration(ItemStack stack) {
      return 40;
   }

   /**
    * returns the action that specifies what animation to play when the items is being used
    */
   public UseAction getUseAction(ItemStack stack) {
      return UseAction.DRINK;
   }

   public SoundEvent func_225520_U__() {
      return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
   }

   public SoundEvent func_225519_S__() {
      return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
   }

   /**
    * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
    * {@link #onItemUse}.
    */
   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
      playerIn.setActiveHand(handIn);
      return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
   }
}
