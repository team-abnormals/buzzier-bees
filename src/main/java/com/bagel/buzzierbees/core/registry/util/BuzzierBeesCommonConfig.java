package com.bagel.buzzierbees.core.registry.util;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber(bus = Bus.MOD)
public final class BuzzierBeesCommonConfig {
    public static boolean spawnHoneySlimes = true;
    private static BooleanValue spawnHoneySlimesCfg;

    public static final ForgeConfigSpec spec;

    static {
        final Pair<BuzzierBeesCommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(BuzzierBeesCommonConfig::new);
        spec = specPair.getRight();
    }

    private BuzzierBeesCommonConfig(ForgeConfigSpec.Builder builder) {
    	spawnHoneySlimesCfg = builder.define("spawnHoneySlimes", spawnHoneySlimes);
    }

    public static void refresh() {
    	spawnHoneySlimes = spawnHoneySlimesCfg.get();
    }

    @SubscribeEvent
    public static void onFileChange(ModConfig.Reloading event) {
        ((CommentedFileConfig) event.getConfig().getConfigData()).load();
        refresh();
    }
}