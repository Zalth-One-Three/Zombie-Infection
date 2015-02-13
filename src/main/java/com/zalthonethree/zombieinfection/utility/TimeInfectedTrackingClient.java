package com.zalthonethree.zombieinfection.utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TimeInfectedTrackingClient /*extends EntityDragon*/ {
	@SideOnly(Side.CLIENT) public static void setSecondsInfected(int Seconds) {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
		player.getEntityData().setInteger("TimeInfectedTracking", Seconds);
	}
	
	@SideOnly(Side.CLIENT) public static int getSecondsInfected() {
		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
		NBTTagCompound nbt = player.getEntityData();
		return nbt.hasKey("TimeInfectedTracking") ? nbt.getInteger("TimeInfectedTracking") : 0;
	}
}