package com.bagel.buzzierbees.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Iterator;

public class CureItem extends Item {
    public CureItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack item, World world, LivingEntity livingEntity) {
        PlayerEntity playerEntity = livingEntity instanceof PlayerEntity ? (PlayerEntity)livingEntity : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, item);
        }

        if (!world.isRemote) {
            livingEntity.removePotionEffect(getEffectFromItem(item));
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

    public int getUseDuration(ItemStack itemStack) {
        return 40;
    }

    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.DRINK;
    }

    public SoundEvent func_225520_U__() {
        return SoundEvents.field_226141_eV_;
    }

    public SoundEvent func_225519_S__() {
        return SoundEvents.field_226141_eV_;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand hand) {
        player.setActiveHand(hand);
        return ActionResult.func_226248_a_(player.getHeldItem(hand));
    }

    public String getTranslationKey(ItemStack itemStack) {
        return this.getTranslationKey() + "." + getEffectFromItem(itemStack).getRegistryName();
    }

    public static Effect getEffectFromItem(ItemStack itemStack) {
        return getEffectFromNBT(itemStack.getTag());
    }

    public static Effect getEffectFromNBT(@Nullable CompoundNBT nbt) {
        return nbt == null ? null : getEffectForName(nbt.getString("Cure"));
    }

    public static Effect getEffectForName(String name) {
        return ForgeRegistries.POTIONS.getValue(ResourceLocation.tryCreate(name));
    }

    @Override
    public void fillItemGroup(ItemGroup itemGroup, NonNullList<ItemStack> itemStacks) {
        if (this.isInGroup(itemGroup)) {
            @SuppressWarnings("rawtypes")
			Iterator iterator = ForgeRegistries.POTIONS.iterator();

            while(iterator.hasNext()) {
                Effect effect = (Effect)iterator.next();
                if (effect != null) {
                    itemStacks.add(addCureToItemStack(new ItemStack(this), effect));
                }
            }
        }
    }

    public static ItemStack addCureToItemStack(ItemStack itemStack, Effect effect) {
        ResourceLocation resourceLocation = ForgeRegistries.POTIONS.getKey(effect);
        ItemStack newItemStack = new ItemStack(ModItems.CURE);
        newItemStack.getOrCreateTag().putString("Cure", resourceLocation.toString());
        return newItemStack;
    }
}
