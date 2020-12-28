package com.minecraftabnormals.buzzier_bees.core.mixin;

import com.minecraftabnormals.buzzier_bees.core.registry.BBEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

	protected MobEntityMixin(EntityType<? extends LivingEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Inject(at = @At("TAIL"), method = "isInDaylight", cancellable = true)
	private void isInDaylight(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(this.getActivePotionEffect(BBEffects.SUNNY.get()) != null && !this.isInWaterRainOrBubbleColumn() && !this.world.isRemote);
	}
}
