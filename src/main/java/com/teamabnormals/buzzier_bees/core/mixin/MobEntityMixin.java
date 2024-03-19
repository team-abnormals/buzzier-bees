package com.teamabnormals.buzzier_bees.core.mixin;

import com.teamabnormals.buzzier_bees.core.registry.BBMobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobEntityMixin extends LivingEntity {

	protected MobEntityMixin(EntityType<? extends LivingEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Inject(at = @At("TAIL"), method = "isSunBurnTick", cancellable = true)
	private void isInDaylight(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(this.getEffect(BBMobEffects.SUNNY.get()) != null && !this.isInWaterRainOrBubble() && !this.level().isClientSide);
	}
}
