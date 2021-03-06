package com.zalthonethree.zombieinfection.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TimeInfectedTrackingClient {
	@SideOnly(Side.CLIENT) public static void setSecondsInfected(int Seconds) {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().player;
		player.getEntityData().setInteger("TimeInfectedTracking", Seconds);
	}
	
	@SideOnly(Side.CLIENT) public static int getSecondsInfected() {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().player;
		NBTTagCompound nbt = player.getEntityData();
		return nbt.hasKey("TimeInfectedTracking") ? nbt.getInteger("TimeInfectedTracking") : 0;
	}
}