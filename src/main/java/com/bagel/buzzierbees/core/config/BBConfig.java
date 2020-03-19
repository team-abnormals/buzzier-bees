package com.bagel.buzzierbees.core.config;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber(bus = Bus.MOD)
public final class BBConfig {
	
	public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    
    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }
    
    public static boolean spawnHoneySlimes = true;
    

    public static class ServerConfig {
    	public final BooleanValue spawnHoneySlimes;

    	ServerConfig(ForgeConfigSpec.Builder builder) {
        	spawnHoneySlimes = builder.comment("Requires game restart").define("Spawn Honey Slimes", true);
        }
    }

    public static void refresh() {
    	spawnHoneySlimes = SERVER.spawnHoneySlimes.get();
    }

    @SubscribeEvent
    public static void onFileChange(ModConfig.Reloading event) {
        ((CommentedFileConfig) event.getConfig().getConfigData()).load();
        refresh();
    }
}