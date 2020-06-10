package com.bagel.buzzierbees.core;

import com.bagel.buzzierbees.common.dispenser.BeeBottleDispenseBehavior;
import com.bagel.buzzierbees.common.dispenser.BugBottleDispenseBehavior;
import com.bagel.buzzierbees.core.other.BBBlockData;
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

@Mod("buzzierbees")
@EventBusSubscriber(modid = "buzzierbees")
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
    	
        modEventBus.addListener(this::setup);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
		});
    }
    
    public void initSetupClient()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
	}
    
    private void setupClient(final FMLClientSetupEvent event) {
		BBBlockData.setupRenderLayer();
		BBEntities.registerRendering();
		BlockColorManager.registerBlockColors();
	}
    
    @OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		REGISTRY_HELPER.processSpawnEggColors(event);
	}
    
    private void setup(final FMLCommonSetupEvent event)
    {
		// Note: This was deprecated too early by Forge. There is no replacement yet, so
		// the deprecation can (and should) be safely disregarded.
		//noinspection deprecation
		DeferredWorkQueue.runLater(() -> {
			BBBlockData.registerCompostables();
			BBBlockData.registerFlammables();
			BBEffects.addBrewingRecipes();
			BBFeatures.addFeatures();
			BBEntities.addEntitySpawns();
			BBVillagers.init();

			DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_BEE.get(), new BeeBottleDispenseBehavior());
			DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_SILVERFISH.get(), new BugBottleDispenseBehavior());
			DispenserBlock.registerDispenseBehavior(BBItems.BOTTLE_OF_ENDERMITE.get(), new BugBottleDispenseBehavior());
		});
    }
}
