package com.bagel.buzzierbees.core;

import com.bagel.buzzierbees.common.dispenser.BeeBottleDispenseBehavior;
import com.bagel.buzzierbees.common.dispenser.BugBottleDispenseBehavior;
import com.bagel.buzzierbees.core.other.BBCompostables;
import com.bagel.buzzierbees.core.other.BBFlammables;
import com.bagel.buzzierbees.core.other.BBRenderLayers;
import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.registry.BBEffects;
import com.bagel.buzzierbees.core.registry.BBEntities;
import com.bagel.buzzierbees.core.registry.BBFeatures;
import com.bagel.buzzierbees.core.registry.BBItems;
import com.bagel.buzzierbees.core.registry.BBVillagers;
import com.bagel.buzzierbees.core.util.BlockColorManager;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

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
	public static final String MODID = "buzzierbees";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);
	
    public BuzzierBees() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredEntityRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredTileEntityRegister().register(modEventBus);

    	BBBlocks.PAINTINGS.register(modEventBus);
    	BBEffects.EFFECTS.register(modEventBus);
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
			BBFlammables.registerFlammables();
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
    		BlockColorManager.registerBlockColors();
    		BBItems.setupItemProperties();
    	});
	}
    
    @OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		REGISTRY_HELPER.processSpawnEggColors(event);
	}
}
