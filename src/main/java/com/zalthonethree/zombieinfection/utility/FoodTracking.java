package com.zalthonethree.zombieinfection.utility;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public class FoodTracking /*extends EntityDragon*/ {
	private static HashMap<UUID, Integer> foodTrack = new HashMap<UUID, Integer>();
	
	public static void put(EntityPlayer player) {
		foodTrack.put(player.getUniqueID(), player.getFoodStats().getFoodLevel());
	}
	
	public static int get(EntityPlayer player) {
		if (foodTrack.containsKey(player.getUniqueID())) {
			return foodTrack.get(player.getUniqueID());
		}
		return 20;
	}
	
	public static void remove(EntityPlayer player) {
		if (foodTrack.containsKey(player.getUniqueID())) {
			foodTrack.remove(player.getUniqueID());
		}
	}
}
