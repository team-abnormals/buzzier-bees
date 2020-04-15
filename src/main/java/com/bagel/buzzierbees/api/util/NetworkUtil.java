package com.bagel.buzzierbees.api.util;

import org.apache.commons.lang3.ArrayUtils;

import com.bagel.buzzierbees.api.endimator.Endimation;
import com.bagel.buzzierbees.api.endimator.entity.IEndimatedEntity;
import com.bagel.buzzierbees.common.network.MessageCAnimation;
import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * @author - SmellyModder(Luke Tonon) & ExpensiveKoala
 * This class holds a list of useful network functions
 */
public class NetworkUtil {
	
	/**
	 * Sends an animation message to the clients to update an entity's animations
	 * @param entity - The Entity to send the packet for
	 * @param endimationToPlay - The endimation to play
	 */
	public static <E extends Entity & IEndimatedEntity> void setPlayingAnimationMessage(E entity, Endimation endimationToPlay) {
		if(!entity.world.isRemote) {
			BuzzierBees.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new MessageCAnimation(entity.getEntityId(), ArrayUtils.indexOf(entity.getEndimations(), endimationToPlay)));
			entity.setPlayingEndimation(endimationToPlay);
		}
	}
}
