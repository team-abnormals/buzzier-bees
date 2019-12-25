package com.bagel.buzzierbees.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class CurePotionItem extends PotionItem {

    public CurePotionItem(Properties p_i48476_1_) {
        super(p_i48476_1_);
    }

    public ItemStack onItemUseFinish(ItemStack item, World world, LivingEntity livingEntity) {
        PlayerEntity playerEntity = livingEntity instanceof PlayerEntity ? (PlayerEntity)livingEntity : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, item);
        }

        if (!world.isRemote) {
            List<EffectInstance> lvt_5_1_ = PotionUtils.getEffectsFromStack(item);
            Iterator var6 = lvt_5_1_.iterator();

            while(var6.hasNext()) {
                EffectInstance effect = (EffectInstance)var6.next();
                livingEntity.removePotionEffect(effect.getPotion());
            }
        }

        if (playerEntity != null) {
            playerEntity.addStat(Stats.ITEM_USED.get(this));
            if (!playerEntity.abilities.isCreativeMode) {
                item.shrink(1);
            }
        }

        if (playerEntity == null || !playerEntity.abilities.isCreativeMode) {
            if (item.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerEntity != null) {
                playerEntity.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return item;
    }

    public int getUseDuration(ItemStack p_77626_1_) {
        return 40;
    }

    public UseAction getUseAction(ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }

    public SoundEvent func_225520_U__() {
        return SoundEvents.field_226141_eV_;
    }

    public SoundEvent func_225519_S__() {
        return SoundEvents.field_226141_eV_;
    }
}
