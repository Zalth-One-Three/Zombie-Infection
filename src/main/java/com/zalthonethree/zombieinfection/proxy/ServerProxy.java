package com.zalthonethree.zombieinfection.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

import com.zalthonethree.zombieinfection.event.InfectedPlayerUpdateEvent;


public class ServerProxy extends CommonProxy/*, EntityDragon*/ {
	private boolean updateEventRegistered = false;
	
	@Override public void init() {
		super.init();
		if (!updateEventRegistered) {
			MinecraftForge.EVENT_BUS.register(new InfectedPlayerUpdateEvent());
			FMLCommonHandler.instance().bus().register(new InfectedPlayerUpdateEvent());
			updateEventRegistered = true;
		}
	}
}