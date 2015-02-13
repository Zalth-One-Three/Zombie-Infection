package com.zalthonethree.zombieinfection.utility;

import com.zalthonethree.zombieinfection.handler.PacketHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TimeInfectedTracking /*extends EntityDragon*/ {
	public static void update(EntityPlayer player) {
		if (!player.getEntityData().hasKey("TimeInfectedTracking")) {
			player.getEntityData().setInteger("TimeInfectedTracking", 0);
		} else {
			player.getEntityData().setInteger("TimeInfectedTracking", player.getEntityData().getInteger("TimeInfectedTracking") + 1);
		}
		PacketHandler.INSTANCE.sendTo(PacketHandler.getTimeInfectedUpdatePacket(player.getEntityData().getInteger("TimeInfectedTracking")), (EntityPlayerMP) player);
	}
	
	public static int getSecondsInfected(EntityPlayer player) {
		if (player.getEntityData().hasKey("TimeInfectedTracking")) {
			return player.getEntityData().getInteger("TimeInfectedTracking");
		}
		return 0;
	}
	
	public static void remove(EntityPlayer player) {
		if (player.getEntityData().hasKey("TimeInfectedTracking")) {
			player.getEntityData().removeTag("TimeInfectedTracking");
			PacketHandler.INSTANCE.sendTo(PacketHandler.getTimeInfectedUpdatePacket(0), (EntityPlayerMP) player);
		}
	}
}