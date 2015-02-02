package com.zalthonethree.zombieinfection.utility;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public class TimeInfectedTracking /*extends EntityDragon*/ {
	private static HashMap<UUID, Integer> timeTrack = new HashMap<UUID, Integer>();
	
	public static void update(EntityPlayer player) {
		if (!timeTrack.containsKey(player.getUniqueID())) {
			timeTrack.put(player.getUniqueID(), player.getFoodStats().getFoodLevel());
		} else {
			timeTrack.put(player.getUniqueID(), timeTrack.get(player.getUniqueID()) + 1);
		}
	}
	
	public static int getTicksInfected(EntityPlayer player) {
		if (timeTrack.containsKey(player.getUniqueID())) {
			return timeTrack.get(player.getUniqueID());
		}
		return 0;
	}
	
	public static void remove(EntityPlayer player) {
		if (timeTrack.containsKey(player.getUniqueID())) {
			timeTrack.remove(player.getUniqueID());
		}
	}
}