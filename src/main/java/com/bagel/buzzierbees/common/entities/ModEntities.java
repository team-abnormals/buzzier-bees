package com.bagel.buzzierbees.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
	public static EntityType<HoneySlimeEntity> HONEY_SLIME;
	public static EntityType<BoatEntity> HIVE_BOAT;
	
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        ModEntities.HONEY_SLIME = EntityType.Builder.<HoneySlimeEntity>create(HoneySlimeEntity::new, EntityClassification.MONSTER).size(2.04F, 2.04F).build("buzzierbees:honey_slime");
        ModEntities.HONEY_SLIME.setRegistryName("honey_slime");

        ForgeRegistries.ENTITIES.register(ModEntities.HONEY_SLIME);
    }

    public static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, EntityClassification classification, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        ResourceLocation location = new ResourceLocation("buzzierbees", name);
        EntityType<T> type = EntityType.Builder.<T>create(factory, classification).setTrackingRange(trackingRange).setUpdateInterval(updateFrequency).setShouldReceiveVelocityUpdates(sendsVelocityUpdates).build(location.toString());
        type.setRegistryName(name);
        ForgeRegistries.ENTITIES.register(type);
        return type;
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends HoneySlimeEntity>)ModEntities.HONEY_SLIME, HoneySlimeRenderer::new);
    }
}
