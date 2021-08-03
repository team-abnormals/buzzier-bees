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
	public abstract boolean isTiredOfLookingForNectar();

	@Shadow
	public abstract boolean hasNectar();

	@Shadow
	public abstract boolean isHiveNearFire();

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/BeeEntity;getTarget()Lnet/minecraft/entity/LivingEntity;", shift = At.Shift.AFTER), method = "wantsToEnterHive", cancellable = true)
	private void canEnterHive(CallbackInfoReturnable<Boolean> cir) {
		boolean sunny = this.getEffect(BBEffects.SUNNY.get()) != null;
		boolean flag = this.isTiredOfLookingForNectar() || this.level.isRaining() || (this.level.isNight() && !sunny) || this.hasNectar();
		cir.setReturnValue(flag && !this.isHiveNearFire());
	}
}
