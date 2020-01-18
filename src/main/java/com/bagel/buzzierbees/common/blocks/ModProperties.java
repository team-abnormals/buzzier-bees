package com.bagel.buzzierbees.common.blocks;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;

public class ModProperties {
	public static final IntegerProperty CANDLES_1_4 = IntegerProperty.create("candles", 1, 4);
	public static final BooleanProperty PATCH = BooleanProperty.create("patch");
}
