package com.minecraftabnormals.buzzier_bees.core;

import com.minecraftabnormals.buzzier_bees.common.dispenser.BeeBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.common.dispenser.BugBottleDispenseBehavior;
import com.minecraftabnormals.buzzier_bees.core.other.BBCompostables;
import com.minecraftabnormals.buzzier_bees.core.other.BBRenderLayers;
import com.minecraftabnormals.buzzier_bees.core.registry.BBBlocks;
import com.minecraftabnormals.buzzier_bees.core.registry.BBEffects;
import com.minecraftabnormals.buzzier_bees.core.registry.BBEntities;
import com.minecraftabnormals.buzzier_bees.core.registry.BBFeatures;
import com.minecraftabnormals.buzzier_bees.core.registry.BBItems;
import com.minecraftabnormals.buzzier_bees.core.registry.BBVillagers;
import com.minecraftabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("deprecation")
@Mod(BuzzierBees.MODID)
@EventBusSubscriber(modid = BuzzierBees.MODID)
public class BuzzierBees
{
	public static final String MODID = "buzzier_bees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);
	
    public BuzzierBees() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredEntityRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredTileEntityRegister().register(modEventBus);

    	BBBlocks.PAINTINGS.register(modEventBus);
    	BBEffects.POTIONS.register(modEventBus);
    	BBVillagers.PROFESSIONS.register(modEventBus);
    	BBVillagers.POI_TYPES.register(modEventBus);
    	
        modEventBus.addListener(this::setupCommon);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
		});
    }
    
    public void initSetupClient()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}
    
	private void setupCommon(final FMLCommonSetupEvent event)
    {
		DeferredWorkQueue.runLater(() -> {
			BBCompostables.registerCompostables();
			BBEffects.addBrewingRecipes();
			BBFeatures.addFeatures();
			BBEntities.addEntitySpawns();
			BBEntities.registerAttributes();
			BBVillagers.setupVillagers();

			REGISTRY_HELPER.processSpawnEggDispenseBehaviors();
			DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
			DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
			DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
		});
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
		BBEntities.registerRendering();
    	DeferredWorkQueue.runLater(() -> {
    		BBRenderLayers.setupRenderLayer();
    		BBItems.setupItemProperties();
    	});
	}
    
    @OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		REGISTRY_HELPER.processSpawnEggColors(event);
	}
}
