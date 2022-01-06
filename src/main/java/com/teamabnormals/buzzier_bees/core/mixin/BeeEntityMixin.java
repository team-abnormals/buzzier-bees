package com.teamabnormals.buzzier_bees.core.mixin;

import com.teamabnormals.buzzier_bees.core.registry.BBEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bee.class)
public abstract class BeeEntityMixin extends Animal {

	protected BeeEntityMixin(EntityType<? extends Animal> type, Level worldIn) {
		super(type, worldIn);
	}

	@Shadow
	protected abstract boolean isTiredOfLookingForNectar();

	@Shadow
	public abstract boolean hasNectar();

	@Shadow
	protected abstract boolean isHiveNearFire();

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Bee;getTarget()Lnet/minecraft/world/entity/LivingEntity;", shift = At.Shift.AFTER), method = "wantsToEnterHive", cancellable = true)
	private void canEnterHive(CallbackInfoReturnable<Boolean> cir) {
		boolean sunny = this.getEffect(BBEffects.SUNNY.get()) != null;
		boolean flag = this.isTiredOfLookingForNectar() || this.level.isRaining() || (this.level.isNight() && !sunny) || this.hasNectar();
		cir.setReturnValue(flag && !this.isHiveNearFire());
	}
}
