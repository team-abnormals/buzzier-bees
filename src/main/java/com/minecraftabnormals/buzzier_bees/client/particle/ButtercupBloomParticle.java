package com.minecraftabnormals.buzzier_bees.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButtercupBloomParticle extends SpriteTexturedParticle {
	protected final IAnimatedSprite animatedSprite;
	private float angle;

	public ButtercupBloomParticle(IAnimatedSprite animatedSprite, ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(world, posX, posY, posZ, motionX, motionY, motionZ);
		this.xd = motionX;
		this.yd = motionY + (random.nextDouble() * 0.05D);
		this.zd = motionZ;
		this.angle = (float) Math.random() * ((float) Math.PI * 2F);
		this.animatedSprite = animatedSprite;
		this.lifetime = random.nextInt(75) + 75;
		this.quadSize = 0.2F * (this.random.nextFloat() * 0.5F + 0.5F);
		this.pickSprite(animatedSprite);
	}

	@Override
	public void tick() {
		if (this.age % 5 == 0) {
			this.angle = (float) Math.random() * ((float) Math.PI * 2F);
		}
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			this.yd -= 0.04D * this.gravity;
			this.move(this.xd, this.yd, this.zd);
			this.xd += Math.cos(this.angle) * 0.0005;
			this.zd += Math.sin(this.angle) * 0.0005;
			this.yd *= 0.98D;
		}
		this.alpha -= (this.lifetime / 10000F);
	}

	@Override
	public int getLightColor(float partialTick) {
		float f = this.lifetime / (((this.age + (this.lifetime * 0.5F)) + partialTick));
		f = MathHelper.clamp(f, 0F, 0.5F);
		int i = super.getLightColor(partialTick);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (f * 15f * 16f);
		if (j > 240) {
			j = 240;
		}
		return j | k << 16;
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite animatedSprite;

		public Factory(IAnimatedSprite animatedSprite) {
			this.animatedSprite = animatedSprite;
		}

		@Override
		public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ButtercupBloomParticle(this.animatedSprite, world, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}