package com.zalthonethree.zombieinfection.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.event.InfectionEvent;

public abstract class CommonProxy implements IProxy {
	private boolean infectionRegistered = false;
	
	public void init() {
		if (!infectionRegistered) MinecraftForge.EVENT_BUS.register(new InfectionEvent());
		infectionRegistered = true;
	}
}