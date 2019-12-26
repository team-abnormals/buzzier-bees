package com.bagel.buzzierbees.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

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

    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        p_77659_2_.setActiveHand(p_77659_3_);
        return ActionResult.func_226248_a_(p_77659_2_.getHeldItem(p_77659_3_));
    }

    public boolean hasEffect(ItemStack p_77636_1_) {
        return super.hasEffect(p_77636_1_) || !PotionUtils.getEffectsFromStack(p_77636_1_).isEmpty();
    }

    public String getTranslationKey(ItemStack p_77667_1_) {
        return this.getTranslationKey() + getEffectFromItem(p_77667_1_).toString();
    }

    public static Effect getEffectFromItem(ItemStack p_185191_0_) {
        return getEffectFromNBT(p_185191_0_.getTag());
    }

    public static Effect getEffectFromNBT(@Nullable CompoundNBT p_185187_0_) {
        return p_185187_0_ == null ? null : getEffectForName(p_185187_0_.getString("Cure"));
    }

    public static Effect getEffectForName(String name) {
        return ForgeRegistries.POTIONS.getValue(ResourceLocation.tryCreate(name));
    }

    public static ListNBT getEffects(ItemStack itemStack) {
        CompoundNBT nbt = itemStack.getTag();
        return nbt != null ? nbt.getList("StoredEffects", 10) : new ListNBT();
    }

    /*public static void addEffect(ItemStack itemStack, Effect p_92115_1_) {
        ListNBT nbtEffects = getEffects(itemStack);
        boolean flag = true;
        ResourceLocation resourceLocation = ForgeRegistries.POTIONS.getKey(p_92115_1_.getEffect());

        for(int lvt_5_1_ = 0; lvt_5_1_ < nbtEffects.size(); ++lvt_5_1_) {
            CompoundNBT lvt_6_1_ = nbtEffects.getCompound(lvt_5_1_);
            ResourceLocation lvt_7_1_ = ResourceLocation.tryCreate(lvt_6_1_.getString("id"));
            if (lvt_7_1_ != null && lvt_7_1_.equals(resourceLocation)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("id", String.valueOf(resourceLocation));
            nbtEffects.add(nbt);
        }

        itemStack.getOrCreateTag().put("StoredEffects", nbtEffects);
    }*/

    @Override
    public void fillItemGroup(ItemGroup p_150895_1_, NonNullList<ItemStack> itemStacks) {
        if (this.isInGroup(p_150895_1_)) {
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

    /*public static void addEffect(ItemStack p_92115_0_, Effect p_92115_1_) {
        ListNBT lvt_2_1_ = getEffects(p_92115_0_);
        boolean lvt_3_1_ = true;
        ResourceLocation lvt_4_1_ = ForgeRegistries.POTIONS.getKey(p_92115_1_);

        for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_2_1_.size(); ++lvt_5_1_) {
            CompoundNBT lvt_6_1_ = lvt_2_1_.getCompound(lvt_5_1_);
            ResourceLocation lvt_7_1_ = ResourceLocation.tryCreate(lvt_6_1_.getString("id"));
                lvt_3_1_ = false;
                break;
            }
        }

        if (lvt_3_1_) {
            CompoundNBT lvt_5_2_ = new CompoundNBT();
            lvt_5_2_.putString("id", String.valueOf(lvt_4_1_));
            lvt_5_2_.putShort("lvl", (short)p_92115_1_.enchantmentLevel);
            lvt_2_1_.add(lvt_5_2_);
        }

        p_92115_0_.getOrCreateTag().put("StoredEnchantments", lvt_2_1_);
    }*/

    /*@Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemStack.addEnchantmentTooltips(tooltip, EnchantedBookItem.getEnchantments(stack));
    }*/
}
