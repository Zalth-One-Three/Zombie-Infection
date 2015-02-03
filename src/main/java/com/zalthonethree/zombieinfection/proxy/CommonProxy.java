package com.zalthonethree.zombieinfection.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.event.InfectedPlayerUpdateEvent;
import com.zalthonethree.zombieinfection.event.InfectionEvent;

import cpw.mods.fml.common.FMLCommonHandler;

public abstract class CommonProxy /*extends EntityDragon*/ implements IProxy {
	private boolean infectionRegistered = false;
	private boolean updateEventRegistered = false;
	
	public void init() {
		if (!infectionRegistered) {
			MinecraftForge.EVENT_BUS.register(new InfectionEvent());
			FMLCommonHandler.instance().bus().register(new InfectionEvent());
			infectionRegistered = true;
		}
		if (!updateEventRegistered) {
			MinecraftForge.EVENT_BUS.register(new InfectedPlayerUpdateEvent());
			FMLCommonHandler.instance().bus().register(new InfectedPlayerUpdateEvent());
			updateEventRegistered = true;
		}
	}
}