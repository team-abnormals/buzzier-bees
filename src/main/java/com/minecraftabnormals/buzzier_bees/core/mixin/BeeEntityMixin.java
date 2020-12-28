package com.minecraftabnormals.buzzier_bees.core.mixin;

import com.minecraftabnormals.buzzier_bees.core.registry.BBEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin extends AnimalEntity {

	protected BeeEntityMixin(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Shadow
	public abstract boolean failedPollinatingTooLong();

	@Shadow
	public abstract boolean hasNectar();

	@Shadow
	public abstract boolean isHiveNearFire();

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/BeeEntity;getAttackTarget()Lnet/minecraft/entity/LivingEntity;", shift = At.Shift.AFTER), method = "canEnterHive", cancellable = true)
	private void canEnterHive(CallbackInfoReturnable<Boolean> cir) {
		boolean sunny = this.getActivePotionEffect(BBEffects.SUNNY.get()) != null;
		boolean flag = this.failedPollinatingTooLong() || this.world.isRaining() || (this.world.isNightTime() && !sunny) || this.hasNectar();
		cir.setReturnValue(flag && !this.isHiveNearFire());
	}
}
