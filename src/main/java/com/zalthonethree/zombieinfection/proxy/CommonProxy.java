package com.zalthonethree.zombieinfection.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.event.InfectedPlayerUpdateEvent;
import com.zalthonethree.zombieinfection.event.InfectionEvent;

public abstract class CommonProxy implements IProxy {
	
	public void init() {
		MinecraftForge.EVENT_BUS.register(new InfectionEvent());
		MinecraftForge.EVENT_BUS.register(new InfectedPlayerUpdateEvent());
	}
	
	@Override public void registerRenderers() {}
}