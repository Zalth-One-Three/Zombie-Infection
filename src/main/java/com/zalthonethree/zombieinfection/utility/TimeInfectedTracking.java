package com.zalthonethree.zombieinfection.utility;

import net.minecraft.entity.player.EntityPlayer;

public class TimeInfectedTracking /*extends EntityDragon*/ {
	public static void update(EntityPlayer player) {
		if (!player.getEntityData().hasKey("TimeInfectedTracking")) {
			player.getEntityData().setInteger("TimeInfectedTracking", 0);
		} else {
			player.getEntityData().setInteger("TimeInfectedTracking", player.getEntityData().getInteger("TimeInfectedTracking") + 1);
		}
	}
	
	public static int getSecondsInfected(EntityPlayer player) {
		if (player.getEntityData().hasKey("TimeInfectedTracking")) {
			return player.getEntityData().getInteger("TimeInfectedTracking");
		}
		return 0;
	}
	
	public static void remove(EntityPlayer player) {
		if (!player.getEntityData().hasKey("TimeInfectedTracking")) {
			player.getEntityData().removeTag("TimeInfectedTracking");
		}
	}
}