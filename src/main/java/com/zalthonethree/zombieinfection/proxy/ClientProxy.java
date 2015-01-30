package com.zalthonethree.zombieinfection.proxy;

import com.zalthonethree.zombieinfection.event.TooltipEncryption;

import net.minecraftforge.common.MinecraftForge;


public class ClientProxy extends CommonProxy {
	private boolean encrytionRegistered = false;
	
	@Override public void init() {
		super.init();
		if (!encrytionRegistered) MinecraftForge.EVENT_BUS.register(new TooltipEncryption());
		encrytionRegistered = true;
	}
}