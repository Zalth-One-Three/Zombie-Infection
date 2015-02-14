package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.client.gui.GuiEyeInfection;
import com.zalthonethree.zombieinfection.event.InfectedPlayerTooltipEncryptEvent;

public class ClientProxy extends CommonProxy/*, EntityDragon*/ {
	private boolean encrytionRegistered = false;
	
	@Override public void init() {
		super.init();
		MinecraftForge.EVENT_BUS.register(new GuiEyeInfection(Minecraft.getMinecraft()));
		if (!encrytionRegistered) MinecraftForge.EVENT_BUS.register(new InfectedPlayerTooltipEncryptEvent());
		encrytionRegistered = true;
	}
}