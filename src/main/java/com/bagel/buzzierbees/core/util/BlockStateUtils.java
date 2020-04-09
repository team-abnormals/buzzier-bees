package com.bagel.buzzierbees.core.util;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;

public class BlockStateUtils {
	public static final IntegerProperty CANDLES_1_4 = IntegerProperty.create("candles", 1, 4);
	public static final BooleanProperty PATCH = BooleanProperty.create("patch");
	public static final BooleanProperty FLOWER = BooleanProperty.create("flower");
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	public static final IntegerProperty HONEY_LEVEL_0_4 = IntegerProperty.create("honey_level", 0, 4);
}
